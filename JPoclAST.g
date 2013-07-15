grammar JPoclAST;

options {
   output = AST;
   ASTLabelType=TypeTree;
}

tokens{
PLUS;SIGN;BLOCK;STRUCTEQUAL;STRUCTVALUES;
FUNCDECL;FUNCCALL;PDECS;
ASSIGN;CALC;
}

@header{
package gen;

import ast.TypeTree;
}
@lexer::header{
package gen;
}
@members{
	boolean insideFunctionDecl = false;
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

calc 	: stmt+ -> ^(CALC stmt+)
	;
			
stmt : NL ->
		 | ECHO instr NL -> ^(ECHO instr)
		 | qualifiedID tk='=' bexpr0 NL -> ^(ASSIGN[$tk,$tk.text] bexpr0 qualifiedID)
		 | functionCall		 
		 | 'if'^ '('! bexpr0 ')'! stmt
		 | 'while'^ '('! bexpr0 ')'! stmt
		 | block
		 | structDecl
		 | functionDecl
		 | returnStmt
		 ;

block : tk='{' stmt+ '}' NL -> ^(BLOCK[$tk,$tk.text] stmt+);

functionDecl: {!insideFunctionDecl}? =>
						  {insideFunctionDecl = true;}
						  returnFunctionDecl ID '(' parametersDecl? ')''{'
							stmt*
							'}'NL
							{insideFunctionDecl = false;}
							-> ^(FUNCDECL returnFunctionDecl ID parametersDecl? stmt*)
						;

returnFunctionDecl : 'int'
									 | 'bool'
									 | 'void'
									 | 'struct'! ID^
									 ;
		
returnStmt : {insideFunctionDecl}? => 'return' bexpr0? NL-> ^('return' bexpr0?);

parametersDecl : parameterDecl (',' parameterDecl)*
								 -> ^(PDECS	parameterDecl+)
							;

parameterDecl :	'int' ID -> ^('int' ID)
							| 'bool' ID -> ^('bool' ID)
							| 'struct' structType=ID structID=ID -> ^('struct' $structType $structID)
							;

structDecl : 'struct' ID '{'structMember+'}' -> ^('struct' ID structMember+)
					 ;

structMember : 'int' ID ';' -> ^('int' ID)
						 | 'bool' ID ';' -> ^('bool' ID)
						 | 'struct' structType=ID structID=ID ';' -> ^('struct' $structType $structID)
						 ;

instr   : bexpr0
				;

bexpr0 	: bexpr1 ('||'^ bexpr1 )*	
	;
	
bexpr1 	: bexpr2 ('&&'^ bexpr2 )*	
	;
		
bexpr2 	: bexpr3 ('=='^ bexpr3 )*	
	;

bexpr3 	: expr0 (('<'|'<='|'>'|'>=')^ expr0)*	
	;
			
expr0 	: expr1 (('+' | '-')^ expr1)*	
	;
	
expr1 	: expr2 ( ('*' | '/' |'%')^ expr2)*
	;

expr2	: tk='+' expr2 -> ^(PLUS[$tk,"plus"] expr2) | tk='-' expr2 -> ^(SIGN[$tk,"sign"] expr2) | expr3	 
	;

expr2b	: tk='+' expr2b -> ^(PLUS[$tk,"plus"] expr2b) | tk='-' expr2b -> ^(SIGN[$tk,"sign"] expr2b) | expr4	 
	;
	
expr3	: expr4 '!'^* 
	;

expr4	: (expr5 -> expr5) (('!' -> ^('!' $expr4))* '^' expr2b -> ^('^' $expr4 expr2b))?
	;

expr5 : INT 
			 | BOOLEAN 
			 | '('! bexpr0^ ')'!
			 | '~'^ expr5
			 | functionCall
			 | structValue
			 | qualifiedID
			 ;

functionCall : (ID'(' parameters? ')' -> ^(FUNCCALL ID parameters?))
							(DOT qualifiedID -> ^(DOT $functionCall qualifiedID))?
						 ;

parameters : bexpr0 (',' bexpr0)* -> bexpr0+
					 ;

structValue : ('struct' ID '{' bexpr0 (',' bexpr0)*'}' -> ^('struct' ID bexpr0+))
							(DOT qualifiedID -> ^(DOT $structValue qualifiedID))?
						;
						
qualifiedID : ID
						| ID DOT qualifiedID -> ^(DOT ID qualifiedID)
						;

BOOLEAN : 'false' | 'true';

ECHO : 'echo';

ID:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT :	'0'..'9'+
    ;

WS  :   ( ' '
        | '\t'
        ) {skip();}
    ;

NL 	: '\r'? '\n' | '\r'
	;
DOT : '.';