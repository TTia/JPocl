// $ANTLR 3.4 C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g 2013-07-15 11:46:23

package gen;

import type.*;
import ast.*;
import java.util.LinkedList;
import java.util.Collection;
import java.util.LinkedHashMap;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;
import java.util.HashMap;
@SuppressWarnings({"all", "warnings", "unchecked"})
public class JPoclGenerator extends TreeParser {
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

    protected static class FunctionStack_scope {
        int stackLimit;
    }
    protected Stack FunctionStack_stack = new Stack();


    protected static class BlockLocals_scope {
        LinkedList<String> locals;
        int localsLimit;
        boolean isFunctionBlock;
    }
    protected Stack BlockLocals_stack = new Stack();



    public JPoclGenerator(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public JPoclGenerator(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected StringTemplateGroup templateLib =
  new StringTemplateGroup("JPoclGeneratorTemplates", AngleBracketTemplateLexer.class);

public void setTemplateLib(StringTemplateGroup templateLib) {
  this.templateLib = templateLib;
}
public StringTemplateGroup getTemplateLib() {
  return templateLib;
}
/** allows convenient multi-value initialization:
 *  "new STAttrMap().put(...).put(...)"
 */
public static class STAttrMap extends HashMap {
  public STAttrMap put(String attrName, Object value) {
    super.put(attrName, value);
    return this;
  }
  public STAttrMap put(String attrName, int value) {
    super.put(attrName, new Integer(value));
    return this;
  }
}
    public String[] getTokenNames() { return JPoclGenerator.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g"; }


    	private String packageDst;
    	private LinkedHashMap<String,FunctionType> api;
    	private LinkedHashMap<String,StructType> dataTypes;

    	private int getVariableLocalNumber(String id){
    		int localNumber = 0;
    		for(int s = BlockLocals_stack.size()-1; s>=0; s--){
    			if(((BlockLocals_scope)BlockLocals_stack.elementAt(s)).locals.contains(id)){
    				localNumber = ((BlockLocals_scope)BlockLocals_stack.elementAt(s)).locals.indexOf(id);
    			}else{
    				localNumber += ((BlockLocals_scope)BlockLocals_stack.elementAt(s)).locals.size();
    			}
    			if(((BlockLocals_scope)BlockLocals_stack.elementAt(s)).isFunctionBlock){
    				break;
    			}
    		}
    		return localNumber;
    	}
    	
    	private void addVariableToLocals(String id){
    		((BlockLocals_scope)BlockLocals_stack.peek()).locals.addLast(id);
    		
    		for(int s = BlockLocals_stack.size()-1; s>=0; s--){
    			((BlockLocals_scope)BlockLocals_stack.elementAt(s)).localsLimit++;
    			if(((BlockLocals_scope)BlockLocals_stack.elementAt(s)).isFunctionBlock){
    				return;
    			}
    		}
    	}
    	
    	private boolean isDeclared(String id){
    		for(int s = BlockLocals_stack.size()-1; s>=0; s--){
    			if(((BlockLocals_scope)BlockLocals_stack.elementAt(s)).locals.contains(id)){
    				return true;
    			}
    			if(((BlockLocals_scope)BlockLocals_stack.elementAt(s)).isFunctionBlock){
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
    		((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit += delta;
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
      	((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit = Math.max(stackNeeded, ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit);
      }
      
      private int calculateStackLimit(int leftStackLimit, int rightStackLimit){
      	return calculateStackLimit(leftStackLimit, rightStackLimit, +1);
      }
      
      private int calculateStackLimit(int leftStackLimit, int rightStackLimit, int delta){
      	return Math.max(leftStackLimit, rightStackLimit)+delta;
      }
    	
    	private int getStackLimit(){
    		return ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit;
    	}



    public static class calc_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "calc"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:127:1: calc[String packageDst, LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes] : ^( CALC ( stat )+ ) -> calc(stackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimitstmts=stmtsSTstructDecls=structDeclsSTfunctionDecls=functionDeclsSTpackageDst=packageDst);
    public final JPoclGenerator.calc_return calc(String packageDst, LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes) throws RecognitionException {
        BlockLocals_stack.push(new BlockLocals_scope());
        FunctionStack_stack.push(new FunctionStack_scope());

        JPoclGenerator.calc_return retval = new JPoclGenerator.calc_return();
        retval.start = input.LT(1);


        JPoclGenerator.stat_return stat1 =null;



        			((BlockLocals_scope)BlockLocals_stack.peek()).locals = new LinkedList<>();
        			((BlockLocals_scope)BlockLocals_stack.peek()).isFunctionBlock = true;
        			this.packageDst = packageDst;
        			this.api = api;
        			this.dataTypes = dataTypes;
        			
        			List<Object> structDeclsST = new LinkedList<Object>();
        			List<Object> functionDeclsST = new LinkedList<Object>();
        			List<Object> stmtsST = new LinkedList<Object>();			
        		
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:141:3: ( ^( CALC ( stat )+ ) -> calc(stackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimitstmts=stmtsSTstructDecls=structDeclsSTfunctionDecls=functionDeclsSTpackageDst=packageDst))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:141:5: ^( CALC ( stat )+ )
            {
            match(input,CALC,FOLLOW_CALC_in_calc96); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:141:12: ( stat )+
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
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:141:13: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_calc99);
            	    stat1=stat();

            	    state._fsp--;



            	    				if((stat1!=null?stat1.structDeclST:null) != null){
            	    					structDeclsST.add((stat1!=null?stat1.structDeclST:null));
            	    				}else if((stat1!=null?stat1.functionDeclsST:null) != null){
            	    					functionDeclsST.add((stat1!=null?stat1.functionDeclsST:null));
            	    				}else{
            	    					stmtsST.add((stat1!=null?stat1.st:null));
            	    				}
            	    			

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


            // TEMPLATE REWRITE
            // 150:3: -> calc(stackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimitstmts=stmtsSTstructDecls=structDeclsSTfunctionDecls=functionDeclsSTpackageDst=packageDst)
            {
                retval.st = templateLib.getInstanceOf("calc",new STAttrMap().put("stackLimit", ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit).put("localsLimit", ((BlockLocals_scope)BlockLocals_stack.peek()).localsLimit).put("stmts", stmtsST).put("structDecls", structDeclsST).put("functionDecls", functionDeclsST).put("packageDst", packageDst));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
            BlockLocals_stack.pop();
            FunctionStack_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "calc"


    public static class stat_return extends TreeRuleReturnScope {
        public Object structDeclST;
        public Object functionDeclsST;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "stat"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:156:1: stat returns [Object structDeclST, Object functionDeclsST] : ( echo | ^( ASSIGN expr qualifiedID ) -> {$qualifiedID.start.getStaticType() instanceof StructType}? assignToObject(expr=$expr.stvariable=$qualifiedID.sttype=$qualifiedID.start.getStaticType()) -> assign(expr=$expr.stvariable=$qualifiedID.st)| functionCall | ^( 'if' expr s= stat ) -> if(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.st)| ^( 'while' expr s= stat ) -> while(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.st)| block | structDecl | functionDecl | returnStat );
    public final JPoclGenerator.stat_return stat() throws RecognitionException {
        JPoclGenerator.stat_return retval = new JPoclGenerator.stat_return();
        retval.start = input.LT(1);


        JPoclGenerator.stat_return s =null;

        JPoclGenerator.echo_return echo2 =null;

        JPoclGenerator.expr_return expr3 =null;

        JPoclGenerator.qualifiedID_return qualifiedID4 =null;

        JPoclGenerator.functionCall_return functionCall5 =null;

        JPoclGenerator.expr_return expr6 =null;

        JPoclGenerator.expr_return expr7 =null;

        JPoclGenerator.block_return block8 =null;

        JPoclGenerator.structDecl_return structDecl9 =null;

        JPoclGenerator.functionDecl_return functionDecl10 =null;

        JPoclGenerator.returnStat_return returnStat11 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:157:4: ( echo | ^( ASSIGN expr qualifiedID ) -> {$qualifiedID.start.getStaticType() instanceof StructType}? assignToObject(expr=$expr.stvariable=$qualifiedID.sttype=$qualifiedID.start.getStaticType()) -> assign(expr=$expr.stvariable=$qualifiedID.st)| functionCall | ^( 'if' expr s= stat ) -> if(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.st)| ^( 'while' expr s= stat ) -> while(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.st)| block | structDecl | functionDecl | returnStat )
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:158:4: echo
                    {
                    pushFollow(FOLLOW_echo_in_stat184);
                    echo2=echo();

                    state._fsp--;


                    retval.st = (echo2!=null?echo2.st:null);

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:160:5: ^( ASSIGN expr qualifiedID )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_stat197); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat199);
                    expr3=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_qualifiedID_in_stat201);
                    qualifiedID4=qualifiedID();

                    state._fsp--;


                    setStackLimit((expr3!=null?expr3.stackSize:0) + 1);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 161:5: -> {$qualifiedID.start.getStaticType() instanceof StructType}? assignToObject(expr=$expr.stvariable=$qualifiedID.sttype=$qualifiedID.start.getStaticType())
                    if ((qualifiedID4!=null?((TypeTree)qualifiedID4.start):null).getStaticType() instanceof StructType) {
                        retval.st = templateLib.getInstanceOf("assignToObject",new STAttrMap().put("expr", (expr3!=null?expr3.st:null)).put("variable", (qualifiedID4!=null?qualifiedID4.st:null)).put("type", (qualifiedID4!=null?((TypeTree)qualifiedID4.start):null).getStaticType()));
                    }

                    else // 163:5: -> assign(expr=$expr.stvariable=$qualifiedID.st)
                    {
                        retval.st = templateLib.getInstanceOf("assign",new STAttrMap().put("expr", (expr3!=null?expr3.st:null)).put("variable", (qualifiedID4!=null?qualifiedID4.st:null)));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:165:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_stat266);
                    functionCall5=functionCall();

                    state._fsp--;


                    retval.st = (functionCall5!=null?functionCall5.st:null); setStackLimit((functionCall5!=null?functionCall5.stackSize:0));

                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:167:8: ^( 'if' expr s= stat )
                    {
                    match(input,40,FOLLOW_40_in_stat282); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat284);
                    expr6=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_stat_in_stat288);
                    s=stat();

                    state._fsp--;


                    setStackLimit((expr6!=null?expr6.stackSize:0));

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 168:8: -> if(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.st)
                    {
                        retval.st = templateLib.getInstanceOf("if",new STAttrMap().put("bexpr", (expr6!=null?expr6.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()).put("stmts", (s!=null?s.st:null)));
                    }



                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:170:8: ^( 'while' expr s= stat )
                    {
                    match(input,45,FOLLOW_45_in_stat335); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat337);
                    expr7=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_stat_in_stat341);
                    s=stat();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 171:8: -> while(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.st)
                    {
                        retval.st = templateLib.getInstanceOf("while",new STAttrMap().put("bexpr", (expr7!=null?expr7.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()).put("stmts", (s!=null?s.st:null)));
                    }



                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:173:9: block
                    {
                    pushFollow(FOLLOW_block_in_stat386);
                    block8=block();

                    state._fsp--;


                    retval.st = (block8!=null?block8.st:null);

                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:174:7: structDecl
                    {
                    pushFollow(FOLLOW_structDecl_in_stat396);
                    structDecl9=structDecl();

                    state._fsp--;


                    retval.structDeclST = (structDecl9!=null?structDecl9.st:null);

                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:175:7: functionDecl
                    {
                    pushFollow(FOLLOW_functionDecl_in_stat406);
                    functionDecl10=functionDecl();

                    state._fsp--;


                    retval.functionDeclsST = (functionDecl10!=null?functionDecl10.st:null);

                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:176:7: returnStat
                    {
                    pushFollow(FOLLOW_returnStat_in_stat416);
                    returnStat11=returnStat();

                    state._fsp--;


                    retval.st = (returnStat11!=null?returnStat11.st:null);

                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stat"


    public static class echo_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "echo"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:179:1: echo : ^( ECHO e= expr ) -> {!(type instanceof StructType)}? echo(expression=$e.sttype=type.toString()) -> echoObject(expression=$e.sttype=type);
    public final JPoclGenerator.echo_return echo() throws RecognitionException {
        JPoclGenerator.echo_return retval = new JPoclGenerator.echo_return();
        retval.start = input.LT(1);


        JPoclGenerator.expr_return e =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:179:6: ( ^( ECHO e= expr ) -> {!(type instanceof StructType)}? echo(expression=$e.sttype=type.toString()) -> echoObject(expression=$e.sttype=type))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:179:8: ^( ECHO e= expr )
            {
            match(input,ECHO,FOLLOW_ECHO_in_echo436); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_echo440);
            e=expr();

            state._fsp--;



            					Type type = (e!=null?((TypeTree)e.start):null).getStaticType();
            					setStackLimit((e!=null?e.stackSize:0) +1);
            				

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 183:5: -> {!(type instanceof StructType)}? echo(expression=$e.sttype=type.toString())
            if (!(type instanceof StructType)) {
                retval.st = templateLib.getInstanceOf("echo",new STAttrMap().put("expression", (e!=null?e.st:null)).put("type", type.toString()));
            }

            else // 184:5: -> echoObject(expression=$e.sttype=type)
            {
                retval.st = templateLib.getInstanceOf("echoObject",new STAttrMap().put("expression", (e!=null?e.st:null)).put("type", type));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "echo"


    public static class block_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "block"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:186:1: block : ^( BLOCK (s+= stat )+ ) -> block(stmts=$s);
    public final JPoclGenerator.block_return block() throws RecognitionException {
        BlockLocals_stack.push(new BlockLocals_scope());

        JPoclGenerator.block_return retval = new JPoclGenerator.block_return();
        retval.start = input.LT(1);


        List list_s=null;
        RuleReturnScope s = null;

        				((BlockLocals_scope)BlockLocals_stack.peek()).locals = new LinkedList<>();
        				((BlockLocals_scope)BlockLocals_stack.peek()).isFunctionBlock = false;
        			
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:192:4: ( ^( BLOCK (s+= stat )+ ) -> block(stmts=$s))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:193:5: ^( BLOCK (s+= stat )+ )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block517); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:193:14: (s+= stat )+
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
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:193:14: s+= stat
            	    {
            	    pushFollow(FOLLOW_stat_in_block521);
            	    s=stat();

            	    state._fsp--;

            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTemplate());


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


            // TEMPLATE REWRITE
            // 193:23: -> block(stmts=$s)
            {
                retval.st = templateLib.getInstanceOf("block",new STAttrMap().put("stmts", list_s));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
            BlockLocals_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "block"


    public static class functionDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "functionDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:197:1: functionDecl : ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? (stmts+= stat )* ) -> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}? functionDeclVoid(visibility=isTopLevel($start) ? \"public\" : \"\"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=$stmtsstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit) -> functionDecl(visibility=isTopLevel($start) ? \"public\" : \"\"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=$stmtsstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit);
    public final JPoclGenerator.functionDecl_return functionDecl() throws RecognitionException {
        BlockLocals_stack.push(new BlockLocals_scope());
        FunctionStack_stack.push(new FunctionStack_scope());

        JPoclGenerator.functionDecl_return retval = new JPoclGenerator.functionDecl_return();
        retval.start = input.LT(1);


        TypeTree ID13=null;
        List list_stmts=null;
        JPoclGenerator.returnFunctionDecl_return returnFunctionDecl12 =null;

        JPoclGenerator.parametersDecl_return parametersDecl14 =null;

        RuleReturnScope stmts = null;

        							((BlockLocals_scope)BlockLocals_stack.peek()).locals = new LinkedList<>();
        							((BlockLocals_scope)BlockLocals_stack.peek()).isFunctionBlock = true;
        						
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:204:7: ( ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? (stmts+= stat )* ) -> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}? functionDeclVoid(visibility=isTopLevel($start) ? \"public\" : \"\"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=$stmtsstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit) -> functionDecl(visibility=isTopLevel($start) ? \"public\" : \"\"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=$stmtsstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:205:7: ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? (stmts+= stat )* )
            {
            match(input,FUNCDECL,FOLLOW_FUNCDECL_in_functionDecl591); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_returnFunctionDecl_in_functionDecl593);
            returnFunctionDecl12=returnFunctionDecl();

            state._fsp--;


            ID13=(TypeTree)match(input,ID,FOLLOW_ID_in_functionDecl595); 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:205:40: ( parametersDecl )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==PDECS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:205:40: parametersDecl
                    {
                    pushFollow(FOLLOW_parametersDecl_in_functionDecl597);
                    parametersDecl14=parametersDecl();

                    state._fsp--;


                    }
                    break;

            }


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:205:61: (stmts+= stat )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= ASSIGN && LA5_0 <= BLOCK)||(LA5_0 >= ECHO && LA5_0 <= FUNCDECL)||LA5_0==40||(LA5_0 >= 42 && LA5_0 <= 43)||LA5_0==45) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:205:61: stmts+= stat
            	    {
            	    pushFollow(FOLLOW_stat_in_functionDecl602);
            	    stmts=stat();

            	    state._fsp--;

            	    if (list_stmts==null) list_stmts=new ArrayList();
            	    list_stmts.add(stmts.getTemplate());


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 206:7: -> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}? functionDeclVoid(visibility=isTopLevel($start) ? \"public\" : \"\"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=$stmtsstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit)
            if (api.get((ID13!=null?ID13.getText():null)).getReturnType().equalsTo(PrimType.VOID)) {
                retval.st = templateLib.getInstanceOf("functionDeclVoid",new STAttrMap().put("visibility", isTopLevel(((TypeTree)retval.start)) ? "public" : "").put("returnType", (returnFunctionDecl12!=null?returnFunctionDecl12.st:null)).put("id", (ID13!=null?ID13.getText():null)).put("parameters", (parametersDecl14!=null?parametersDecl14.st:null)).put("stmts", list_stmts).put("stackLimit", ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit).put("localsLimit", ((BlockLocals_scope)BlockLocals_stack.peek()).localsLimit));
            }

            else // 210:7: -> functionDecl(visibility=isTopLevel($start) ? \"public\" : \"\"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=$stmtsstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit)
            {
                retval.st = templateLib.getInstanceOf("functionDecl",new STAttrMap().put("visibility", isTopLevel(((TypeTree)retval.start)) ? "public" : "").put("returnType", (returnFunctionDecl12!=null?returnFunctionDecl12.st:null)).put("id", (ID13!=null?ID13.getText():null)).put("parameters", (parametersDecl14!=null?parametersDecl14.st:null)).put("stmts", list_stmts).put("stackLimit", ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit).put("localsLimit", ((BlockLocals_scope)BlockLocals_stack.peek()).localsLimit));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
            BlockLocals_stack.pop();
            FunctionStack_stack.pop();

        }
        return retval;
    }
    // $ANTLR end "functionDecl"


    public static class returnFunctionDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "returnFunctionDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:215:1: returnFunctionDecl : ( 'int' -> type(type=PrimType.INT)| 'bool' -> type(type=PrimType.BOOL)| 'void' -> type(type=PrimType.VOID)| ID -> type(type=\"L\"+packageDst+\"/\"+$ID.text+\";\"));
    public final JPoclGenerator.returnFunctionDecl_return returnFunctionDecl() throws RecognitionException {
        JPoclGenerator.returnFunctionDecl_return retval = new JPoclGenerator.returnFunctionDecl_return();
        retval.start = input.LT(1);


        TypeTree ID15=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:215:20: ( 'int' -> type(type=PrimType.INT)| 'bool' -> type(type=PrimType.BOOL)| 'void' -> type(type=PrimType.VOID)| ID -> type(type=\"L\"+packageDst+\"/\"+$ID.text+\";\"))
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:216:12: 'int'
                    {
                    match(input,41,FOLLOW_41_in_returnFunctionDecl790); 

                    // TEMPLATE REWRITE
                    // 216:18: -> type(type=PrimType.INT)
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", PrimType.INT));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:217:13: 'bool'
                    {
                    match(input,39,FOLLOW_39_in_returnFunctionDecl813); 

                    // TEMPLATE REWRITE
                    // 217:20: -> type(type=PrimType.BOOL)
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", PrimType.BOOL));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:218:13: 'void'
                    {
                    match(input,44,FOLLOW_44_in_returnFunctionDecl836); 

                    // TEMPLATE REWRITE
                    // 218:20: -> type(type=PrimType.VOID)
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", PrimType.VOID));
                    }



                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:219:13: ID
                    {
                    ID15=(TypeTree)match(input,ID,FOLLOW_ID_in_returnFunctionDecl859); 

                    // TEMPLATE REWRITE
                    // 219:16: -> type(type=\"L\"+packageDst+\"/\"+$ID.text+\";\")
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", "L"+packageDst+"/"+(ID15!=null?ID15.getText():null)+";"));
                    }



                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "returnFunctionDecl"


    public static class returnStat_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "returnStat"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:222:1: returnStat : ( 'return' -> returnStat(stat=\"return\")| ^( 'return' expr ) -> {$expr.start.getStaticType().equalsTo(PrimType.INT) \r\n\t\t\t\t\t\t\t\t\t\t\t|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}? returnStat(stat=\"ireturn\"expr=$expr.st) -> returnStat(stat=\"areturn\"expr=$expr.st));
    public final JPoclGenerator.returnStat_return returnStat() throws RecognitionException {
        JPoclGenerator.returnStat_return retval = new JPoclGenerator.returnStat_return();
        retval.start = input.LT(1);


        JPoclGenerator.expr_return expr16 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:222:12: ( 'return' -> returnStat(stat=\"return\")| ^( 'return' expr ) -> {$expr.start.getStaticType().equalsTo(PrimType.INT) \r\n\t\t\t\t\t\t\t\t\t\t\t|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}? returnStat(stat=\"ireturn\"expr=$expr.st) -> returnStat(stat=\"areturn\"expr=$expr.st))
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:223:6: 'return'
                    {
                    match(input,42,FOLLOW_42_in_returnStat892); 

                    // TEMPLATE REWRITE
                    // 223:15: -> returnStat(stat=\"return\")
                    {
                        retval.st = templateLib.getInstanceOf("returnStat",new STAttrMap().put("stat", "return"));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:224:8: ^( 'return' expr )
                    {
                    match(input,42,FOLLOW_42_in_returnStat911); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_returnStat913);
                    expr16=expr();

                    state._fsp--;


                    setStackLimit((expr16!=null?expr16.stackSize:0));

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 225:8: -> {$expr.start.getStaticType().equalsTo(PrimType.INT) \r\n\t\t\t\t\t\t\t\t\t\t\t|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}? returnStat(stat=\"ireturn\"expr=$expr.st)
                    if ((expr16!=null?((TypeTree)expr16.start):null).getStaticType().equalsTo(PrimType.INT) 
                    											|| (expr16!=null?((TypeTree)expr16.start):null).getStaticType().equalsTo(PrimType.BOOL)) {
                        retval.st = templateLib.getInstanceOf("returnStat",new STAttrMap().put("stat", "ireturn").put("expr", (expr16!=null?expr16.st:null)));
                    }

                    else // 228:8: -> returnStat(stat=\"areturn\"expr=$expr.st)
                    {
                        retval.st = templateLib.getInstanceOf("returnStat",new STAttrMap().put("stat", "areturn").put("expr", (expr16!=null?expr16.st:null)));
                    }



                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "returnStat"


    public static class parametersDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "parametersDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:231:1: parametersDecl : ^( PDECS (p+= parameterDecl )+ ) -> parametersDecl(parameters=$p);
    public final JPoclGenerator.parametersDecl_return parametersDecl() throws RecognitionException {
        JPoclGenerator.parametersDecl_return retval = new JPoclGenerator.parametersDecl_return();
        retval.start = input.LT(1);


        List list_p=null;
        RuleReturnScope p = null;
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:231:16: ( ^( PDECS (p+= parameterDecl )+ ) -> parametersDecl(parameters=$p))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:232:9: ^( PDECS (p+= parameterDecl )+ )
            {
            match(input,PDECS,FOLLOW_PDECS_in_parametersDecl995); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:232:18: (p+= parameterDecl )+
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
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:232:18: p+= parameterDecl
            	    {
            	    pushFollow(FOLLOW_parameterDecl_in_parametersDecl999);
            	    p=parameterDecl();

            	    state._fsp--;

            	    if (list_p==null) list_p=new ArrayList();
            	    list_p.add(p.getTemplate());


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


            // TEMPLATE REWRITE
            // 232:36: -> parametersDecl(parameters=$p)
            {
                retval.st = templateLib.getInstanceOf("parametersDecl",new STAttrMap().put("parameters", list_p));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parametersDecl"


    public static class parameterDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "parameterDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:235:1: parameterDecl : ( ^( 'int' ID ) -> parameter(parameter=\"I\")| ^( 'bool' ID ) -> parameter(parameter=\"Z\")| ^( 'struct' structType= ID structID= ID ) -> parameter(parameter=\"L\"+packageDst+\"/\"+$structType.text+\";\"));
    public final JPoclGenerator.parameterDecl_return parameterDecl() throws RecognitionException {
        JPoclGenerator.parameterDecl_return retval = new JPoclGenerator.parameterDecl_return();
        retval.start = input.LT(1);


        TypeTree structType=null;
        TypeTree structID=null;
        TypeTree ID17=null;
        TypeTree ID18=null;


        								String pid = "";
        							
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:242:8: ( ^( 'int' ID ) -> parameter(parameter=\"I\")| ^( 'bool' ID ) -> parameter(parameter=\"Z\")| ^( 'struct' structType= ID structID= ID ) -> parameter(parameter=\"L\"+packageDst+\"/\"+$structType.text+\";\"))
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:243:8: ^( 'int' ID )
                    {
                    match(input,41,FOLLOW_41_in_parameterDecl1065); 

                    match(input, Token.DOWN, null); 
                    ID17=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1067); 

                    pid = (ID17!=null?ID17.getText():null);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 243:38: -> parameter(parameter=\"I\")
                    {
                        retval.st = templateLib.getInstanceOf("parameter",new STAttrMap().put("parameter", "I"));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:244:9: ^( 'bool' ID )
                    {
                    match(input,39,FOLLOW_39_in_parameterDecl1090); 

                    match(input, Token.DOWN, null); 
                    ID18=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1092); 

                    pid = (ID18!=null?ID18.getText():null);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 244:40: -> parameter(parameter=\"Z\")
                    {
                        retval.st = templateLib.getInstanceOf("parameter",new STAttrMap().put("parameter", "Z"));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:245:9: ^( 'struct' structType= ID structID= ID )
                    {
                    match(input,43,FOLLOW_43_in_parameterDecl1115); 

                    match(input, Token.DOWN, null); 
                    structType=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1119); 

                    structID=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1123); 

                    pid = (structID!=null?structID.getText():null);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 246:9: -> parameter(parameter=\"L\"+packageDst+\"/\"+$structType.text+\";\")
                    {
                        retval.st = templateLib.getInstanceOf("parameter",new STAttrMap().put("parameter", "L"+packageDst+"/"+(structType!=null?structType.getText():null)+";"));
                    }



                    }
                    break;

            }

            								addVariableToLocals(pid);
            							
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameterDecl"


    public static class structDecl_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "structDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:249:1: structDecl : ^( 'struct' ID (sm+= structMember[visibility] )+ ) -> structDecl(id=$ID.textvisibility=visibilitypackageDst=packageDstconstructor=structType.buildConstructor(visibility, packageDst)member=$smtoStringMethod=structType.buildToString(packageDst)equalsMethod=structType.buildEquals(packageDst));
    public final JPoclGenerator.structDecl_return structDecl() throws RecognitionException {
        JPoclGenerator.structDecl_return retval = new JPoclGenerator.structDecl_return();
        retval.start = input.LT(1);


        TypeTree ID19=null;
        List list_sm=null;
        RuleReturnScope sm = null;

        					 		String visibility;
        					 
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:253:7: ( ^( 'struct' ID (sm+= structMember[visibility] )+ ) -> structDecl(id=$ID.textvisibility=visibilitypackageDst=packageDstconstructor=structType.buildConstructor(visibility, packageDst)member=$smtoStringMethod=structType.buildToString(packageDst)equalsMethod=structType.buildEquals(packageDst)))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:253:8: ^( 'struct' ID (sm+= structMember[visibility] )+ )
            {
            visibility = isTopLevel(((TypeTree)retval.start)) ? "public" : "";

            match(input,43,FOLLOW_43_in_structDecl1186); 

            match(input, Token.DOWN, null); 
            ID19=(TypeTree)match(input,ID,FOLLOW_ID_in_structDecl1188); 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:254:23: (sm+= structMember[visibility] )+
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
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:254:23: sm+= structMember[visibility]
            	    {
            	    pushFollow(FOLLOW_structMember_in_structDecl1192);
            	    sm=structMember(visibility);

            	    state._fsp--;

            	    if (list_sm==null) list_sm=new ArrayList();
            	    list_sm.add(sm.getTemplate());


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



            					 	StructType structType = dataTypes.get((ID19!=null?ID19.getText():null));
            					 

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 257:7: -> structDecl(id=$ID.textvisibility=visibilitypackageDst=packageDstconstructor=structType.buildConstructor(visibility, packageDst)member=$smtoStringMethod=structType.buildToString(packageDst)equalsMethod=structType.buildEquals(packageDst))
            {
                retval.st = templateLib.getInstanceOf("structDecl",new STAttrMap().put("id", (ID19!=null?ID19.getText():null)).put("visibility", visibility).put("packageDst", packageDst).put("constructor", structType.buildConstructor(visibility, packageDst)).put("member", list_sm).put("toStringMethod", structType.buildToString(packageDst)).put("equalsMethod", structType.buildEquals(packageDst)));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structDecl"


    public static class structMember_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "structMember"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:263:1: structMember[String visibility] : ( ^( 'int' ID ) -> structMember(type=\"I\"id=$ID.textvisibility=$visibility)| ^( 'bool' id2= ID ) -> structMember(type=\"Z\"id=$ID.textvisibility=$visibility)| ^( 'struct' structType= ID structID= ID ) -> structMember(type=dataTypes.get($structType.text).getObjectType(packageDst)id=$structID.textvisibility=$visibility));
    public final JPoclGenerator.structMember_return structMember(String visibility) throws RecognitionException {
        JPoclGenerator.structMember_return retval = new JPoclGenerator.structMember_return();
        retval.start = input.LT(1);


        TypeTree id2=null;
        TypeTree structType=null;
        TypeTree structID=null;
        TypeTree ID20=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:263:33: ( ^( 'int' ID ) -> structMember(type=\"I\"id=$ID.textvisibility=$visibility)| ^( 'bool' id2= ID ) -> structMember(type=\"Z\"id=$ID.textvisibility=$visibility)| ^( 'struct' structType= ID structID= ID ) -> structMember(type=dataTypes.get($structType.text).getObjectType(packageDst)id=$structID.textvisibility=$visibility))
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:264:7: ^( 'int' ID )
                    {
                    match(input,41,FOLLOW_41_in_structMember1304); 

                    match(input, Token.DOWN, null); 
                    ID20=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1306); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 264:19: -> structMember(type=\"I\"id=$ID.textvisibility=$visibility)
                    {
                        retval.st = templateLib.getInstanceOf("structMember",new STAttrMap().put("type", "I").put("id", (ID20!=null?ID20.getText():null)).put("visibility", visibility));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:265:9: ^( 'bool' id2= ID )
                    {
                    match(input,39,FOLLOW_39_in_structMember1337); 

                    match(input, Token.DOWN, null); 
                    id2=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1341); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 265:26: -> structMember(type=\"Z\"id=$ID.textvisibility=$visibility)
                    {
                        retval.st = templateLib.getInstanceOf("structMember",new STAttrMap().put("type", "Z").put("id", (id2!=null?id2.getText():null)).put("visibility", visibility));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:266:9: ^( 'struct' structType= ID structID= ID )
                    {
                    match(input,43,FOLLOW_43_in_structMember1372); 

                    match(input, Token.DOWN, null); 
                    structType=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1376); 

                    structID=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1380); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 267:8: -> structMember(type=dataTypes.get($structType.text).getObjectType(packageDst)id=$structID.textvisibility=$visibility)
                    {
                        retval.st = templateLib.getInstanceOf("structMember",new STAttrMap().put("type", dataTypes.get((structType!=null?structType.getText():null)).getObjectType(packageDst)).put("id", (structID!=null?structID.getText():null)).put("visibility", visibility));
                    }



                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structMember"


    public static class expr_return extends TreeRuleReturnScope {
        public int stackSize;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "expr"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:271:1: expr returns [int stackSize] : ( ^( '^' t1= expr t2= expr ) -> pow(leftExpr=$t1.strightExpr=$t2.st)| ^( '+' t1= expr t2= expr ) -> add(leftExpr=$t1.strightExpr=$t2.st)| ^( '-' t1= expr t2= expr ) -> sub(leftExpr=$t1.strightExpr=$t2.st)| ^( '%' t1= expr t2= expr ) -> remainder(leftExpr=$t1.strightExpr=$t2.st)| ^( '*' t1= expr t2= expr ) -> mul(leftExpr=$t1.strightExpr=$t2.st)| ^( '/' t1= expr t2= expr ) -> div(leftExpr=$t1.strightExpr=$t2.st)| ^( '!' t= expr ) -> fact(expr=$t.stpackageDst=packageDst)| ^( '~' t= expr ) -> not(expr=$t.sttokenNum=$start.getIndex()packageDst=packageDst)| ^( PLUS t= expr ) -> unaryPlus(expr=$t.st)| ^( SIGN t= expr ) -> unaryMinus(expr=$t.st)| ^( '<' t1= expr t2= expr ) -> less(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '<=' t1= expr t2= expr ) -> lessEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>' t1= expr t2= expr ) -> greater(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>=' t1= expr t2= expr ) -> greaterEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| equals | ^( '&&' t1= expr t2= expr ) -> and(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '||' t1= expr t2= expr ) -> or(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( DOT ( functionCall | structValue ) qualifiedID ) -> oneTimeAccess(function=$functionCall.ststructValue=$structValue.sttype=typeqid=$qualifiedID.st)| functionCall | structValue | qualifiedID | INT -> constant(value=$INT.text)| BOOLEAN -> constant(value=$BOOLEAN.text.equals(\"true\") ? \"1\" : \"0\"));
    public final JPoclGenerator.expr_return expr() throws RecognitionException {
        JPoclGenerator.expr_return retval = new JPoclGenerator.expr_return();
        retval.start = input.LT(1);


        TypeTree INT28=null;
        TypeTree BOOLEAN29=null;
        JPoclGenerator.expr_return t1 =null;

        JPoclGenerator.expr_return t2 =null;

        JPoclGenerator.expr_return t =null;

        JPoclGenerator.equals_return equals21 =null;

        JPoclGenerator.functionCall_return functionCall22 =null;

        JPoclGenerator.structValue_return structValue23 =null;

        JPoclGenerator.qualifiedID_return qualifiedID24 =null;

        JPoclGenerator.functionCall_return functionCall25 =null;

        JPoclGenerator.structValue_return structValue26 =null;

        JPoclGenerator.qualifiedID_return qualifiedID27 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:272:5: ( ^( '^' t1= expr t2= expr ) -> pow(leftExpr=$t1.strightExpr=$t2.st)| ^( '+' t1= expr t2= expr ) -> add(leftExpr=$t1.strightExpr=$t2.st)| ^( '-' t1= expr t2= expr ) -> sub(leftExpr=$t1.strightExpr=$t2.st)| ^( '%' t1= expr t2= expr ) -> remainder(leftExpr=$t1.strightExpr=$t2.st)| ^( '*' t1= expr t2= expr ) -> mul(leftExpr=$t1.strightExpr=$t2.st)| ^( '/' t1= expr t2= expr ) -> div(leftExpr=$t1.strightExpr=$t2.st)| ^( '!' t= expr ) -> fact(expr=$t.stpackageDst=packageDst)| ^( '~' t= expr ) -> not(expr=$t.sttokenNum=$start.getIndex()packageDst=packageDst)| ^( PLUS t= expr ) -> unaryPlus(expr=$t.st)| ^( SIGN t= expr ) -> unaryMinus(expr=$t.st)| ^( '<' t1= expr t2= expr ) -> less(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '<=' t1= expr t2= expr ) -> lessEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>' t1= expr t2= expr ) -> greater(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>=' t1= expr t2= expr ) -> greaterEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| equals | ^( '&&' t1= expr t2= expr ) -> and(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '||' t1= expr t2= expr ) -> or(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( DOT ( functionCall | structValue ) qualifiedID ) -> oneTimeAccess(function=$functionCall.ststructValue=$structValue.sttype=typeqid=$qualifiedID.st)| functionCall | structValue | qualifiedID | INT -> constant(value=$INT.text)| BOOLEAN -> constant(value=$BOOLEAN.text.equals(\"true\") ? \"1\" : \"0\"))
            int alt13=23;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt13=1;
                }
                break;
            case 27:
                {
                alt13=2;
                }
                break;
            case 29:
                {
                alt13=3;
                }
                break;
            case 22:
                {
                alt13=4;
                }
                break;
            case 26:
                {
                alt13=5;
                }
                break;
            case 30:
                {
                alt13=6;
                }
                break;
            case 21:
                {
                alt13=7;
                }
                break;
            case 49:
                {
                alt13=8;
                }
                break;
            case PLUS:
                {
                alt13=9;
                }
                break;
            case SIGN:
                {
                alt13=10;
                }
                break;
            case 32:
                {
                alt13=11;
                }
                break;
            case 33:
                {
                alt13=12;
                }
                break;
            case 36:
                {
                alt13=13;
                }
                break;
            case 37:
                {
                alt13=14;
                }
                break;
            case 35:
                {
                alt13=15;
                }
                break;
            case 23:
                {
                alt13=16;
                }
                break;
            case 47:
                {
                alt13=17;
                }
                break;
            case DOT:
                {
                int LA13_18 = input.LA(2);

                if ( (LA13_18==DOWN) ) {
                    int LA13_24 = input.LA(3);

                    if ( (LA13_24==FUNCCALL||LA13_24==43) ) {
                        alt13=18;
                    }
                    else if ( (LA13_24==ID) ) {
                        alt13=21;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 24, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 18, input);

                    throw nvae;

                }
                }
                break;
            case FUNCCALL:
                {
                alt13=19;
                }
                break;
            case 43:
                {
                alt13=20;
                }
                break;
            case ID:
                {
                alt13=21;
                }
                break;
            case INT:
                {
                alt13=22;
                }
                break;
            case BOOLEAN:
                {
                alt13=23;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:273:6: ^( '^' t1= expr t2= expr )
                    {
                    match(input,38,FOLLOW_38_in_expr1448); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1452);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1456);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize, 3);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 274:6: -> pow(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("pow",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:276:7: ^( '+' t1= expr t2= expr )
                    {
                    match(input,27,FOLLOW_27_in_expr1492); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1496);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1500);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 277:7: -> add(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("add",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:279:7: ^( '-' t1= expr t2= expr )
                    {
                    match(input,29,FOLLOW_29_in_expr1538); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1542);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1546);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 280:6: -> sub(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("sub",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:282:7: ^( '%' t1= expr t2= expr )
                    {
                    match(input,22,FOLLOW_22_in_expr1582); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1586);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1590);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 283:6: -> remainder(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("remainder",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:285:7: ^( '*' t1= expr t2= expr )
                    {
                    match(input,26,FOLLOW_26_in_expr1626); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1630);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1634);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 286:7: -> mul(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("mul",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:288:7: ^( '/' t1= expr t2= expr )
                    {
                    match(input,30,FOLLOW_30_in_expr1672); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1676);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1680);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 289:7: -> div(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("div",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:291:8: ^( '!' t= expr )
                    {
                    match(input,21,FOLLOW_21_in_expr1719); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1723);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 292:7: -> fact(expr=$t.stpackageDst=packageDst)
                    {
                        retval.st = templateLib.getInstanceOf("fact",new STAttrMap().put("expr", (t!=null?t.st:null)).put("packageDst", packageDst));
                    }



                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:294:7: ^( '~' t= expr )
                    {
                    match(input,49,FOLLOW_49_in_expr1762); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1766);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 295:6: -> not(expr=$t.sttokenNum=$start.getIndex()packageDst=packageDst)
                    {
                        retval.st = templateLib.getInstanceOf("not",new STAttrMap().put("expr", (t!=null?t.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()).put("packageDst", packageDst));
                    }



                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:297:8: ^( PLUS t= expr )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expr1808); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1812);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 298:8: -> unaryPlus(expr=$t.st)
                    {
                        retval.st = templateLib.getInstanceOf("unaryPlus",new STAttrMap().put("expr", (t!=null?t.st:null)));
                    }



                    }
                    break;
                case 10 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:300:8: ^( SIGN t= expr )
                    {
                    match(input,SIGN,FOLLOW_SIGN_in_expr1849); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1853);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 301:8: -> unaryMinus(expr=$t.st)
                    {
                        retval.st = templateLib.getInstanceOf("unaryMinus",new STAttrMap().put("expr", (t!=null?t.st:null)));
                    }



                    }
                    break;
                case 11 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:303:7: ^( '<' t1= expr t2= expr )
                    {
                    match(input,32,FOLLOW_32_in_expr1889); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1893);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1897);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 304:6: -> less(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("less",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 12 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:306:7: ^( '<=' t1= expr t2= expr )
                    {
                    match(input,33,FOLLOW_33_in_expr1937); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1941);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1945);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 307:7: -> lessEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("lessEqual",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 13 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:309:7: ^( '>' t1= expr t2= expr )
                    {
                    match(input,36,FOLLOW_36_in_expr1986); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1990);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1994);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 310:7: -> greater(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("greater",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 14 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:312:7: ^( '>=' t1= expr t2= expr )
                    {
                    match(input,37,FOLLOW_37_in_expr2035); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2039);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2043);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 313:7: -> greaterEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("greaterEqual",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 15 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:315:7: equals
                    {
                    pushFollow(FOLLOW_equals_in_expr2083);
                    equals21=equals();

                    state._fsp--;


                    retval.st = (equals21!=null?equals21.st:null); retval.stackSize = (equals21!=null?equals21.stackSize:0);

                    }
                    break;
                case 16 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:317:7: ^( '&&' t1= expr t2= expr )
                    {
                    match(input,23,FOLLOW_23_in_expr2099); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2103);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2107);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 318:6: -> and(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("and",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 17 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:320:7: ^( '||' t1= expr t2= expr )
                    {
                    match(input,47,FOLLOW_47_in_expr2147); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2151);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2155);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 321:6: -> or(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("or",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 18 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:323:7: ^( DOT ( functionCall | structValue ) qualifiedID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_expr2195); 

                    Type type = null;

                    match(input, Token.DOWN, null); 
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:324:7: ( functionCall | structValue )
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
                            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:324:8: functionCall
                            {
                            pushFollow(FOLLOW_functionCall_in_expr2206);
                            functionCall22=functionCall();

                            state._fsp--;


                            type = (functionCall22!=null?((TypeTree)functionCall22.start):null).getStaticType(); retval.stackSize = (functionCall22!=null?functionCall22.stackSize:0);

                            }
                            break;
                        case 2 :
                            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:325:9: structValue
                            {
                            pushFollow(FOLLOW_structValue_in_expr2218);
                            structValue23=structValue();

                            state._fsp--;


                            type = (structValue23!=null?((TypeTree)structValue23.start):null).getStaticType(); retval.stackSize = (functionCall22!=null?functionCall22.stackSize:0);

                            }
                            break;

                    }


                    pushFollow(FOLLOW_qualifiedID_in_expr2230);
                    qualifiedID24=qualifiedID();

                    state._fsp--;


                    retval.stackSize =  Math.max(retval.stackSize,1);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 327:7: -> oneTimeAccess(function=$functionCall.ststructValue=$structValue.sttype=typeqid=$qualifiedID.st)
                    {
                        retval.st = templateLib.getInstanceOf("oneTimeAccess",new STAttrMap().put("function", (functionCall22!=null?functionCall22.st:null)).put("structValue", (structValue23!=null?structValue23.st:null)).put("type", type).put("qid", (qualifiedID24!=null?qualifiedID24.st:null)));
                    }



                    }
                    break;
                case 19 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:330:7: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_expr2304);
                    functionCall25=functionCall();

                    state._fsp--;


                    retval.st = (functionCall25!=null?functionCall25.st:null); retval.stackSize = (functionCall25!=null?functionCall25.stackSize:0);

                    }
                    break;
                case 20 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:332:7: structValue
                    {
                    pushFollow(FOLLOW_structValue_in_expr2319);
                    structValue26=structValue();

                    state._fsp--;


                    retval.st = (structValue26!=null?structValue26.st:null); retval.stackSize = (structValue26!=null?structValue26.stackSize:0);

                    }
                    break;
                case 21 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:334:7: qualifiedID
                    {
                    pushFollow(FOLLOW_qualifiedID_in_expr2334);
                    qualifiedID27=qualifiedID();

                    state._fsp--;


                    retval.st = (qualifiedID27!=null?qualifiedID27.st:null); retval.stackSize = 1;

                    }
                    break;
                case 22 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:336:8: INT
                    {
                    INT28=(TypeTree)match(input,INT,FOLLOW_INT_in_expr2350); 

                    retval.stackSize = 1;

                    // TEMPLATE REWRITE
                    // 337:7: -> constant(value=$INT.text)
                    {
                        retval.st = templateLib.getInstanceOf("constant",new STAttrMap().put("value", (INT28!=null?INT28.getText():null)));
                    }



                    }
                    break;
                case 23 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:338:7: BOOLEAN
                    {
                    BOOLEAN29=(TypeTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expr2375); 

                    retval.stackSize = 1;

                    // TEMPLATE REWRITE
                    // 339:6: -> constant(value=$BOOLEAN.text.equals(\"true\") ? \"1\" : \"0\")
                    {
                        retval.st = templateLib.getInstanceOf("constant",new STAttrMap().put("value", (BOOLEAN29!=null?BOOLEAN29.getText():null).equals("true") ? "1" : "0"));
                    }



                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class equals_return extends TreeRuleReturnScope {
        public int stackSize;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "equals"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:342:1: equals returns [int stackSize] : ^( '==' t1= expr t2= expr ) -> {areStructs}? equalsStruct(leftExpr=$t1.strightExpr=$t2.st) -> equals(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex());
    public final JPoclGenerator.equals_return equals() throws RecognitionException {
        JPoclGenerator.equals_return retval = new JPoclGenerator.equals_return();
        retval.start = input.LT(1);


        JPoclGenerator.expr_return t1 =null;

        JPoclGenerator.expr_return t2 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:342:31: ( ^( '==' t1= expr t2= expr ) -> {areStructs}? equalsStruct(leftExpr=$t1.strightExpr=$t2.st) -> equals(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex()))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:343:5: ^( '==' t1= expr t2= expr )
            {
            match(input,35,FOLLOW_35_in_equals2424); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_equals2428);
            t1=expr();

            state._fsp--;


            pushFollow(FOLLOW_expr_in_equals2432);
            t2=expr();

            state._fsp--;


             boolean areStructs = (t1!=null?((TypeTree)t1.start):null).getStaticType() instanceof StructType;
            			 		retval.stackSize = calculateStackLimit((t1!=null?t1.stackSize:0), (t2!=null?t2.stackSize:0));			 								 
            			 	

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 348:5: -> {areStructs}? equalsStruct(leftExpr=$t1.strightExpr=$t2.st)
            if (areStructs) {
                retval.st = templateLib.getInstanceOf("equalsStruct",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
            }

            else // 350:5: -> equals(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
            {
                retval.st = templateLib.getInstanceOf("equals",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "equals"


    public static class functionCall_return extends TreeRuleReturnScope {
        public int stackSize;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "functionCall"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:353:1: functionCall returns [int stackSize] : ^( FUNCCALL ID (e+= expr )* ) -> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}? functionCall(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e) -> functionCallAndPop(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e);
    public final JPoclGenerator.functionCall_return functionCall() throws RecognitionException {
        JPoclGenerator.functionCall_return retval = new JPoclGenerator.functionCall_return();
        retval.start = input.LT(1);


        TypeTree ID30=null;
        List list_e=null;
        RuleReturnScope e = null;
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:354:7: ( ^( FUNCCALL ID (e+= expr )* ) -> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}? functionCall(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e) -> functionCallAndPop(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:355:7: ^( FUNCCALL ID (e+= expr )* )
            {
            match(input,FUNCCALL,FOLLOW_FUNCCALL_in_functionCall2534); 

            match(input, Token.DOWN, null); 
            ID30=(TypeTree)match(input,ID,FOLLOW_ID_in_functionCall2536); 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:355:22: (e+= expr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==BOOLEAN||LA14_0==DOT||LA14_0==FUNCCALL||(LA14_0 >= ID && LA14_0 <= INT)||(LA14_0 >= PLUS && LA14_0 <= SIGN)||(LA14_0 >= 21 && LA14_0 <= 23)||(LA14_0 >= 26 && LA14_0 <= 27)||(LA14_0 >= 29 && LA14_0 <= 30)||(LA14_0 >= 32 && LA14_0 <= 33)||(LA14_0 >= 35 && LA14_0 <= 38)||LA14_0==43||LA14_0==47||LA14_0==49) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:355:23: e+= expr
            	    {
            	    pushFollow(FOLLOW_expr_in_functionCall2542);
            	    e=expr();

            	    state._fsp--;

            	    if (list_e==null) list_e=new ArrayList();
            	    list_e.add(e.getTemplate());


            	    retval.stackSize += ((expr_return)e).stackSize;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);



            							FunctionType functionType = api.get((ID30!=null?ID30.getText():null));
            							Type returnType = functionType.getReturnType();
            							if(!returnType.equalsTo(PrimType.VOID)){
            								retval.stackSize += 1;
            							}
            							String returnTypeRappresentation = returnType.getObjectType(packageDst);
            						

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 363:7: -> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}? functionCall(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e)
            if (hasReturnValueConsumed(((TypeTree)retval.start)) || returnType.equalsTo(PrimType.VOID)) {
                retval.st = templateLib.getInstanceOf("functionCall",new STAttrMap().put("package", packageDst).put("fid", (ID30!=null?ID30.getText():null)).put("returnType", returnTypeRappresentation).put("signature", functionType.getParametersSignature(packageDst)).put("expr", list_e));
            }

            else // 367:7: -> functionCallAndPop(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e)
            {
                retval.st = templateLib.getInstanceOf("functionCallAndPop",new STAttrMap().put("package", packageDst).put("fid", (ID30!=null?ID30.getText():null)).put("returnType", returnTypeRappresentation).put("signature", functionType.getParametersSignature(packageDst)).put("expr", list_e));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionCall"


    public static class structValue_return extends TreeRuleReturnScope {
        public int stackSize;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "structValue"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:372:1: structValue returns [int stackSize] : ^( 'struct' ID (e+= expr )+ ) -> structValue(id=$ID.textpackageDst=packageDstconstructorSignature=dataTypes.get($ID.text).getFieldsSignature(packageDst)expr=$e);
    public final JPoclGenerator.structValue_return structValue() throws RecognitionException {
        JPoclGenerator.structValue_return retval = new JPoclGenerator.structValue_return();
        retval.start = input.LT(1);


        TypeTree ID31=null;
        List list_e=null;
        RuleReturnScope e = null;
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:376:7: ( ^( 'struct' ID (e+= expr )+ ) -> structValue(id=$ID.textpackageDst=packageDstconstructorSignature=dataTypes.get($ID.text).getFieldsSignature(packageDst)expr=$e))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:377:8: ^( 'struct' ID (e+= expr )+ )
            {
            match(input,43,FOLLOW_43_in_structValue2725); 

            match(input, Token.DOWN, null); 
            ID31=(TypeTree)match(input,ID,FOLLOW_ID_in_structValue2727); 

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:377:22: (e+= expr )+
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
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:377:23: e+= expr
            	    {
            	    pushFollow(FOLLOW_expr_in_structValue2732);
            	    e=expr();

            	    state._fsp--;

            	    if (list_e==null) list_e=new ArrayList();
            	    list_e.add(e.getTemplate());


            	    retval.stackSize += ((expr_return)e).stackSize;

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


            // TEMPLATE REWRITE
            // 378:8: -> structValue(id=$ID.textpackageDst=packageDstconstructorSignature=dataTypes.get($ID.text).getFieldsSignature(packageDst)expr=$e)
            {
                retval.st = templateLib.getInstanceOf("structValue",new STAttrMap().put("id", (ID31!=null?ID31.getText():null)).put("packageDst", packageDst).put("constructorSignature", dataTypes.get((ID31!=null?ID31.getText():null)).getFieldsSignature(packageDst)).put("expr", list_e));
            }



            }


            							retval.stackSize = retval.stackSize+2;
            						
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "structValue"


    public static class qualifiedID_return extends TreeRuleReturnScope {
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "qualifiedID"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:382:1: qualifiedID : ( id | ^( DOT id qid= qualifiedID ) -> {$start.parent.token.getType() != DOT}? qualifiedIDAndLoad(qualifiedID=$qid.stlocalNum=getVariableLocalNumber($id.text)) -> qualifiedID(id=$id.stqualifiedID=$qid.st));
    public final JPoclGenerator.qualifiedID_return qualifiedID() throws RecognitionException {
        JPoclGenerator.qualifiedID_return retval = new JPoclGenerator.qualifiedID_return();
        retval.start = input.LT(1);


        JPoclGenerator.qualifiedID_return qid =null;

        JPoclGenerator.id_return id32 =null;

        JPoclGenerator.id_return id33 =null;


        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:382:13: ( id | ^( DOT id qid= qualifiedID ) -> {$start.parent.token.getType() != DOT}? qualifiedIDAndLoad(qualifiedID=$qid.stlocalNum=getVariableLocalNumber($id.text)) -> qualifiedID(id=$id.stqualifiedID=$qid.st))
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:383:7: id
                    {
                    pushFollow(FOLLOW_id_in_qualifiedID2802);
                    id32=id();

                    state._fsp--;


                    retval.st = (id32!=null?id32.st:null);

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:384:9: ^( DOT id qid= qualifiedID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_qualifiedID2815); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_id_in_qualifiedID2817);
                    id33=id();

                    state._fsp--;


                    pushFollow(FOLLOW_qualifiedID_in_qualifiedID2821);
                    qid=qualifiedID();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 385:8: -> {$start.parent.token.getType() != DOT}? qualifiedIDAndLoad(qualifiedID=$qid.stlocalNum=getVariableLocalNumber($id.text))
                    if (((TypeTree)retval.start).parent.token.getType() != DOT) {
                        retval.st = templateLib.getInstanceOf("qualifiedIDAndLoad",new STAttrMap().put("qualifiedID", (qid!=null?qid.st:null)).put("localNum", getVariableLocalNumber((id33!=null?id33.text:null))));
                    }

                    else // 387:8: -> qualifiedID(id=$id.stqualifiedID=$qid.st)
                    {
                        retval.st = templateLib.getInstanceOf("qualifiedID",new STAttrMap().put("id", (id33!=null?id33.st:null)).put("qualifiedID", (qid!=null?qid.st:null)));
                    }



                    }
                    break;

            }
        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "qualifiedID"


    public static class id_return extends TreeRuleReturnScope {
        public String text;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "id"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:390:1: id returns [String text] : ID -> {!isRootDOT && isLeftValue}? store(store=type.getStorer()localNum=getVariableLocalNumber($ID.text)) -> {!isRootDOT}? load(load=type.getLoader()localNum=getVariableLocalNumber($ID.text)) -> {isLeftValue}? putField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst)) -> getField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst));
    public final JPoclGenerator.id_return id() throws RecognitionException {
        JPoclGenerator.id_return retval = new JPoclGenerator.id_return();
        retval.start = input.LT(1);


        TypeTree ID34=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:390:26: ( ID -> {!isRootDOT && isLeftValue}? store(store=type.getStorer()localNum=getVariableLocalNumber($ID.text)) -> {!isRootDOT}? load(load=type.getLoader()localNum=getVariableLocalNumber($ID.text)) -> {isLeftValue}? putField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst)) -> getField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst)))
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclGenerator.g:390:28: ID
            {
            ID34=(TypeTree)match(input,ID,FOLLOW_ID_in_id2900); 

            retval.text = (ID34!=null?ID34.getText():null);


            							if(((TypeTree)retval.start).parent.token.getType() != DOT && !isDeclared(retval.text)){
            								addVariableToLocals((ID34!=null?ID34.getText():null));
            							}
            							boolean isRootDOT = ((TypeTree)retval.start).parent.token.getType() == DOT,
            											isLeftValue = isLeftValue(((TypeTree)retval.start));
            											
            							Type siblingType = null, type = ((TypeTree)retval.start).getStaticType();
            							if(isRootDOT){
            								siblingType = ((TypeTree)retval.start).childIndex == 0 ?
            															((TypeTree)((TypeTree)retval.start).parent.parent.getChild(0)).getStaticType() :
            															((TypeTree)((TypeTree)retval.start).parent.getChild(0)).getStaticType();
            							}
            						

            // TEMPLATE REWRITE
            // 403:9: -> {!isRootDOT && isLeftValue}? store(store=type.getStorer()localNum=getVariableLocalNumber($ID.text))
            if (!isRootDOT && isLeftValue) {
                retval.st = templateLib.getInstanceOf("store",new STAttrMap().put("store", type.getStorer()).put("localNum", getVariableLocalNumber((ID34!=null?ID34.getText():null))));
            }

            else // 405:8: -> {!isRootDOT}? load(load=type.getLoader()localNum=getVariableLocalNumber($ID.text))
            if (!isRootDOT) {
                retval.st = templateLib.getInstanceOf("load",new STAttrMap().put("load", type.getLoader()).put("localNum", getVariableLocalNumber((ID34!=null?ID34.getText():null))));
            }

            else // 407:8: -> {isLeftValue}? putField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst))
            if (isLeftValue) {
                retval.st = templateLib.getInstanceOf("putField",new STAttrMap().put("field", (ID34!=null?ID34.getText():null)).put("struct", siblingType).put("type", type.getObjectType(packageDst)));
            }

            else // 409:8: -> getField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst))
            {
                retval.st = templateLib.getInstanceOf("getField",new STAttrMap().put("field", (ID34!=null?ID34.getText():null)).put("struct", siblingType).put("type", type.getObjectType(packageDst)));
            }



            }

        }

        catch (RecognitionException re) {
        	throw re;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "id"

    // Delegated rules


 

    public static final BitSet FOLLOW_CALC_in_calc96 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_calc99 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_echo_in_stat184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_stat197 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat199 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_stat201 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functionCall_in_stat266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_stat282 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat284 = new BitSet(new long[]{0x00002D0000000E30L});
    public static final BitSet FOLLOW_stat_in_stat288 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_45_in_stat335 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat337 = new BitSet(new long[]{0x00002D0000000E30L});
    public static final BitSet FOLLOW_stat_in_stat341 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_stat386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structDecl_in_stat396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDecl_in_stat406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStat_in_stat416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ECHO_in_echo436 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_echo440 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block517 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_block521 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_FUNCDECL_in_functionDecl591 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_returnFunctionDecl_in_functionDecl593 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_functionDecl595 = new BitSet(new long[]{0x00002D0000008E38L});
    public static final BitSet FOLLOW_parametersDecl_in_functionDecl597 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_stat_in_functionDecl602 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_41_in_returnFunctionDecl790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_returnFunctionDecl813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_returnFunctionDecl836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_returnFunctionDecl859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStat892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStat911 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_returnStat913 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PDECS_in_parametersDecl995 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_parameterDecl_in_parametersDecl999 = new BitSet(new long[]{0x00000A8000000008L});
    public static final BitSet FOLLOW_41_in_parameterDecl1065 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1067 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_parameterDecl1090 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1092 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_parameterDecl1115 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1119 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1123 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_structDecl1186 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structDecl1188 = new BitSet(new long[]{0x00000A8000000000L});
    public static final BitSet FOLLOW_structMember_in_structDecl1192 = new BitSet(new long[]{0x00000A8000000008L});
    public static final BitSet FOLLOW_41_in_structMember1304 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember1306 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_structMember1337 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember1341 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_structMember1372 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember1376 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember1380 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_38_in_expr1448 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1452 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1456 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_27_in_expr1492 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1496 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1500 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_29_in_expr1538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1542 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1546 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_22_in_expr1582 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1586 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1590 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_26_in_expr1626 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1630 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1634 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_30_in_expr1672 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1676 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1680 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_21_in_expr1719 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1723 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_49_in_expr1762 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1766 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expr1808 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1812 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SIGN_in_expr1849 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1853 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_expr1889 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1893 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1897 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_33_in_expr1937 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1941 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1945 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_expr1986 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1990 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1994 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_37_in_expr2035 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2039 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2043 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_equals_in_expr2083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_expr2099 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2103 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2107 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_47_in_expr2147 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2151 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2155 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_expr2195 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_expr2206 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_structValue_in_expr2218 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_expr2230 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functionCall_in_expr2304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structValue_in_expr2319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedID_in_expr2334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_expr2350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_expr2375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_equals2424 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_equals2428 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_equals2432 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCCALL_in_functionCall2534 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_functionCall2536 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_expr_in_functionCall2542 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_43_in_structValue2725 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structValue2727 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_structValue2732 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_id_in_qualifiedID2802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_qualifiedID2815 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_id_in_qualifiedID2817 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_qualifiedID2821 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_id2900 = new BitSet(new long[]{0x0000000000000002L});

}