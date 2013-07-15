tree grammar JPoclTypedAST;

options {
   tokenVocab=JPoclAST; 
   ASTLabelType=TypeTree;
}

scope Scope{
	Map<String,Type> env;
	Map<String,FunctionType> functionEnv;
	Map<String,StructType> structEnv;
	boolean isFunctionBlock;
}

@header {
package gen;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Iterator;
import static type.ErrorMessage.*;

import type.*;
import ast.*;
}

@members {
   LinkedHashMap<String, Type> env = new LinkedHashMap<>();
   LinkedHashMap<String, FunctionType> functionEnv = new LinkedHashMap<>();
   LinkedHashMap<String, StructType> structEnv = new LinkedHashMap<>();
   
   private void raiseTypeException(ErrorMessage e, int line, int column, Object... args){
     throw new TypeException(e.buildMessage(line, column, args));
   }
   
   private void raiseTypeException(ErrorMessage e, int line, int column){
     throw new TypeException(e.buildMessage(line, column));
   }
   
   private boolean isFunctionAlreadyDeclared(String fid){
     for(String currentFid : functionEnv.keySet()){
   	   if(currentFid.equals(fid))
    	   return true;
     }
     return false;
   }
       
   private boolean isStructAlreadyDeclared(String sid){
     for(String currentSid : structEnv.keySet()){
       if(currentSid.equals(sid))
    	   return true;
     }
     return false;
   }
       
   private void addFunctionDeclarationToGlobalScope(String fid, FunctionType type){
     functionEnv.put(fid, type);
   }
      
   private void addStructDeclarationToGlobalScope(String sid, StructType type){
     structEnv.put(sid, type);
   }
   
   private void checkEchoStatement(Type t, Tree tree){
   		if(t.equalsTo(PrimType.VOID))
   			raiseTypeException(VOID_EXPRESSION, tree.getLine(), tree.getCharPositionInLine());
   }
   
   private void check(Type t1,Type t2, Tree tree) {
   		if(t1.equalsTo(PrimType.VOID) || t2.equalsTo(PrimType.VOID))
						raiseTypeException(VOID_EXPRESSION, tree.getLine(), tree.getCharPositionInLine());
      if(!t1.equalsTo(t2))
						raiseTypeException(INCOMPATIBLE_TYPES, 
															 tree.getLine(), tree.getCharPositionInLine(),
															 t1.getId(), t2.getId());
   }
    
    private void checkFunctionReturn(Tree tree, Type returnType){
    	Tree currentTree = tree;
    	while(currentTree.getType() != FUNCDECL){
    		currentTree = currentTree.getParent();
    	}
    	Type functionReturnType = ((TypeTree)currentTree).getStaticType();
    	String fid = currentTree.getChild(1).getText();
    	if(!functionReturnType.equalsTo(returnType))
    		raiseTypeException(INVALID_RETURN, 
    												tree.getLine(), tree.getCharPositionInLine(),
    												fid, functionReturnType.getId(), returnType.getId());
    }
    
    private void checkFunctionParameters(FunctionType functionType, List<Type> types, String fid, Tree tree){
        if(types.size()!=functionType.getParameters().size())
	    		raiseTypeException(FUNCTION_CALL, 
	    												tree.getLine(), tree.getCharPositionInLine(),
	    												fid);
	    												
        int i = 0;
        for (Type type : functionType.getParameters()) {
        	if(!type.equalsTo(types.get(i)))
		    		raiseTypeException(FUNCTION_CALL, 
	    												tree.getLine(), tree.getCharPositionInLine(),
	    												fid);
					i++;
        }
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    public Type isFieldDefined(String id, Type type){
    	if(!(type instanceof StructType))
    		return null;
   		StructType structType = (StructType) type;
   		return structType.getType(id);
    }
   
		public Type isIDVisible(String id){
  			for(int s = $Scope.size()-1; s>=0; s--){
   				if($Scope[s]::env.containsKey(id)) {
   					return $Scope[s]::env.get(id);
   				}
		  		if($Scope::isFunctionBlock)
		  			break;
   			}
    		return null;       	
    }
    
    public FunctionType isFunctionIDVisible(String fid){
  		for(int s = $Scope.size()-1; s>=0; s--){
   			if($Scope[s]::functionEnv.containsKey(fid)) {
   				FunctionType type = $Scope[s]::functionEnv.get(fid);
   				if(type != null)
   					return type;
   			}
   		}
    	return null;       	
    }
    
    public StructType isStructIDVisible(String sid){
   		for(int s = $Scope.size()-1; s>=0; s--){
   			if($Scope[s]::structEnv.containsKey(sid)) {
   				StructType type = $Scope[s]::structEnv.get(sid);
   				if(type != null)
   					return type;
   			}
   		}
	   	return null;
    }
    
	  @Override
	  public void reportError(RecognitionException e) {
 	    throw new RuntimeException("Syntax error.\n");
	  }
}

@rulecatch {
catch (RecognitionException re) {
	reportError(re);
}
}

calc[LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes]
		returns [LinkedHashMap<String,FunctionType>_api, LinkedHashMap<String,StructType> _dataTypes]
		scope Scope;
		@init{
			$Scope::env = new LinkedHashMap<>();
			$Scope::functionEnv = new LinkedHashMap<>();
			$Scope::structEnv = new LinkedHashMap<>();
			
			for(FunctionType functionType : $api.values()){
				$Scope::functionEnv.put(functionType.getId(),functionType);
				addFunctionDeclarationToGlobalScope(functionType.getId(),functionType);
			}
			
			for(StructType structType : $dataTypes.values()){
				$Scope::structEnv.put(structType.getId(),structType);
				addStructDeclarationToGlobalScope(structType.getId(),structType);
			}
			
			$Scope::isFunctionBlock = true;
		}
		@after{
			$_api = functionEnv;
			$_dataTypes = structEnv;
		}
		:
		^(CALC stat+)
		;


stat returns[boolean isReturnStat, Type type]:
		  ^(ECHO e=expr){
		  	checkEchoStatement($e.type, $start);
		  }
			|^(ASSIGN expr qualifiedID){
				if($expr.type == PrimType.VOID){
   				raiseTypeException(VOID_EXPRESSION, 
   					$start.getLine(), $start.getCharPositionInLine());
				}
				check($qualifiedID.type,$expr.type, $start);
			}
			| functionCall
      |^('if' guardType=expr stat){
				check($guardType.type,PrimType.BOOL, $start);
      }
      |^('while' guardType=expr stat){
				check($guardType.type,PrimType.BOOL, $start);
      }
      | block
		  | structDecl
		  | functionDecl
		  | returnStat {$isReturnStat=true;}
		  ;
block scope Scope;
			@init{
				$Scope::env = new HashMap<>();
				$Scope::functionEnv = new HashMap<>();
				$Scope::structEnv = new HashMap<>();
				
				$Scope::isFunctionBlock = false;
			}
			: 
				^(BLOCK stat+)
			;


functionDecl scope Scope;
			@init{
				$Scope::env = new HashMap<>();
				$Scope::functionEnv = new HashMap<>();
				$Scope::structEnv = new HashMap<>();
				$Scope::isFunctionBlock = true;
				
				boolean hasReturnStat = false, hasBody = false;				
			}
			: ^(FUNCDECL rtn=returnFunctionDecl ID fDecl=parametersDecl? 
			{
				if(isFunctionAlreadyDeclared($ID.text))
					raiseTypeException(FUNCTION_REDECLARATION, 
   					$start.getLine(), $start.getCharPositionInLine(), $ID.text);
   					
				FunctionType type = new FunctionType($ID.text,
															$rtn.type,
															$fDecl.dec == null ? new FunctionTypeDec() : $fDecl.dec);
				int outerScopeLevel = $Scope.size()-2;
				$Scope[outerScopeLevel]::functionEnv.put($ID.text,type);
				addFunctionDeclarationToGlobalScope($ID.text,type);
				$start.setStaticType($rtn.type);
			}
					(stat {
						hasBody = true;
						if($stat.isReturnStat && hasReturnStat){
							raiseTypeException(UNREACHBLE_CODE, 
		   					$start.getLine(), $start.getCharPositionInLine());
						}
						if($stat.isReturnStat){
							hasReturnStat = true;
						}})*)
			{
				if(!hasReturnStat && !(PrimType.VOID.equalsTo($rtn.type)))
						raiseTypeException(RETURN_MISSING, 
		   					$start.getLine(), $start.getCharPositionInLine(), 
		   					$ID.text, $rtn.text);
				/*if(!hasBody)
					throw new TypeException("Empty function");*/
			}
			;

returnFunctionDecl returns [Type type]:
										 'int' {$type = PrimType.INT;}
									 | 'bool' {$type = PrimType.BOOL;}
									 | 'void' {$type = PrimType.VOID;}
									 | ID {
									 	if(($type = isStructIDVisible($ID.text)) == null){
											raiseTypeException(TYPE_UNRESOLVED, 
							   					$start.getLine(), $start.getCharPositionInLine(), 
							   					$ID.text);
							   		}
									 }
									 ;

returnStat :
					'return' {checkFunctionReturn($start, PrimType.VOID);}
					| ^('return' expr) {checkFunctionReturn($start, $expr.type);}
					;

parametersDecl returns [FunctionTypeDec dec]
							: 
							{$dec = new FunctionTypeDec();}
							^(PDECS	parameterDecl[dec]+)
							;

parameterDecl [FunctionTypeDec dec]
							:
							^('int' intID=ID) {
								if(!$dec.addParameter($intID.text,PrimType.INT))
									raiseTypeException(DUPLICATED_PARAMETER, 
							   		$start.getLine(), $start.getCharPositionInLine(), 
							   		$intID.text);
								$Scope::env.put($intID.text,PrimType.INT);
							}
							|^('bool' boolID=ID) {
								if(!$dec.addParameter($boolID.text,PrimType.BOOL))
									raiseTypeException(DUPLICATED_PARAMETER, 
							   		$start.getLine(), $start.getCharPositionInLine(), 
							   		$boolID.text);								
								$Scope::env.put($boolID.text,PrimType.BOOL);
							}
							|^('struct' id0=ID id1=ID) {
								StructType type;
								if((type = isStructIDVisible($id0.text)) == null)
									raiseTypeException(TYPE_UNRESOLVED, 
							   		$start.getLine(), $start.getCharPositionInLine(), 
							   		$id0.text);
								if(!$dec.addParameter($id1.text,type))
									raiseTypeException(DUPLICATED_PARAMETER, 
							   		$start.getLine(), $start.getCharPositionInLine(), 
							   		$id1.text);		
								$Scope::env.put($id1.text,type);
							}
							;		

structDecl :
					 ^('struct' ID 
					 {
					 	if(isStructAlreadyDeclared($ID.text))
							raiseTypeException(STRUCT_REDECLARATION, 
								$start.getLine(), $start.getCharPositionInLine(), 
							  $ID.text);

					 	StructTypeDec structTypeDec = new StructTypeDec();
					 	StructType structType = new StructType($ID.text,structTypeDec);
					 }
					 structMember[structTypeDec, structType]+)
					 {
						 $Scope::structEnv.put($ID.text,structType);
						 addStructDeclarationToGlobalScope($ID.text,structType);
					 }
					 ;

structMember [StructTypeDec structTypeDec, StructType declaringStructType] :
						^('int' id1=ID) {
						 if(!structTypeDec.addField($ID.text, PrimType.INT))
						 		raiseTypeException(DUPLICATED_FIELD, 
									$start.getLine(), $start.getCharPositionInLine(), 
							  	$id1.text);
						 
						}
						| ^('bool' id2=ID) {
						 if(!structTypeDec.addField($ID.text, PrimType.BOOL))
						 		raiseTypeException(DUPLICATED_FIELD, 
									$start.getLine(), $start.getCharPositionInLine(), 
							  	$id2.text);
						}
						| ^('struct' structType=ID structID=ID) { 
	  					Type type;
	  					if($structType.text.equals(declaringStructType.getId()))
	  						type = declaringStructType;
							else if((type = isStructIDVisible($structType.text)) == null)
									raiseTypeException(TYPE_UNRESOLVED, 
							   		$start.getLine(), $start.getCharPositionInLine(), 
							   		$structType.text);
							if(!structTypeDec.addField($structID.text,type))
						 		raiseTypeException(DUPLICATED_FIELD, 
									$start.getLine(), $start.getCharPositionInLine(), 
							  	$structID.text);							
						 }
						 ;	

expr returns [Type type] 
				@after{
					$start.setStaticType($type);
				}:
			  ^(('^'| '+' | '-' | '%' | '*' | '/') t1=expr t2=expr) {
					check($t1.type,PrimType.INT,$start); 
					check($t2.type,PrimType.INT,$start); 
					$type = PrimType.INT;
				} 
			  | ^(('!' | PLUS | SIGN) t=expr) {
			  	check($t.type,PrimType.INT,$start); 
					$type = PrimType.INT;
				} 
				| ^(('<' | '<='|'>'|'>=') t1=expr t2=expr) {
					check($t1.type,PrimType.INT,$start); 
					check($t2.type,PrimType.INT,$start); 
					$type = PrimType.BOOL;
				} 
				| ^('==' t1=expr t2=expr) {
					check($t1.type,$t2.type,$start); 
					$type = PrimType.BOOL;
				} 
				| ^(('&&' | '||') t1=expr t2=expr) {
					check($t1.type,PrimType.BOOL,$start); 
					check($t2.type,PrimType.BOOL,$start); 
					$type = PrimType.BOOL;
				}
				| ^(DOT(functionCall {$start.setStaticType($functionCall.type);}
								| structValue	{$start.setStaticType($structValue.type);})
						qualifiedID {$type = $qualifiedID.type;})
				| functionCall {
					$type = $functionCall.type;
				}
				| structValue {
					$type = $structValue.type;
				}
				| qualifiedID {
					$type = $qualifiedID.type;
				}
			  | INT {
			  	$type = PrimType.INT;
			  } 
				| BOOLEAN {
					$type = PrimType.BOOL;
				}
				| ^('~' t=expr) {
				  check($t.type,PrimType.BOOL,$start); 
				  $type = PrimType.BOOL;
			  }  
        ;
			
functionCall returns [Type type]
						@init{
							List<Type> parametersType = new LinkedList<>();
						}:
						^(FUNCCALL ID  (e=expr{parametersType.add($e.type);})*){
							FunctionType functionType;
							if((functionType = isFunctionIDVisible($ID.text)) == null)
								raiseTypeException(FUNCTION_UNDEFINED, 
						   		$start.getLine(), $start.getCharPositionInLine(), 
						   		$ID.text);
							checkFunctionParameters(functionType, parametersType, $ID.text, $start);
							$type = functionType.getReturnType();
							$start.setStaticType($type);
						}
						;

structValue returns [Type type]:
						 ^('struct' ID {
								 StructType t;
								 if((t = isStructIDVisible($ID.text)) == null){
										raiseTypeException(TYPE_UNRESOLVED, 
							   			$start.getLine(), $start.getCharPositionInLine(), 
							   			$ID.text);
								 }
								 Iterator<Type> it = t.types().iterator();
								 Type field;
							 } (expr {field = it.next(); check(field,$expr.type,$start);})+
						 )
						 {$type = t; $start.setStaticType(t);}
						;
				
qualifiedID returns [Type type]: 
						id {
							if($start.parent.token.getType() == DOT){
								Type outerType = ((TypeTree)$start.parent).getStaticType();
								$type = isFieldDefined($id.text, outerType);
							}else{
								$type = isIDVisible($id.text);
								if($type == null && $start.parent.token.getType() == ASSIGN){
									$type = ((TypeTree)$start.parent.getChild(0)).getStaticType();
									$Scope::env.put($id.text,$type);
								}
							}
							if($type == null)
								raiseTypeException(ID_FIELD_UNRESOLVED, 
									$start.getLine(), $start.getCharPositionInLine(), 
							   	$id.text);
							$id.start.setStaticType($type);
						}
						| ^(DOT	id{
									if($start.parent.token.getType() == DOT){
										Type outerType = ((TypeTree)$start.parent).getStaticType();
										$type = isFieldDefined($id.text, outerType);
									}else{
										$type = isIDVisible($id.text);
									}
									if($type == null)
										raiseTypeException(ID_FIELD_UNRESOLVED, 
							   			$start.getLine(), $start.getCharPositionInLine(), 
							   			$id.text);
									$start.setStaticType($type);
									$id.start.setStaticType($type);
								}
								innerQID = qualifiedID{
									$type = $innerQID.type;
									$start.setStaticType($type);
								}
							)
						;
id: ID
	;