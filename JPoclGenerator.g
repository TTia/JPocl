tree grammar JPoclGenerator;

options {
   tokenVocab=JPoclAST; 
   ASTLabelType=TypeTree;
   output=template;
}

scope BlockLocals{
	LinkedList<String> locals;
	int localsLimit;
	boolean isFunctionBlock;
}
scope FunctionStack{
	int stackLimit;
}

@header {
package gen;

import type.*;
import ast.*;
import java.util.LinkedList;
import java.util.Collection;
import java.util.LinkedHashMap;
}

@members {
	private String packageDst;
	private LinkedHashMap<String,FunctionType> api;
	private LinkedHashMap<String,StructType> dataTypes;

	private int getVariableLocalNumber(String id){
		int localNumber = 0;
		for(int s = $BlockLocals.size()-1; s>=0; s--){
			if($BlockLocals[s]::locals.contains(id)){
				localNumber = $BlockLocals[s]::locals.indexOf(id);
			}else{
				localNumber += $BlockLocals[s]::locals.size();
			}
			if($BlockLocals[s]::isFunctionBlock){
				break;
			}
		}
		return localNumber;
	}
	
	private void addVariableToLocals(String id){
		$BlockLocals::locals.addLast(id);
		
		for(int s = $BlockLocals.size()-1; s>=0; s--){
			$BlockLocals[s]::localsLimit++;
			if($BlockLocals[s]::isFunctionBlock){
				return;
			}
		}
	}
	
	private boolean isDeclared(String id){
		for(int s = $BlockLocals.size()-1; s>=0; s--){
			if($BlockLocals[s]::locals.contains(id)){
				return true;
			}
			if($BlockLocals[s]::isFunctionBlock){
				break;
			}
		}
		return false;
	}
	
  private boolean isLeftValue(TypeTree tree){
  	while(tree.parent.getType() != ASSIGN){
  		tree = (TypeTree) tree.parent;
  		if(tree.parent == null)
	 			return false;
  	}
		return tree.childIndex == 1;
  }
  
  private boolean isTopLevel(TypeTree tree){
  	return tree.parent.getType() == CALC;
  }
  
  private boolean hasReturnValueConsumed(TypeTree tree){
  	int tokenType = tree.parent.getType();
  	return !(tokenType == BLOCK || tokenType == CALC || tokenType == FUNCDECL);
  }
	
	private void sumStackDelta(int delta){
		$FunctionStack::stackLimit += delta;
	}
	
  private int astDepth(Tree tree){
   	int maxDepth = 0;
  	for(int i = 0; i<tree.getChildCount(); i++){
  		int childDepth = astDepth(tree.getChild(i))+1;
  		if(childDepth > maxDepth)
  			maxDepth = childDepth;
   		}
  	return maxDepth;
  }
  
  private void setStackLimit(int stackNeeded){
  	$FunctionStack::stackLimit = Math.max(stackNeeded, $FunctionStack::stackLimit);
  }
  
  private int calculateStackLimit(int leftStackLimit, int rightStackLimit){
  	return calculateStackLimit(leftStackLimit, rightStackLimit, +1);
  }
  
  private int calculateStackLimit(int leftStackLimit, int rightStackLimit, int delta){
  	return Math.max(leftStackLimit, rightStackLimit)+delta;
  }
	
	private int getStackLimit(){
		return $FunctionStack::stackLimit;
	}

}

@rulecatch {
catch (RecognitionException re) {
	throw re;
}
}
	
calc[String packageDst, LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes]
		scope BlockLocals;
		scope FunctionStack;
		@init{
			$BlockLocals::locals = new LinkedList<>();
			$BlockLocals::isFunctionBlock = true;
			this.packageDst = packageDst;
			this.api = api;
			this.dataTypes = dataTypes;
			
			List<Object> structDeclsST = new LinkedList<Object>();
			List<Object> functionDeclsST = new LinkedList<Object>();
			List<Object> stringTemplates = new LinkedList<Object>();			
		}
		: ^(CALC (stat{
					structDeclsST.addAll($stat.structDeclST);
					functionDeclsST.addAll($stat.functionDeclsST);
					stringTemplates.addAll($stat.stringTemplates);
				})+)
		-> calc(stackLimit = {$FunctionStack::stackLimit},
						localsLimit = {$BlockLocals::localsLimit},
						stmts = {stringTemplates}, structDecls={structDeclsST},
						functionDecls={functionDeclsST}, packageDst={packageDst})
		;

stat returns [List<Object> structDeclST, List<Object> functionDeclsST, List<Object> stringTemplates]
			@init{
				$structDeclST = new LinkedList<>();
				$functionDeclsST = new LinkedList<>();
				$stringTemplates = new LinkedList<>();
			}
			@after{
				if($stat.st != null)
					$stringTemplates.add($stat.st);
			}
			:
			echo {$stringTemplates.add($echo.st);}
			
			|^(ASSIGN expr qualifiedID {setStackLimit($expr.stackSize + 1);}) 
				-> {$qualifiedID.start.getStaticType() instanceof StructType}?
					 assignToObject(expr={$expr.st}, variable={$qualifiedID.st}, type={$qualifiedID.start.getStaticType()})
				-> assign(expr={$expr.st}, variable={$qualifiedID.st})
				
			| functionCall {$stringTemplates.add($functionCall.st); setStackLimit($functionCall.stackSize);}
			
      |^('if' expr s=stat {setStackLimit($expr.stackSize);}) 
       -> if(bexpr={$expr.st}, tokenNum={$start.getIndex()}, stmts={$s.stringTemplates})
      
      |^('while' expr s=stat) 
       -> while(bexpr={$expr.st}, tokenNum={$start.getIndex()}, stmts={$s.stringTemplates})
      
      | block {
	      $structDeclST.addAll($block.structDeclST);
	      $functionDeclsST.addAll($block.functionDeclsST);
	      $stringTemplates.addAll($block.stringTemplates);
      }
		  | structDecl {$structDeclST.add($structDecl.st);}
		  | functionDecl {$functionDeclsST.add($functionDecl.st);}
		  | returnStat {$stringTemplates.add($returnStat.st);}
		  ;
		  
echo : ^(ECHO e=expr {
					Type type = $expr.start.getStaticType();
					setStackLimit($expr.stackSize +1);
				}) 
			 -> {!(type instanceof StructType)}? echo(expression={$e.st},type={type.toString()})
			 -> echoObject(expression={$e.st},type={type})
			 ;
block	returns [List<Object> structDeclST, List<Object> functionDeclsST, List<Object> stringTemplates]
			scope BlockLocals;
			@init{
				$BlockLocals::locals = new LinkedList<>();
				$BlockLocals::isFunctionBlock = false;
				
				$structDeclST = new LinkedList<>();
				$functionDeclsST = new LinkedList<>();
				$stringTemplates = new LinkedList<>();
			}
			:
				^(BLOCK (s+=stat{
					$structDeclST.addAll(((stat_return)s).structDeclST);
					$functionDeclsST.addAll(((stat_return)s).functionDeclsST);
					$stringTemplates.addAll(((stat_return)s).stringTemplates);
				})+)
			;

functionDecl 
						scope BlockLocals;
						scope FunctionStack;
						@init{
							$BlockLocals::locals = new LinkedList<>();
							$BlockLocals::isFunctionBlock = true;
							
							List<Object> stringTemplates = new LinkedList<>();
						}
						:
						^(FUNCDECL returnFunctionDecl ID parametersDecl? (stat{stringTemplates.addAll($stat.stringTemplates);})*)
						-> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}?
							 functionDeclVoid(visibility={isTopLevel($start) ? "public" : " "}, returnType={$returnFunctionDecl.st},
															id={$ID.text}, parameters={$parametersDecl.st}, stmts={stringTemplates},
															stackLimit={$FunctionStack::stackLimit}, localsLimit={$BlockLocals::localsLimit})
						-> functionDecl(visibility={isTopLevel($start) ? "public" : " "}, returnType={$returnFunctionDecl.st},
															id={$ID.text}, parameters={$parametersDecl.st}, stmts={stringTemplates},
															stackLimit={$FunctionStack::stackLimit}, localsLimit={$BlockLocals::localsLimit})
						;

returnFunctionDecl :
										 'int' -> type(type={PrimType.INT})
									 | 'bool' -> type(type={PrimType.BOOL})
									 | 'void' -> type(type={PrimType.VOID})
									 | ID -> type(type={"L"+packageDst+"/"+$ID.text+";"})
									 ;

returnStat :
					'return' -> returnStat(stat={"return"})
					| ^('return' expr {setStackLimit($expr.stackSize);}) 
							-> {$expr.start.getStaticType().equalsTo(PrimType.INT) 
											|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}?
							   returnStat(stat={"ireturn"}, expr={$expr.st})
							-> returnStat(stat={"areturn"}, expr={$expr.st})
					;

parametersDecl : 
							 ^(PDECS	p+=parameterDecl+) -> parametersDecl(parameters={$p})
							 ;

parameterDecl	
							@init{
								String pid = "";
							}
							@after{
								addVariableToLocals(pid);
							}
							:
							^('int' ID {pid = $ID.text;}) -> parameter(parameter={"I"})
							|^('bool' ID {pid = $ID.text;}) -> parameter(parameter={"Z"})
							|^('struct' structType=ID structID=ID {pid = $structID.text;})
								-> parameter(parameter={"L"+packageDst+"/"+$structType.text+";"})
							;		

structDecl 
					 @init{
					 		String visibility;
					 }
						:{visibility = isTopLevel($start) ? "public" : "";}
					 ^('struct' ID sm+=structMember[visibility]+{
					 	StructType structType = dataTypes.get($ID.text);
					 }) 
					 -> structDecl(id={$ID.text}, visibility={visibility}, packageDst={packageDst},
					 							constructor={structType.buildConstructor(visibility, packageDst)},
					 							member={$sm}, toStringMethod={structType.buildToString(packageDst)},
					 							equalsMethod={structType.buildEquals(packageDst)})
					 ;

structMember[String visibility] :
						^('int' ID) -> structMember(type={"I"}, id={$ID.text}, visibility={$visibility})
						| ^('bool' id2=ID) -> structMember(type={"Z"}, id={$ID.text}, visibility={$visibility})
						| ^('struct' structType=ID structID=ID) 
							-> structMember(type={dataTypes.get($structType.text).getObjectType(packageDst)},
								 id={$structID.text}, visibility={$visibility})
						 ;	

expr returns [int stackSize]
				:
			  ^('^' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize, 3);})
			  -> pow(leftExpr={$t1.st},rightExpr={$t2.st})
			  
				| ^('+' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
				  -> add(leftExpr={$t1.st},rightExpr={$t2.st})
				  
				| ^('-' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
					-> sub(leftExpr={$t1.st},rightExpr={$t2.st})
					
				| ^('%' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
					-> remainder(leftExpr={$t1.st},rightExpr={$t2.st})
					
				| ^('*' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
				  -> mul(leftExpr={$t1.st},rightExpr={$t2.st})
				  
				| ^('/' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
				  -> div(leftExpr={$t1.st},rightExpr={$t2.st})
				  
			  | ^('!' t=expr { $stackSize = t.stackSize;})
			  	-> fact(expr={$t.st}, packageDst={packageDst})
			  	
				| ^('~' t=expr { $stackSize = t.stackSize;})
					-> not(expr={$t.st}, tokenNum={$start.getIndex()}, packageDst={packageDst})
				
			  | ^(PLUS t=expr { $stackSize = t.stackSize;})
			    -> unaryPlus(expr={$t.st})
			    
			  | ^(SIGN t=expr { $stackSize = t.stackSize;})
			    -> unaryMinus(expr={$t.st})
			    
				| ^('<' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
					-> less(leftExpr={$t1.st},rightExpr={$t2.st}, tokenNum={$start.getIndex()})
				
				| ^('<=' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
				  -> lessEqual(leftExpr={$t1.st},rightExpr={$t2.st}, tokenNum={$start.getIndex()})
				
				| ^('>' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
				  -> greater(leftExpr={$t1.st},rightExpr={$t2.st}, tokenNum={$start.getIndex()})
				
				| ^('>=' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
				  -> greaterEqual(leftExpr={$t1.st},rightExpr={$t2.st}, tokenNum={$start.getIndex()})
				
				| equals {$expr.st = $equals.st; $stackSize = $equals.stackSize;}
				
				| ^('&&' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
					-> and(leftExpr={$t1.st},rightExpr={$t2.st}, tokenNum={$start.getIndex()})
				
				| ^('||' t1=expr t2=expr { $stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);})
					-> or(leftExpr={$t1.st},rightExpr={$t2.st}, tokenNum={$start.getIndex()})
				
				| ^(DOT {Type type = null;}
						(functionCall {type = $functionCall.start.getStaticType(); $stackSize = $functionCall.stackSize;}
						| structValue {type = $structValue.start.getStaticType(); $stackSize = $functionCall.stackSize;}) 
						qualifiedID	{$stackSize =  Math.max($stackSize,1);}) 
						-> oneTimeAccess(function={$functionCall.st}, structValue={$structValue.st}, 
														 type={type}, qid={$qualifiedID.st})
														 
				| functionCall {$expr.st = $functionCall.st; $stackSize = $functionCall.stackSize;}
				
				| structValue {$expr.st = $structValue.st; $stackSize = $structValue.stackSize;}
				
				| qualifiedID {$expr.st = $qualifiedID.st; $stackSize = 1;}
				
			  | INT {$stackSize = 1;}
			  	-> constant(value={$INT.text})
				| BOOLEAN {$stackSize = 1;}
					-> constant(value={$BOOLEAN.text.equals("true") ? "1" : "0"})
        ;
        
equals returns [int stackSize]:
			 ^('==' t1=expr t2=expr
			 	{ boolean areStructs = $t1.start.getStaticType() instanceof StructType;
			 		$stackSize = calculateStackLimit($t1.stackSize, $t2.stackSize);			 								 
			 	}
			 ) 
			 -> {areStructs}?
					equalsStruct(leftExpr = {$t1.st}, rightExpr = {$t2.st})
			 -> equals(leftExpr = {$t1.st}, rightExpr = {$t2.st}, tokenNum={$start.getIndex()})
			 ;
			
functionCall returns[int stackSize]
						:
						^(FUNCCALL ID  (e+=expr{$stackSize += ((expr_return)e).stackSize;})*{
							FunctionType functionType = api.get($ID.text);
							Type returnType = functionType.getReturnType();
							if(!returnType.equalsTo(PrimType.VOID)){
								$stackSize += 1;
							}
							String returnTypeRappresentation = returnType.getObjectType(packageDst);
						})
						-> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}?
							 functionCall(package={packageDst}, fid={$ID.text},
							 							returnType={returnTypeRappresentation},
							 							signature={functionType.getParametersSignature(packageDst)},expr={$e})
						-> functionCallAndPop(package={packageDst}, fid={$ID.text},
														returnType={returnTypeRappresentation},
														signature={functionType.getParametersSignature(packageDst)},expr={$e})
						;

structValue returns[int stackSize]
						@after{
							$stackSize = $stackSize+2;
						}
						:
						 ^('struct' ID (e+=expr{$stackSize += ((expr_return)e).stackSize;})+)
						 -> structValue(id={$ID.text}, packageDst={packageDst}, 
						 		constructorSignature={dataTypes.get($ID.text).getFieldsSignature(packageDst)}, expr={$e})
						;
				
qualifiedID :
						id {$qualifiedID.st = $id.st;}
						| ^(DOT	id qid=qualifiedID)
							-> {$start.parent.token.getType() != DOT}?
							qualifiedIDAndLoad(qualifiedID={$qid.st}, localNum={getVariableLocalNumber($id.text)})
							-> qualifiedID(id = {$id.st}, qualifiedID={$qid.st})
						;
						
id returns [String text] : ID {$text = $ID.text;}{
							if($start.parent.token.getType() != DOT && !isDeclared($id.text)){
								addVariableToLocals($ID.text);
							}
							boolean isRootDOT = $start.parent.token.getType() == DOT,
											isLeftValue = isLeftValue($start);
											
							Type siblingType = null, type = $start.getStaticType();
							if(isRootDOT){
								siblingType = $start.childIndex == 0 ?
															((TypeTree)$start.parent.parent.getChild(0)).getStaticType() :
															((TypeTree)$start.parent.getChild(0)).getStaticType();
							}
						} -> {!isRootDOT && isLeftValue}?
								 store(store={type.getStorer()}, localNum={getVariableLocalNumber($ID.text)})
							-> {!isRootDOT}?
								 load(load={type.getLoader()}, localNum={getVariableLocalNumber($ID.text)})
							-> {isLeftValue}?
								 putField(field={$ID.text}, struct={siblingType},	type={type.getObjectType(packageDst)})
							-> getField(field={$ID.text}, struct={siblingType},	type={type.getObjectType(packageDst)})
	 ;