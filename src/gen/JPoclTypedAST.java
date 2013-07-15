// $ANTLR 3.4 C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g 2013-07-15 11:46:46

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


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class JPoclTypedAST extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGN", "BLOCK", "BOOLEAN", "CALC", "DOT", "ECHO", "FUNCCALL", "FUNCDECL", "ID", "INT", "NL", "PDECS", "PLUS", "SIGN", "STRUCTEQUAL", "STRUCTVALUES", "WS", "'!'", "'%'", "'&&'", "'('", "')'", "'*'", "'+'", "','", "'-'", "'/'", "';'", "'<'", "'<='", "'='", "'=='", "'>'", "'>='", "'^'", "'bool'", "'if'", "'int'", "'return'", "'struct'", "'void'", "'while'", "'{'", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int ASSIGN=4;
    public static final int BLOCK=5;
    public static final int BOOLEAN=6;
    public static final int CALC=7;
    public static final int DOT=8;
    public static final int ECHO=9;
    public static final int FUNCCALL=10;
    public static final int FUNCDECL=11;
    public static final int ID=12;
    public static final int INT=13;
    public static final int NL=14;
    public static final int PDECS=15;
    public static final int PLUS=16;
    public static final int SIGN=17;
    public static final int STRUCTEQUAL=18;
    public static final int STRUCTVALUES=19;
    public static final int WS=20;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators

    protected static class Scope_scope {
        Map<String,Type> env;
        Map<String,FunctionType> functionEnv;
        Map<String,StructType> structEnv;
        boolean isFunctionBlock;
    }
    protected Stack Scope_stack = new Stack();



    public JPoclTypedAST(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public JPoclTypedAST(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return JPoclTypedAST.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g"; }


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
      			for(int s = Scope_stack.size()-1; s>=0; s--){
       				if(((Scope_scope)Scope_stack.elementAt(s)).env.containsKey(id)) {
       					return ((Scope_scope)Scope_stack.elementAt(s)).env.get(id);
       				}
    		  		if(((Scope_scope)Scope_stack.peek()).isFunctionBlock)
    		  			break;
       			}
        		return null;       	
        }
        
        public FunctionType isFunctionIDVisible(String fid){
      		for(int s = Scope_stack.size()-1; s>=0; s--){
       			if(((Scope_scope)Scope_stack.elementAt(s)).functionEnv.containsKey(fid)) {
       				FunctionType type = ((Scope_scope)Scope_stack.elementAt(s)).functionEnv.get(fid);
       				if(type != null)
       					return type;
       			}
       		}
        	return null;       	
        }
        
        public StructType isStructIDVisible(String sid){
       		for(int s = Scope_stack.size()-1; s>=0; s--){
       			if(((Scope_scope)Scope_stack.elementAt(s)).structEnv.containsKey(sid)) {
       				StructType type = ((Scope_scope)Scope_stack.elementAt(s)).structEnv.get(sid);
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


    public static class calc_return extends TreeRuleReturnScope {
        public LinkedHashMap<String,FunctionType>_api;
        public LinkedHashMap<String,StructType> _dataTypes;
    };


    // $ANTLR start "calc"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:162:1: calc[LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes] returns [LinkedHashMap<String,FunctionType>_api, LinkedHashMap<String,StructType> _dataTypes] : ^( CALC ( stat )+ ) ;
    public final JPoclTypedAST.calc_return calc(LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes) throws RecognitionException {
        Scope_stack.push(new Scope_scope());

        JPoclTypedAST.calc_return retval = new JPoclTypedAST.calc_return();
        retval.start = input.LT(1);



        			((Scope_scope)Scope_stack.peek()).env = new LinkedHashMap<>();
        			((Scope_scope)Scope_stack.peek()).functionEnv = new LinkedHashMap<>();
        			((Scope_scope)Scope_stack.peek()).structEnv = new LinkedHashMap<>();
        			
        			for(FunctionType functionType : api.values()){
        				((Scope_scope)Scope_stack.peek()).functionEnv.put(functionType.getId(),functionType);
        				addFunctionDeclarationToGlobalScope(functionType.getId(),functionType);
        			}
        			
        			for(StructType structType : dataTypes.values()){
        				((Scope_scope)Scope_stack.peek()).structEnv.put(structType.getId(),structType);
        				addStructDeclarationToGlobalScope(structType.getId(),structType);
        			}
        			
        			((Scope_scope)Scope_stack.peek()).isFunctionBlock = true;
        		
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:186:3: ( ^( CALC ( stat )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:187:3: ^( CALC ( stat )+ )
            {
            match(input,CALC,FOLLOW_CALC_in_calc89); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:187:10: ( stat )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= ASSIGN && LA1_0 <= BLOCK)||(LA1_0 >= ECHO && LA1_0 <= FUNCDECL)||LA1_0==40||(LA1_0 >= 42 && LA1_0 <= 43)||LA1_0==45) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:187:10: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_calc91);
            	    stat();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            match(input, Token.UP, null); 


            }


            			retval._api = functionEnv;
            			retval._dataTypes = structEnv;
            		
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
            Scope_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "calc"


    public static class stat_return extends TreeRuleReturnScope {
        public boolean isReturnStat;
        public Type type;
    };


    // $ANTLR start "stat"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:191:1: stat returns [boolean isReturnStat, Type type] : ( ^( ECHO e= expr ) | ^( ASSIGN expr qualifiedID ) | functionCall | ^( 'if' guardType= expr stat ) | ^( 'while' guardType= expr stat ) | block | structDecl | functionDecl | returnStat );
    public final JPoclTypedAST.stat_return stat() throws RecognitionException {
        JPoclTypedAST.stat_return retval = new JPoclTypedAST.stat_return();
        retval.start = input.LT(1);


        JPoclTypedAST.expr_return e =null;

        JPoclTypedAST.expr_return guardType =null;

        JPoclTypedAST.expr_return expr1 =null;

        JPoclTypedAST.qualifiedID_return qualifiedID2 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:191:46: ( ^( ECHO e= expr ) | ^( ASSIGN expr qualifiedID ) | functionCall | ^( 'if' guardType= expr stat ) | ^( 'while' guardType= expr stat ) | block | structDecl | functionDecl | returnStat )
            int alt2=9;
            switch ( input.LA(1) ) {
            case ECHO:
                {
                alt2=1;
                }
                break;
            case ASSIGN:
                {
                alt2=2;
                }
                break;
            case FUNCCALL:
                {
                alt2=3;
                }
                break;
            case 40:
                {
                alt2=4;
                }
                break;
            case 45:
                {
                alt2=5;
                }
                break;
            case BLOCK:
                {
                alt2=6;
                }
                break;
            case 43:
                {
                alt2=7;
                }
                break;
            case FUNCDECL:
                {
                alt2=8;
                }
                break;
            case 42:
                {
                alt2=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:192:5: ^( ECHO e= expr )
                    {
                    match(input,ECHO,FOLLOW_ECHO_in_stat112); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat116);
                    e=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    		  	checkEchoStatement((e!=null?e.type:null), ((TypeTree)retval.start));
                    		  

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:195:5: ^( ASSIGN expr qualifiedID )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_stat125); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat127);
                    expr1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_qualifiedID_in_stat129);
                    qualifiedID2=qualifiedID();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    				if((expr1!=null?expr1.type:null) == PrimType.VOID){
                       				raiseTypeException(VOID_EXPRESSION, 
                       					((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine());
                    				}
                    				check((qualifiedID2!=null?qualifiedID2.type:null),(expr1!=null?expr1.type:null), ((TypeTree)retval.start));
                    			

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:202:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_stat138);
                    functionCall();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:203:8: ^( 'if' guardType= expr stat )
                    {
                    match(input,40,FOLLOW_40_in_stat148); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat152);
                    guardType=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_stat_in_stat154);
                    stat();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    				check((guardType!=null?guardType.type:null),PrimType.BOOL, ((TypeTree)retval.start));
                          

                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:206:8: ^( 'while' guardType= expr stat )
                    {
                    match(input,45,FOLLOW_45_in_stat166); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat170);
                    guardType=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_stat_in_stat172);
                    stat();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    				check((guardType!=null?guardType.type:null),PrimType.BOOL, ((TypeTree)retval.start));
                          

                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:209:9: block
                    {
                    pushFollow(FOLLOW_block_in_stat184);
                    block();

                    state._fsp--;


                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:210:7: structDecl
                    {
                    pushFollow(FOLLOW_structDecl_in_stat192);
                    structDecl();

                    state._fsp--;


                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:211:7: functionDecl
                    {
                    pushFollow(FOLLOW_functionDecl_in_stat200);
                    functionDecl();

                    state._fsp--;


                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:212:7: returnStat
                    {
                    pushFollow(FOLLOW_returnStat_in_stat208);
                    returnStat();

                    state._fsp--;


                    retval.isReturnStat =true;

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stat"



    // $ANTLR start "block"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:214:1: block : ^( BLOCK ( stat )+ ) ;
    public final void block() throws RecognitionException {
        Scope_stack.push(new Scope_scope());


        				((Scope_scope)Scope_stack.peek()).env = new HashMap<>();
        				((Scope_scope)Scope_stack.peek()).functionEnv = new HashMap<>();
        				((Scope_scope)Scope_stack.peek()).structEnv = new HashMap<>();
        				
        				((Scope_scope)Scope_stack.peek()).isFunctionBlock = false;
        			
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:222:4: ( ^( BLOCK ( stat )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:223:5: ^( BLOCK ( stat )+ )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block243); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:223:13: ( stat )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= ASSIGN && LA3_0 <= BLOCK)||(LA3_0 >= ECHO && LA3_0 <= FUNCDECL)||LA3_0==40||(LA3_0 >= 42 && LA3_0 <= 43)||LA3_0==45) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:223:13: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_block245);
            	    stat();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
            Scope_stack.pop();

        }
        return ;
    }
    // $ANTLR end "block"


    public static class functionDecl_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "functionDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:227:1: functionDecl : ^( FUNCDECL rtn= returnFunctionDecl ID (fDecl= parametersDecl )? ( stat )* ) ;
    public final JPoclTypedAST.functionDecl_return functionDecl() throws RecognitionException {
        Scope_stack.push(new Scope_scope());

        JPoclTypedAST.functionDecl_return retval = new JPoclTypedAST.functionDecl_return();
        retval.start = input.LT(1);


        TypeTree ID3=null;
        JPoclTypedAST.returnFunctionDecl_return rtn =null;

        FunctionTypeDec fDecl =null;

        JPoclTypedAST.stat_return stat4 =null;



        				((Scope_scope)Scope_stack.peek()).env = new HashMap<>();
        				((Scope_scope)Scope_stack.peek()).functionEnv = new HashMap<>();
        				((Scope_scope)Scope_stack.peek()).structEnv = new HashMap<>();
        				((Scope_scope)Scope_stack.peek()).isFunctionBlock = true;
        				
        				boolean hasReturnStat = false, hasBody = false;				
        			
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:236:4: ( ^( FUNCDECL rtn= returnFunctionDecl ID (fDecl= parametersDecl )? ( stat )* ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:236:6: ^( FUNCDECL rtn= returnFunctionDecl ID (fDecl= parametersDecl )? ( stat )* )
            {
            match(input,FUNCDECL,FOLLOW_FUNCDECL_in_functionDecl276); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_returnFunctionDecl_in_functionDecl280);
            rtn=returnFunctionDecl();

            state._fsp--;


            ID3=(TypeTree)match(input,ID,FOLLOW_ID_in_functionDecl282); 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:236:48: (fDecl= parametersDecl )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==PDECS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:236:48: fDecl= parametersDecl
                    {
                    pushFollow(FOLLOW_parametersDecl_in_functionDecl286);
                    fDecl=parametersDecl();

                    state._fsp--;


                    }
                    break;

            }



            				if(isFunctionAlreadyDeclared((ID3!=null?ID3.getText():null)))
            					raiseTypeException(FUNCTION_REDECLARATION, 
               					((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), (ID3!=null?ID3.getText():null));
               					
            				FunctionType type = new FunctionType((ID3!=null?ID3.getText():null),
            															(rtn!=null?rtn.type:null),
            															fDecl == null ? new FunctionTypeDec() : fDecl);
            				int outerScopeLevel = Scope_stack.size()-2;
            				((Scope_scope)Scope_stack.elementAt(outerScopeLevel)).functionEnv.put((ID3!=null?ID3.getText():null),type);
            				addFunctionDeclarationToGlobalScope((ID3!=null?ID3.getText():null),type);
            				((TypeTree)retval.start).setStaticType((rtn!=null?rtn.type:null));
            			

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:250:6: ( stat )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= ASSIGN && LA5_0 <= BLOCK)||(LA5_0 >= ECHO && LA5_0 <= FUNCDECL)||LA5_0==40||(LA5_0 >= 42 && LA5_0 <= 43)||LA5_0==45) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:250:7: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_functionDecl301);
            	    stat4=stat();

            	    state._fsp--;



            	    						hasBody = true;
            	    						if((stat4!=null?stat4.isReturnStat:false) && hasReturnStat){
            	    							raiseTypeException(UNREACHBLE_CODE, 
            	    		   					((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine());
            	    						}
            	    						if((stat4!=null?stat4.isReturnStat:false)){
            	    							hasReturnStat = true;
            	    						}

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input, Token.UP, null); 



            				if(!hasReturnStat && !(PrimType.VOID.equalsTo((rtn!=null?rtn.type:null))))
            						raiseTypeException(RETURN_MISSING, 
            		   					((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
            		   					(ID3!=null?ID3.getText():null), (rtn!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(rtn.start),input.getTreeAdaptor().getTokenStopIndex(rtn.start))):null));
            				/*if(!hasBody)
            					throw new TypeException("Empty function");*/
            			

            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
            Scope_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "functionDecl"


    public static class returnFunctionDecl_return extends TreeRuleReturnScope {
        public Type type;
    };


    // $ANTLR start "returnFunctionDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:269:1: returnFunctionDecl returns [Type type] : ( 'int' | 'bool' | 'void' | ID );
    public final JPoclTypedAST.returnFunctionDecl_return returnFunctionDecl() throws RecognitionException {
        JPoclTypedAST.returnFunctionDecl_return retval = new JPoclTypedAST.returnFunctionDecl_return();
        retval.start = input.LT(1);


        TypeTree ID5=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:269:39: ( 'int' | 'bool' | 'void' | ID )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt6=1;
                }
                break;
            case 39:
                {
                alt6=2;
                }
                break;
            case 44:
                {
                alt6=3;
                }
                break;
            case ID:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }

            switch (alt6) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:270:12: 'int'
                    {
                    match(input,41,FOLLOW_41_in_returnFunctionDecl337); 

                    retval.type = PrimType.INT;

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:271:13: 'bool'
                    {
                    match(input,39,FOLLOW_39_in_returnFunctionDecl353); 

                    retval.type = PrimType.BOOL;

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:272:13: 'void'
                    {
                    match(input,44,FOLLOW_44_in_returnFunctionDecl369); 

                    retval.type = PrimType.VOID;

                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:273:13: ID
                    {
                    ID5=(TypeTree)match(input,ID,FOLLOW_ID_in_returnFunctionDecl385); 


                    									 	if((retval.type = isStructIDVisible((ID5!=null?ID5.getText():null))) == null){
                    											raiseTypeException(TYPE_UNRESOLVED, 
                    							   					((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   					(ID5!=null?ID5.getText():null));
                    							   		}
                    									 

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "returnFunctionDecl"


    public static class returnStat_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "returnStat"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:282:1: returnStat : ( 'return' | ^( 'return' expr ) );
    public final JPoclTypedAST.returnStat_return returnStat() throws RecognitionException {
        JPoclTypedAST.returnStat_return retval = new JPoclTypedAST.returnStat_return();
        retval.start = input.LT(1);


        JPoclTypedAST.expr_return expr6 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:282:12: ( 'return' | ^( 'return' expr ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==42) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==DOWN) ) {
                    alt7=2;
                }
                else if ( ((LA7_1 >= UP && LA7_1 <= BLOCK)||(LA7_1 >= ECHO && LA7_1 <= FUNCDECL)||LA7_1==40||(LA7_1 >= 42 && LA7_1 <= 43)||LA7_1==45) ) {
                    alt7=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:283:6: 'return'
                    {
                    match(input,42,FOLLOW_42_in_returnStat411); 

                    checkFunctionReturn(((TypeTree)retval.start), PrimType.VOID);

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:284:8: ^( 'return' expr )
                    {
                    match(input,42,FOLLOW_42_in_returnStat423); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_returnStat425);
                    expr6=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    checkFunctionReturn(((TypeTree)retval.start), (expr6!=null?expr6.type:null));

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "returnStat"



    // $ANTLR start "parametersDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:287:1: parametersDecl returns [FunctionTypeDec dec] : ^( PDECS ( parameterDecl[dec] )+ ) ;
    public final FunctionTypeDec parametersDecl() throws RecognitionException {
        FunctionTypeDec dec = null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:288:8: ( ^( PDECS ( parameterDecl[dec] )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:289:8: ^( PDECS ( parameterDecl[dec] )+ )
            {
            dec = new FunctionTypeDec();

            match(input,PDECS,FOLLOW_PDECS_in_parametersDecl471); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:290:16: ( parameterDecl[dec] )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==39||LA8_0==41||LA8_0==43) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:290:16: parameterDecl[dec]
            	    {
            	    pushFollow(FOLLOW_parameterDecl_in_parametersDecl473);
            	    parameterDecl(dec);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            match(input, Token.UP, null); 


            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return dec;
    }
    // $ANTLR end "parametersDecl"


    public static class parameterDecl_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "parameterDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:293:1: parameterDecl[FunctionTypeDec dec] : ( ^( 'int' intID= ID ) | ^( 'bool' boolID= ID ) | ^( 'struct' id0= ID id1= ID ) );
    public final JPoclTypedAST.parameterDecl_return parameterDecl(FunctionTypeDec dec) throws RecognitionException {
        JPoclTypedAST.parameterDecl_return retval = new JPoclTypedAST.parameterDecl_return();
        retval.start = input.LT(1);


        TypeTree intID=null;
        TypeTree boolID=null;
        TypeTree id0=null;
        TypeTree id1=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:294:8: ( ^( 'int' intID= ID ) | ^( 'bool' boolID= ID ) | ^( 'struct' id0= ID id1= ID ) )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt9=1;
                }
                break;
            case 39:
                {
                alt9=2;
                }
                break;
            case 43:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:295:8: ^( 'int' intID= ID )
                    {
                    match(input,41,FOLLOW_41_in_parameterDecl509); 

                    match(input, Token.DOWN, null); 
                    intID=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl513); 

                    match(input, Token.UP, null); 



                    								if(!dec.addParameter((intID!=null?intID.getText():null),PrimType.INT))
                    									raiseTypeException(DUPLICATED_PARAMETER, 
                    							   		((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   		(intID!=null?intID.getText():null));
                    								((Scope_scope)Scope_stack.peek()).env.put((intID!=null?intID.getText():null),PrimType.INT);
                    							

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:302:9: ^( 'bool' boolID= ID )
                    {
                    match(input,39,FOLLOW_39_in_parameterDecl527); 

                    match(input, Token.DOWN, null); 
                    boolID=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl531); 

                    match(input, Token.UP, null); 



                    								if(!dec.addParameter((boolID!=null?boolID.getText():null),PrimType.BOOL))
                    									raiseTypeException(DUPLICATED_PARAMETER, 
                    							   		((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   		(boolID!=null?boolID.getText():null));								
                    								((Scope_scope)Scope_stack.peek()).env.put((boolID!=null?boolID.getText():null),PrimType.BOOL);
                    							

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:309:9: ^( 'struct' id0= ID id1= ID )
                    {
                    match(input,43,FOLLOW_43_in_parameterDecl545); 

                    match(input, Token.DOWN, null); 
                    id0=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl549); 

                    id1=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl553); 

                    match(input, Token.UP, null); 



                    								StructType type;
                    								if((type = isStructIDVisible((id0!=null?id0.getText():null))) == null)
                    									raiseTypeException(TYPE_UNRESOLVED, 
                    							   		((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   		(id0!=null?id0.getText():null));
                    								if(!dec.addParameter((id1!=null?id1.getText():null),type))
                    									raiseTypeException(DUPLICATED_PARAMETER, 
                    							   		((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   		(id1!=null?id1.getText():null));		
                    								((Scope_scope)Scope_stack.peek()).env.put((id1!=null?id1.getText():null),type);
                    							

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameterDecl"


    public static class structDecl_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "structDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:323:1: structDecl : ^( 'struct' ID ( structMember[structTypeDec, structType] )+ ) ;
    public final JPoclTypedAST.structDecl_return structDecl() throws RecognitionException {
        JPoclTypedAST.structDecl_return retval = new JPoclTypedAST.structDecl_return();
        retval.start = input.LT(1);


        TypeTree ID7=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:323:12: ( ^( 'struct' ID ( structMember[structTypeDec, structType] )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:324:7: ^( 'struct' ID ( structMember[structTypeDec, structType] )+ )
            {
            match(input,43,FOLLOW_43_in_structDecl581); 

            match(input, Token.DOWN, null); 
            ID7=(TypeTree)match(input,ID,FOLLOW_ID_in_structDecl583); 


            					 	if(isStructAlreadyDeclared((ID7!=null?ID7.getText():null)))
            							raiseTypeException(STRUCT_REDECLARATION, 
            								((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
            							  (ID7!=null?ID7.getText():null));

            					 	StructTypeDec structTypeDec = new StructTypeDec();
            					 	StructType structType = new StructType((ID7!=null?ID7.getText():null),structTypeDec);
            					 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:334:7: ( structMember[structTypeDec, structType] )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==39||LA10_0==41||LA10_0==43) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:334:7: structMember[structTypeDec, structType]
            	    {
            	    pushFollow(FOLLOW_structMember_in_structDecl600);
            	    structMember(structTypeDec, structType);

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            match(input, Token.UP, null); 



            						 ((Scope_scope)Scope_stack.peek()).structEnv.put((ID7!=null?ID7.getText():null),structType);
            						 addStructDeclarationToGlobalScope((ID7!=null?ID7.getText():null),structType);
            					 

            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structDecl"


    public static class structMember_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "structMember"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:341:1: structMember[StructTypeDec structTypeDec, StructType declaringStructType] : ( ^( 'int' id1= ID ) | ^( 'bool' id2= ID ) | ^( 'struct' structType= ID structID= ID ) );
    public final JPoclTypedAST.structMember_return structMember(StructTypeDec structTypeDec, StructType declaringStructType) throws RecognitionException {
        JPoclTypedAST.structMember_return retval = new JPoclTypedAST.structMember_return();
        retval.start = input.LT(1);


        TypeTree id1=null;
        TypeTree id2=null;
        TypeTree structType=null;
        TypeTree structID=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:341:76: ( ^( 'int' id1= ID ) | ^( 'bool' id2= ID ) | ^( 'struct' structType= ID structID= ID ) )
            int alt11=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt11=1;
                }
                break;
            case 39:
                {
                alt11=2;
                }
                break;
            case 43:
                {
                alt11=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }

            switch (alt11) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:342:7: ^( 'int' id1= ID )
                    {
                    match(input,41,FOLLOW_41_in_structMember635); 

                    match(input, Token.DOWN, null); 
                    id1=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember639); 

                    match(input, Token.UP, null); 



                    						 if(!structTypeDec.addField((id1!=null?id1.getText():null), PrimType.INT))
                    						 		raiseTypeException(DUPLICATED_FIELD, 
                    									((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							  	(id1!=null?id1.getText():null));
                    						 
                    						

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:349:9: ^( 'bool' id2= ID )
                    {
                    match(input,39,FOLLOW_39_in_structMember653); 

                    match(input, Token.DOWN, null); 
                    id2=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember657); 

                    match(input, Token.UP, null); 



                    						 if(!structTypeDec.addField((id2!=null?id2.getText():null), PrimType.BOOL))
                    						 		raiseTypeException(DUPLICATED_FIELD, 
                    									((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							  	(id2!=null?id2.getText():null));
                    						

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:355:9: ^( 'struct' structType= ID structID= ID )
                    {
                    match(input,43,FOLLOW_43_in_structMember671); 

                    match(input, Token.DOWN, null); 
                    structType=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember675); 

                    structID=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember679); 

                    match(input, Token.UP, null); 


                     
                    	  					Type type;
                    	  					if((structType!=null?structType.getText():null).equals(declaringStructType.getId()))
                    	  						type = declaringStructType;
                    							else if((type = isStructIDVisible((structType!=null?structType.getText():null))) == null)
                    									raiseTypeException(TYPE_UNRESOLVED, 
                    							   		((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   		(structType!=null?structType.getText():null));
                    							if(!structTypeDec.addField((structID!=null?structID.getText():null),type))
                    						 		raiseTypeException(DUPLICATED_FIELD, 
                    									((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							  	(structID!=null?structID.getText():null));							
                    						 

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structMember"


    public static class expr_return extends TreeRuleReturnScope {
        public Type type;
    };


    // $ANTLR start "expr"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:370:1: expr returns [Type type] : ( ^( ( '^' | '+' | '-' | '%' | '*' | '/' ) t1= expr t2= expr ) | ^( ( '!' | PLUS | SIGN ) t= expr ) | ^( ( '<' | '<=' | '>' | '>=' ) t1= expr t2= expr ) | ^( '==' t1= expr t2= expr ) | ^( ( '&&' | '||' ) t1= expr t2= expr ) | ^( DOT ( functionCall | structValue ) qualifiedID ) | functionCall | structValue | qualifiedID | INT | BOOLEAN | ^( '~' t= expr ) );
    public final JPoclTypedAST.expr_return expr() throws RecognitionException {
        JPoclTypedAST.expr_return retval = new JPoclTypedAST.expr_return();
        retval.start = input.LT(1);


        JPoclTypedAST.expr_return t1 =null;

        JPoclTypedAST.expr_return t2 =null;

        JPoclTypedAST.expr_return t =null;

        JPoclTypedAST.functionCall_return functionCall8 =null;

        JPoclTypedAST.structValue_return structValue9 =null;

        JPoclTypedAST.qualifiedID_return qualifiedID10 =null;

        JPoclTypedAST.functionCall_return functionCall11 =null;

        JPoclTypedAST.structValue_return structValue12 =null;

        JPoclTypedAST.qualifiedID_return qualifiedID13 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:373:6: ( ^( ( '^' | '+' | '-' | '%' | '*' | '/' ) t1= expr t2= expr ) | ^( ( '!' | PLUS | SIGN ) t= expr ) | ^( ( '<' | '<=' | '>' | '>=' ) t1= expr t2= expr ) | ^( '==' t1= expr t2= expr ) | ^( ( '&&' | '||' ) t1= expr t2= expr ) | ^( DOT ( functionCall | structValue ) qualifiedID ) | functionCall | structValue | qualifiedID | INT | BOOLEAN | ^( '~' t= expr ) )
            int alt13=12;
            switch ( input.LA(1) ) {
            case 22:
            case 26:
            case 27:
            case 29:
            case 30:
            case 38:
                {
                alt13=1;
                }
                break;
            case PLUS:
            case SIGN:
            case 21:
                {
                alt13=2;
                }
                break;
            case 32:
            case 33:
            case 36:
            case 37:
                {
                alt13=3;
                }
                break;
            case 35:
                {
                alt13=4;
                }
                break;
            case 23:
            case 47:
                {
                alt13=5;
                }
                break;
            case DOT:
                {
                int LA13_6 = input.LA(2);

                if ( (LA13_6==DOWN) ) {
                    int LA13_13 = input.LA(3);

                    if ( (LA13_13==FUNCCALL||LA13_13==43) ) {
                        alt13=6;
                    }
                    else if ( (LA13_13==ID) ) {
                        alt13=9;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 13, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 6, input);

                    throw nvae;

                }
                }
                break;
            case FUNCCALL:
                {
                alt13=7;
                }
                break;
            case 43:
                {
                alt13=8;
                }
                break;
            case ID:
                {
                alt13=9;
                }
                break;
            case INT:
                {
                alt13=10;
                }
                break;
            case BOOLEAN:
                {
                alt13=11;
                }
                break;
            case 49:
                {
                alt13=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:374:6: ^( ( '^' | '+' | '-' | '%' | '*' | '/' ) t1= expr t2= expr )
                    {
                    if ( input.LA(1)==22||(input.LA(1) >= 26 && input.LA(1) <= 27)||(input.LA(1) >= 29 && input.LA(1) <= 30)||input.LA(1)==38 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr742);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr746);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    					check((t1!=null?t1.type:null),PrimType.INT,((TypeTree)retval.start)); 
                    					check((t2!=null?t2.type:null),PrimType.INT,((TypeTree)retval.start)); 
                    					retval.type = PrimType.INT;
                    				

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:379:8: ^( ( '!' | PLUS | SIGN ) t= expr )
                    {
                    if ( (input.LA(1) >= PLUS && input.LA(1) <= SIGN)||input.LA(1)==21 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr774);
                    t=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    			  	check((t!=null?t.type:null),PrimType.INT,((TypeTree)retval.start)); 
                    					retval.type = PrimType.INT;
                    				

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:383:7: ^( ( '<' | '<=' | '>' | '>=' ) t1= expr t2= expr )
                    {
                    if ( (input.LA(1) >= 32 && input.LA(1) <= 33)||(input.LA(1) >= 36 && input.LA(1) <= 37) ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr801);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr805);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    					check((t1!=null?t1.type:null),PrimType.INT,((TypeTree)retval.start)); 
                    					check((t2!=null?t2.type:null),PrimType.INT,((TypeTree)retval.start)); 
                    					retval.type = PrimType.BOOL;
                    				

                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:388:7: ^( '==' t1= expr t2= expr )
                    {
                    match(input,35,FOLLOW_35_in_expr818); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr822);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr826);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    					check((t1!=null?t1.type:null),(t2!=null?t2.type:null),((TypeTree)retval.start)); 
                    					retval.type = PrimType.BOOL;
                    				

                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:392:7: ^( ( '&&' | '||' ) t1= expr t2= expr )
                    {
                    if ( input.LA(1)==23||input.LA(1)==47 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr849);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr853);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    					check((t1!=null?t1.type:null),PrimType.BOOL,((TypeTree)retval.start)); 
                    					check((t2!=null?t2.type:null),PrimType.BOOL,((TypeTree)retval.start)); 
                    					retval.type = PrimType.BOOL;
                    				

                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:397:7: ^( DOT ( functionCall | structValue ) qualifiedID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_expr865); 

                    match(input, Token.DOWN, null); 
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:397:12: ( functionCall | structValue )
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==FUNCCALL) ) {
                        alt12=1;
                    }
                    else if ( (LA12_0==43) ) {
                        alt12=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;

                    }
                    switch (alt12) {
                        case 1 :
                            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:397:13: functionCall
                            {
                            pushFollow(FOLLOW_functionCall_in_expr867);
                            functionCall8=functionCall();

                            state._fsp--;


                            ((TypeTree)retval.start).setStaticType((functionCall8!=null?functionCall8.type:null));

                            }
                            break;
                        case 2 :
                            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:398:11: structValue
                            {
                            pushFollow(FOLLOW_structValue_in_expr881);
                            structValue9=structValue();

                            state._fsp--;


                            ((TypeTree)retval.start).setStaticType((structValue9!=null?structValue9.type:null));

                            }
                            break;

                    }


                    pushFollow(FOLLOW_qualifiedID_in_expr892);
                    qualifiedID10=qualifiedID();

                    state._fsp--;


                    retval.type = (qualifiedID10!=null?qualifiedID10.type:null);

                    match(input, Token.UP, null); 


                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:400:7: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_expr903);
                    functionCall11=functionCall();

                    state._fsp--;



                    					retval.type = (functionCall11!=null?functionCall11.type:null);
                    				

                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:403:7: structValue
                    {
                    pushFollow(FOLLOW_structValue_in_expr913);
                    structValue12=structValue();

                    state._fsp--;



                    					retval.type = (structValue12!=null?structValue12.type:null);
                    				

                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:406:7: qualifiedID
                    {
                    pushFollow(FOLLOW_qualifiedID_in_expr923);
                    qualifiedID13=qualifiedID();

                    state._fsp--;



                    					retval.type = (qualifiedID13!=null?qualifiedID13.type:null);
                    				

                    }
                    break;
                case 10 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:409:8: INT
                    {
                    match(input,INT,FOLLOW_INT_in_expr934); 


                    			  	retval.type = PrimType.INT;
                    			  

                    }
                    break;
                case 11 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:412:7: BOOLEAN
                    {
                    match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expr945); 


                    					retval.type = PrimType.BOOL;
                    				

                    }
                    break;
                case 12 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:415:7: ^( '~' t= expr )
                    {
                    match(input,49,FOLLOW_49_in_expr956); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr960);
                    t=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 



                    				  check((t!=null?t.type:null),PrimType.BOOL,((TypeTree)retval.start)); 
                    				  retval.type = PrimType.BOOL;
                    			  

                    }
                    break;

            }

            					((TypeTree)retval.start).setStaticType(retval.type);
            				
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class functionCall_return extends TreeRuleReturnScope {
        public Type type;
    };


    // $ANTLR start "functionCall"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:421:1: functionCall returns [Type type] : ^( FUNCCALL ID (e= expr )* ) ;
    public final JPoclTypedAST.functionCall_return functionCall() throws RecognitionException {
        JPoclTypedAST.functionCall_return retval = new JPoclTypedAST.functionCall_return();
        retval.start = input.LT(1);


        TypeTree ID14=null;
        JPoclTypedAST.expr_return e =null;



        							List<Type> parametersType = new LinkedList<>();
        						
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:424:8: ( ^( FUNCCALL ID (e= expr )* ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:425:7: ^( FUNCCALL ID (e= expr )* )
            {
            match(input,FUNCCALL,FOLLOW_FUNCCALL_in_functionCall1005); 

            match(input, Token.DOWN, null); 
            ID14=(TypeTree)match(input,ID,FOLLOW_ID_in_functionCall1007); 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:425:22: (e= expr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==BOOLEAN||LA14_0==DOT||LA14_0==FUNCCALL||(LA14_0 >= ID && LA14_0 <= INT)||(LA14_0 >= PLUS && LA14_0 <= SIGN)||(LA14_0 >= 21 && LA14_0 <= 23)||(LA14_0 >= 26 && LA14_0 <= 27)||(LA14_0 >= 29 && LA14_0 <= 30)||(LA14_0 >= 32 && LA14_0 <= 33)||(LA14_0 >= 35 && LA14_0 <= 38)||LA14_0==43||LA14_0==47||LA14_0==49) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:425:23: e= expr
            	    {
            	    pushFollow(FOLLOW_expr_in_functionCall1013);
            	    e=expr();

            	    state._fsp--;


            	    parametersType.add((e!=null?e.type:null));

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            match(input, Token.UP, null); 



            							FunctionType functionType;
            							if((functionType = isFunctionIDVisible((ID14!=null?ID14.getText():null))) == null)
            								raiseTypeException(FUNCTION_UNDEFINED, 
            						   		((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
            						   		(ID14!=null?ID14.getText():null));
            							checkFunctionParameters(functionType, parametersType, (ID14!=null?ID14.getText():null), ((TypeTree)retval.start));
            							retval.type = functionType.getReturnType();
            							((TypeTree)retval.start).setStaticType(retval.type);
            						

            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionCall"


    public static class structValue_return extends TreeRuleReturnScope {
        public Type type;
    };


    // $ANTLR start "structValue"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:437:1: structValue returns [Type type] : ^( 'struct' ID ( expr )+ ) ;
    public final JPoclTypedAST.structValue_return structValue() throws RecognitionException {
        JPoclTypedAST.structValue_return retval = new JPoclTypedAST.structValue_return();
        retval.start = input.LT(1);


        TypeTree ID15=null;
        JPoclTypedAST.expr_return expr16 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:437:32: ( ^( 'struct' ID ( expr )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:438:8: ^( 'struct' ID ( expr )+ )
            {
            match(input,43,FOLLOW_43_in_structValue1044); 

            match(input, Token.DOWN, null); 
            ID15=(TypeTree)match(input,ID,FOLLOW_ID_in_structValue1046); 


            								 StructType t;
            								 if((t = isStructIDVisible((ID15!=null?ID15.getText():null))) == null){
            										raiseTypeException(TYPE_UNRESOLVED, 
            							   			((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
            							   			(ID15!=null?ID15.getText():null));
            								 }
            								 Iterator<Type> it = t.types().iterator();
            								 Type field;
            							 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:447:11: ( expr )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==BOOLEAN||LA15_0==DOT||LA15_0==FUNCCALL||(LA15_0 >= ID && LA15_0 <= INT)||(LA15_0 >= PLUS && LA15_0 <= SIGN)||(LA15_0 >= 21 && LA15_0 <= 23)||(LA15_0 >= 26 && LA15_0 <= 27)||(LA15_0 >= 29 && LA15_0 <= 30)||(LA15_0 >= 32 && LA15_0 <= 33)||(LA15_0 >= 35 && LA15_0 <= 38)||LA15_0==43||LA15_0==47||LA15_0==49) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:447:12: expr
            	    {
            	    pushFollow(FOLLOW_expr_in_structValue1051);
            	    expr16=expr();

            	    state._fsp--;


            	    field = it.next(); check(field,(expr16!=null?expr16.type:null),((TypeTree)retval.start));

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            match(input, Token.UP, null); 


            retval.type = t; ((TypeTree)retval.start).setStaticType(t);

            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structValue"


    public static class qualifiedID_return extends TreeRuleReturnScope {
        public Type type;
    };


    // $ANTLR start "qualifiedID"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:452:1: qualifiedID returns [Type type] : ( id | ^( DOT id innerQID= qualifiedID ) );
    public final JPoclTypedAST.qualifiedID_return qualifiedID() throws RecognitionException {
        JPoclTypedAST.qualifiedID_return retval = new JPoclTypedAST.qualifiedID_return();
        retval.start = input.LT(1);


        JPoclTypedAST.qualifiedID_return innerQID =null;

        JPoclTypedAST.id_return id17 =null;

        JPoclTypedAST.id_return id18 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:452:32: ( id | ^( DOT id innerQID= qualifiedID ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ID) ) {
                alt16=1;
            }
            else if ( (LA16_0==DOT) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:453:7: id
                    {
                    pushFollow(FOLLOW_id_in_qualifiedID1102);
                    id17=id();

                    state._fsp--;



                    							if(((TypeTree)retval.start).parent.token.getType() == DOT){
                    								Type outerType = ((TypeTree)((TypeTree)retval.start).parent).getStaticType();
                    								retval.type = isFieldDefined((id17!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id17.start),input.getTreeAdaptor().getTokenStopIndex(id17.start))):null), outerType);
                    							}else{
                    								retval.type = isIDVisible((id17!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id17.start),input.getTreeAdaptor().getTokenStopIndex(id17.start))):null));
                    								if(retval.type == null && ((TypeTree)retval.start).parent.token.getType() == ASSIGN){
                    									retval.type = ((TypeTree)((TypeTree)retval.start).parent.getChild(0)).getStaticType();
                    									((Scope_scope)Scope_stack.peek()).env.put((id17!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id17.start),input.getTreeAdaptor().getTokenStopIndex(id17.start))):null),retval.type);
                    								}
                    							}
                    							if(retval.type == null)
                    								raiseTypeException(ID_FIELD_UNRESOLVED, 
                    									((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   	(id17!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id17.start),input.getTreeAdaptor().getTokenStopIndex(id17.start))):null));
                    							(id17!=null?((TypeTree)id17.start):null).setStaticType(retval.type);
                    						

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:470:9: ^( DOT id innerQID= qualifiedID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_qualifiedID1115); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_id_in_qualifiedID1117);
                    id18=id();

                    state._fsp--;



                    									if(((TypeTree)retval.start).parent.token.getType() == DOT){
                    										Type outerType = ((TypeTree)((TypeTree)retval.start).parent).getStaticType();
                    										retval.type = isFieldDefined((id18!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id18.start),input.getTreeAdaptor().getTokenStopIndex(id18.start))):null), outerType);
                    									}else{
                    										retval.type = isIDVisible((id18!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id18.start),input.getTreeAdaptor().getTokenStopIndex(id18.start))):null));
                    									}
                    									if(retval.type == null)
                    										raiseTypeException(ID_FIELD_UNRESOLVED, 
                    							   			((TypeTree)retval.start).getLine(), ((TypeTree)retval.start).getCharPositionInLine(), 
                    							   			(id18!=null?(input.getTokenStream().toString(input.getTreeAdaptor().getTokenStartIndex(id18.start),input.getTreeAdaptor().getTokenStopIndex(id18.start))):null));
                    									((TypeTree)retval.start).setStaticType(retval.type);
                    									(id18!=null?((TypeTree)id18.start):null).setStaticType(retval.type);
                    								

                    pushFollow(FOLLOW_qualifiedID_in_qualifiedID1132);
                    innerQID=qualifiedID();

                    state._fsp--;



                    									retval.type = (innerQID!=null?innerQID.type:null);
                    									((TypeTree)retval.start).setStaticType(retval.type);
                    								

                    match(input, Token.UP, null); 


                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "qualifiedID"


    public static class id_return extends TreeRuleReturnScope {
    };


    // $ANTLR start "id"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:490:1: id : ID ;
    public final JPoclTypedAST.id_return id() throws RecognitionException {
        JPoclTypedAST.id_return retval = new JPoclTypedAST.id_return();
        retval.start = input.LT(1);


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:490:3: ( ID )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclTypedAST.g:490:5: ID
            {
            match(input,ID,FOLLOW_ID_in_id1155); 

            }

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "id"

    // Delegated rules


 

    public static final BitSet FOLLOW_CALC_in_calc89 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_calc91 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_ECHO_in_stat112 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat116 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSIGN_in_stat125 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat127 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_stat129 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functionCall_in_stat138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_stat148 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat152 = new BitSet(new long[]{0x00002D0000000E30L});
    public static final BitSet FOLLOW_stat_in_stat154 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_45_in_stat166 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat170 = new BitSet(new long[]{0x00002D0000000E30L});
    public static final BitSet FOLLOW_stat_in_stat172 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_stat184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structDecl_in_stat192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDecl_in_stat200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStat_in_stat208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BLOCK_in_block243 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_block245 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_FUNCDECL_in_functionDecl276 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_returnFunctionDecl_in_functionDecl280 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_functionDecl282 = new BitSet(new long[]{0x00002D0000008E38L});
    public static final BitSet FOLLOW_parametersDecl_in_functionDecl286 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_stat_in_functionDecl301 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_41_in_returnFunctionDecl337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_returnFunctionDecl353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_returnFunctionDecl369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_returnFunctionDecl385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStat411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStat423 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_returnStat425 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PDECS_in_parametersDecl471 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_parameterDecl_in_parametersDecl473 = new BitSet(new long[]{0x00000A8000000008L});
    public static final BitSet FOLLOW_41_in_parameterDecl509 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl513 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_parameterDecl527 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl531 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_parameterDecl545 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl549 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl553 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_structDecl581 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structDecl583 = new BitSet(new long[]{0x00000A8000000000L});
    public static final BitSet FOLLOW_structMember_in_structDecl600 = new BitSet(new long[]{0x00000A8000000008L});
    public static final BitSet FOLLOW_41_in_structMember635 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember639 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_structMember653 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember657 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_structMember671 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember675 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember679 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expr717 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr742 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr746 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expr760 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr774 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expr787 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr801 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr805 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_35_in_expr818 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr822 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr826 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_set_in_expr839 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr849 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr853 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_expr865 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_expr867 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_structValue_in_expr881 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_expr892 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functionCall_in_expr903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structValue_in_expr913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedID_in_expr923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_expr934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_expr945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_expr956 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr960 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCCALL_in_functionCall1005 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_functionCall1007 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_expr_in_functionCall1013 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_43_in_structValue1044 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structValue1046 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_structValue1051 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_id_in_qualifiedID1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_qualifiedID1115 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_id_in_qualifiedID1117 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_qualifiedID1132 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_id1155 = new BitSet(new long[]{0x0000000000000002L});

}