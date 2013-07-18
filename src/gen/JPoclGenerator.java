// $ANTLR 3.4 C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g 2013-07-18 21:55:14

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
    public String getGrammarFileName() { return "C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g"; }


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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:127:1: calc[String packageDst, LinkedHashMap<String,FunctionType> api, LinkedHashMap<String,StructType> dataTypes] : ^( CALC ( stat )+ ) -> calc(stackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimitstmts=stringTemplatesstructDecls=structDeclsSTfunctionDecls=functionDeclsSTpackageDst=packageDst);
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
        			List<Object> stringTemplates = new LinkedList<Object>();			
        		
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:141:3: ( ^( CALC ( stat )+ ) -> calc(stackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimitstmts=stringTemplatesstructDecls=structDeclsSTfunctionDecls=functionDeclsSTpackageDst=packageDst))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:141:5: ^( CALC ( stat )+ )
            {
            match(input,CALC,FOLLOW_CALC_in_calc96); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:141:12: ( stat )+
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
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:141:13: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_calc99);
            	    stat1=stat();

            	    state._fsp--;



            	    					structDeclsST.addAll((stat1!=null?stat1.structDeclST:null));
            	    					functionDeclsST.addAll((stat1!=null?stat1.functionDeclsST:null));
            	    					stringTemplates.addAll((stat1!=null?stat1.stringTemplates:null));
            	    				

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
            // 146:3: -> calc(stackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimitstmts=stringTemplatesstructDecls=structDeclsSTfunctionDecls=functionDeclsSTpackageDst=packageDst)
            {
                retval.st = templateLib.getInstanceOf("calc",new STAttrMap().put("stackLimit", ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit).put("localsLimit", ((BlockLocals_scope)BlockLocals_stack.peek()).localsLimit).put("stmts", stringTemplates).put("structDecls", structDeclsST).put("functionDecls", functionDeclsST).put("packageDst", packageDst));
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
        public List<Object> structDeclST;
        public List<Object> functionDeclsST;
        public List<Object> stringTemplates;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "stat"
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:152:1: stat returns [List<Object> structDeclST, List<Object> functionDeclsST, List<Object> stringTemplates] : ( echo | ^( ASSIGN expr qualifiedID ) -> {$qualifiedID.start.getStaticType() instanceof StructType}? assignToObject(expr=$expr.stvariable=$qualifiedID.sttype=$qualifiedID.start.getStaticType()) -> assign(expr=$expr.stvariable=$qualifiedID.st)| functionCall | ^( 'if' expr s= stat ) -> if(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.stringTemplates)| ^( 'while' expr s= stat ) -> while(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.stringTemplates)| block | structDecl | functionDecl | returnStat );
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



        				retval.structDeclST = new LinkedList<>();
        				retval.functionDeclsST = new LinkedList<>();
        				retval.stringTemplates = new LinkedList<>();
        			
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:162:4: ( echo | ^( ASSIGN expr qualifiedID ) -> {$qualifiedID.start.getStaticType() instanceof StructType}? assignToObject(expr=$expr.stvariable=$qualifiedID.sttype=$qualifiedID.start.getStaticType()) -> assign(expr=$expr.stvariable=$qualifiedID.st)| functionCall | ^( 'if' expr s= stat ) -> if(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.stringTemplates)| ^( 'while' expr s= stat ) -> while(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.stringTemplates)| block | structDecl | functionDecl | returnStat )
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:163:4: echo
                    {
                    pushFollow(FOLLOW_echo_in_stat198);
                    echo2=echo();

                    state._fsp--;


                    retval.stringTemplates.add((echo2!=null?echo2.st:null));

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:165:5: ^( ASSIGN expr qualifiedID )
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_stat211); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat213);
                    expr3=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_qualifiedID_in_stat215);
                    qualifiedID4=qualifiedID();

                    state._fsp--;


                    setStackLimit((expr3!=null?expr3.stackSize:0) + 1);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 166:5: -> {$qualifiedID.start.getStaticType() instanceof StructType}? assignToObject(expr=$expr.stvariable=$qualifiedID.sttype=$qualifiedID.start.getStaticType())
                    if ((qualifiedID4!=null?((TypeTree)qualifiedID4.start):null).getStaticType() instanceof StructType) {
                        retval.st = templateLib.getInstanceOf("assignToObject",new STAttrMap().put("expr", (expr3!=null?expr3.st:null)).put("variable", (qualifiedID4!=null?qualifiedID4.st:null)).put("type", (qualifiedID4!=null?((TypeTree)qualifiedID4.start):null).getStaticType()));
                    }

                    else // 168:5: -> assign(expr=$expr.stvariable=$qualifiedID.st)
                    {
                        retval.st = templateLib.getInstanceOf("assign",new STAttrMap().put("expr", (expr3!=null?expr3.st:null)).put("variable", (qualifiedID4!=null?qualifiedID4.st:null)));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:170:6: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_stat280);
                    functionCall5=functionCall();

                    state._fsp--;


                    retval.stringTemplates.add((functionCall5!=null?functionCall5.st:null)); setStackLimit((functionCall5!=null?functionCall5.stackSize:0));

                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:172:8: ^( 'if' expr s= stat )
                    {
                    match(input,40,FOLLOW_40_in_stat296); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat298);
                    expr6=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_stat_in_stat302);
                    s=stat();

                    state._fsp--;


                    setStackLimit((expr6!=null?expr6.stackSize:0));

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 173:8: -> if(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.stringTemplates)
                    {
                        retval.st = templateLib.getInstanceOf("if",new STAttrMap().put("bexpr", (expr6!=null?expr6.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()).put("stmts", (s!=null?s.stringTemplates:null)));
                    }



                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:175:8: ^( 'while' expr s= stat )
                    {
                    match(input,45,FOLLOW_45_in_stat349); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_stat351);
                    expr7=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_stat_in_stat355);
                    s=stat();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 176:8: -> while(bexpr=$expr.sttokenNum=$start.getIndex()stmts=$s.stringTemplates)
                    {
                        retval.st = templateLib.getInstanceOf("while",new STAttrMap().put("bexpr", (expr7!=null?expr7.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()).put("stmts", (s!=null?s.stringTemplates:null)));
                    }



                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:178:9: block
                    {
                    pushFollow(FOLLOW_block_in_stat400);
                    block8=block();

                    state._fsp--;



                    	      retval.structDeclST.addAll((block8!=null?block8.structDeclST:null));
                    	      retval.functionDeclsST.addAll((block8!=null?block8.functionDeclsST:null));
                    	      retval.stringTemplates.addAll((block8!=null?block8.stringTemplates:null));
                          

                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:183:7: structDecl
                    {
                    pushFollow(FOLLOW_structDecl_in_stat410);
                    structDecl9=structDecl();

                    state._fsp--;


                    retval.structDeclST.add((structDecl9!=null?structDecl9.st:null));

                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:184:7: functionDecl
                    {
                    pushFollow(FOLLOW_functionDecl_in_stat420);
                    functionDecl10=functionDecl();

                    state._fsp--;


                    retval.functionDeclsST.add((functionDecl10!=null?functionDecl10.st:null));

                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:185:7: returnStat
                    {
                    pushFollow(FOLLOW_returnStat_in_stat430);
                    returnStat11=returnStat();

                    state._fsp--;


                    retval.stringTemplates.add((returnStat11!=null?returnStat11.st:null));

                    }
                    break;

            }

            				if(retval.st != null)
            					retval.stringTemplates.add(retval.st);
            			
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:188:1: echo : ^( ECHO e= expr ) -> {!(type instanceof StructType)}? echo(expression=$e.sttype=type.toString()) -> echoObject(expression=$e.sttype=type);
    public final JPoclGenerator.echo_return echo() throws RecognitionException {
        JPoclGenerator.echo_return retval = new JPoclGenerator.echo_return();
        retval.start = input.LT(1);


        JPoclGenerator.expr_return e =null;


        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:188:6: ( ^( ECHO e= expr ) -> {!(type instanceof StructType)}? echo(expression=$e.sttype=type.toString()) -> echoObject(expression=$e.sttype=type))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:188:8: ^( ECHO e= expr )
            {
            match(input,ECHO,FOLLOW_ECHO_in_echo450); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_echo454);
            e=expr();

            state._fsp--;



            					Type type = (e!=null?((TypeTree)e.start):null).getStaticType();
            					setStackLimit((e!=null?e.stackSize:0) +1);
            				

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 192:5: -> {!(type instanceof StructType)}? echo(expression=$e.sttype=type.toString())
            if (!(type instanceof StructType)) {
                retval.st = templateLib.getInstanceOf("echo",new STAttrMap().put("expression", (e!=null?e.st:null)).put("type", type.toString()));
            }

            else // 193:5: -> echoObject(expression=$e.sttype=type)
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
        public List<Object> structDeclST;
        public List<Object> functionDeclsST;
        public List<Object> stringTemplates;
        public StringTemplate st;
        public Object getTemplate() { return st; }
        public String toString() { return st==null?null:st.toString(); }
    };


    // $ANTLR start "block"
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:195:1: block returns [List<Object> structDeclST, List<Object> functionDeclsST, List<Object> stringTemplates] : ^( BLOCK (s+= stat )+ ) ;
    public final JPoclGenerator.block_return block() throws RecognitionException {
        BlockLocals_stack.push(new BlockLocals_scope());

        JPoclGenerator.block_return retval = new JPoclGenerator.block_return();
        retval.start = input.LT(1);


        List list_s=null;
        RuleReturnScope s = null;

        				((BlockLocals_scope)BlockLocals_stack.peek()).locals = new LinkedList<>();
        				((BlockLocals_scope)BlockLocals_stack.peek()).isFunctionBlock = false;
        				
        				retval.structDeclST = new LinkedList<>();
        				retval.functionDeclsST = new LinkedList<>();
        				retval.stringTemplates = new LinkedList<>();
        			
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:205:4: ( ^( BLOCK (s+= stat )+ ) )
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:206:5: ^( BLOCK (s+= stat )+ )
            {
            match(input,BLOCK,FOLLOW_BLOCK_in_block533); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:206:13: (s+= stat )+
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
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:206:14: s+= stat
            	    {
            	    pushFollow(FOLLOW_stat_in_block538);
            	    s=stat();

            	    state._fsp--;

            	    if (list_s==null) list_s=new ArrayList();
            	    list_s.add(s.getTemplate());



            	    					retval.structDeclST.addAll(((stat_return)s).structDeclST);
            	    					retval.functionDeclsST.addAll(((stat_return)s).functionDeclsST);
            	    					retval.stringTemplates.addAll(((stat_return)s).stringTemplates);
            	    				

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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:213:1: functionDecl : ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stat )* ) -> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}? functionDeclVoid(visibility=isTopLevel($start) ? \"public\" : \" \"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=stringTemplatesstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit) -> functionDecl(visibility=isTopLevel($start) ? \"public\" : \" \"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=stringTemplatesstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit);
    public final JPoclGenerator.functionDecl_return functionDecl() throws RecognitionException {
        BlockLocals_stack.push(new BlockLocals_scope());
        FunctionStack_stack.push(new FunctionStack_scope());

        JPoclGenerator.functionDecl_return retval = new JPoclGenerator.functionDecl_return();
        retval.start = input.LT(1);


        TypeTree ID14=null;
        JPoclGenerator.stat_return stat12 =null;

        JPoclGenerator.returnFunctionDecl_return returnFunctionDecl13 =null;

        JPoclGenerator.parametersDecl_return parametersDecl15 =null;



        							((BlockLocals_scope)BlockLocals_stack.peek()).locals = new LinkedList<>();
        							((BlockLocals_scope)BlockLocals_stack.peek()).isFunctionBlock = true;
        							
        							List<Object> stringTemplates = new LinkedList<>();
        						
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:222:7: ( ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stat )* ) -> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}? functionDeclVoid(visibility=isTopLevel($start) ? \"public\" : \" \"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=stringTemplatesstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit) -> functionDecl(visibility=isTopLevel($start) ? \"public\" : \" \"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=stringTemplatesstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:223:7: ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stat )* )
            {
            match(input,FUNCDECL,FOLLOW_FUNCDECL_in_functionDecl600); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_returnFunctionDecl_in_functionDecl602);
            returnFunctionDecl13=returnFunctionDecl();

            state._fsp--;


            ID14=(TypeTree)match(input,ID,FOLLOW_ID_in_functionDecl604); 

            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:223:40: ( parametersDecl )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==PDECS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:223:40: parametersDecl
                    {
                    pushFollow(FOLLOW_parametersDecl_in_functionDecl606);
                    parametersDecl15=parametersDecl();

                    state._fsp--;


                    }
                    break;

            }


            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:223:56: ( stat )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0 >= ASSIGN && LA5_0 <= BLOCK)||(LA5_0 >= ECHO && LA5_0 <= FUNCDECL)||LA5_0==40||(LA5_0 >= 42 && LA5_0 <= 43)||LA5_0==45) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:223:57: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_functionDecl610);
            	    stat12=stat();

            	    state._fsp--;


            	    stringTemplates.addAll((stat12!=null?stat12.stringTemplates:null));

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 224:7: -> {api.get($ID.text).getReturnType().equalsTo(PrimType.VOID)}? functionDeclVoid(visibility=isTopLevel($start) ? \"public\" : \" \"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=stringTemplatesstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit)
            if (api.get((ID14!=null?ID14.getText():null)).getReturnType().equalsTo(PrimType.VOID)) {
                retval.st = templateLib.getInstanceOf("functionDeclVoid",new STAttrMap().put("visibility", isTopLevel(((TypeTree)retval.start)) ? "public" : " ").put("returnType", (returnFunctionDecl13!=null?returnFunctionDecl13.st:null)).put("id", (ID14!=null?ID14.getText():null)).put("parameters", (parametersDecl15!=null?parametersDecl15.st:null)).put("stmts", stringTemplates).put("stackLimit", ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit).put("localsLimit", ((BlockLocals_scope)BlockLocals_stack.peek()).localsLimit));
            }

            else // 228:7: -> functionDecl(visibility=isTopLevel($start) ? \"public\" : \" \"returnType=$returnFunctionDecl.stid=$ID.textparameters=$parametersDecl.ststmts=stringTemplatesstackLimit=$FunctionStack::stackLimitlocalsLimit=$BlockLocals::localsLimit)
            {
                retval.st = templateLib.getInstanceOf("functionDecl",new STAttrMap().put("visibility", isTopLevel(((TypeTree)retval.start)) ? "public" : " ").put("returnType", (returnFunctionDecl13!=null?returnFunctionDecl13.st:null)).put("id", (ID14!=null?ID14.getText():null)).put("parameters", (parametersDecl15!=null?parametersDecl15.st:null)).put("stmts", stringTemplates).put("stackLimit", ((FunctionStack_scope)FunctionStack_stack.peek()).stackLimit).put("localsLimit", ((BlockLocals_scope)BlockLocals_stack.peek()).localsLimit));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:233:1: returnFunctionDecl : ( 'int' -> type(type=PrimType.INT)| 'bool' -> type(type=PrimType.BOOL)| 'void' -> type(type=PrimType.VOID)| ID -> type(type=\"L\"+packageDst+\"/\"+$ID.text+\";\"));
    public final JPoclGenerator.returnFunctionDecl_return returnFunctionDecl() throws RecognitionException {
        JPoclGenerator.returnFunctionDecl_return retval = new JPoclGenerator.returnFunctionDecl_return();
        retval.start = input.LT(1);


        TypeTree ID16=null;

        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:233:20: ( 'int' -> type(type=PrimType.INT)| 'bool' -> type(type=PrimType.BOOL)| 'void' -> type(type=PrimType.VOID)| ID -> type(type=\"L\"+packageDst+\"/\"+$ID.text+\";\"))
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:234:12: 'int'
                    {
                    match(input,41,FOLLOW_41_in_returnFunctionDecl800); 

                    // TEMPLATE REWRITE
                    // 234:18: -> type(type=PrimType.INT)
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", PrimType.INT));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:235:13: 'bool'
                    {
                    match(input,39,FOLLOW_39_in_returnFunctionDecl823); 

                    // TEMPLATE REWRITE
                    // 235:20: -> type(type=PrimType.BOOL)
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", PrimType.BOOL));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:236:13: 'void'
                    {
                    match(input,44,FOLLOW_44_in_returnFunctionDecl846); 

                    // TEMPLATE REWRITE
                    // 236:20: -> type(type=PrimType.VOID)
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", PrimType.VOID));
                    }



                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:237:13: ID
                    {
                    ID16=(TypeTree)match(input,ID,FOLLOW_ID_in_returnFunctionDecl869); 

                    // TEMPLATE REWRITE
                    // 237:16: -> type(type=\"L\"+packageDst+\"/\"+$ID.text+\";\")
                    {
                        retval.st = templateLib.getInstanceOf("type",new STAttrMap().put("type", "L"+packageDst+"/"+(ID16!=null?ID16.getText():null)+";"));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:240:1: returnStat : ( 'return' -> returnStat(stat=\"return\")| ^( 'return' expr ) -> {$expr.start.getStaticType().equalsTo(PrimType.INT) \r\n\t\t\t\t\t\t\t\t\t\t\t|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}? returnStat(stat=\"ireturn\"expr=$expr.st) -> returnStat(stat=\"areturn\"expr=$expr.st));
    public final JPoclGenerator.returnStat_return returnStat() throws RecognitionException {
        JPoclGenerator.returnStat_return retval = new JPoclGenerator.returnStat_return();
        retval.start = input.LT(1);


        JPoclGenerator.expr_return expr17 =null;


        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:240:12: ( 'return' -> returnStat(stat=\"return\")| ^( 'return' expr ) -> {$expr.start.getStaticType().equalsTo(PrimType.INT) \r\n\t\t\t\t\t\t\t\t\t\t\t|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}? returnStat(stat=\"ireturn\"expr=$expr.st) -> returnStat(stat=\"areturn\"expr=$expr.st))
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:241:6: 'return'
                    {
                    match(input,42,FOLLOW_42_in_returnStat902); 

                    // TEMPLATE REWRITE
                    // 241:15: -> returnStat(stat=\"return\")
                    {
                        retval.st = templateLib.getInstanceOf("returnStat",new STAttrMap().put("stat", "return"));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:242:8: ^( 'return' expr )
                    {
                    match(input,42,FOLLOW_42_in_returnStat921); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_returnStat923);
                    expr17=expr();

                    state._fsp--;


                    setStackLimit((expr17!=null?expr17.stackSize:0));

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 243:8: -> {$expr.start.getStaticType().equalsTo(PrimType.INT) \r\n\t\t\t\t\t\t\t\t\t\t\t|| $expr.start.getStaticType().equalsTo(PrimType.BOOL)}? returnStat(stat=\"ireturn\"expr=$expr.st)
                    if ((expr17!=null?((TypeTree)expr17.start):null).getStaticType().equalsTo(PrimType.INT) 
                    											|| (expr17!=null?((TypeTree)expr17.start):null).getStaticType().equalsTo(PrimType.BOOL)) {
                        retval.st = templateLib.getInstanceOf("returnStat",new STAttrMap().put("stat", "ireturn").put("expr", (expr17!=null?expr17.st:null)));
                    }

                    else // 246:8: -> returnStat(stat=\"areturn\"expr=$expr.st)
                    {
                        retval.st = templateLib.getInstanceOf("returnStat",new STAttrMap().put("stat", "areturn").put("expr", (expr17!=null?expr17.st:null)));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:249:1: parametersDecl : ^( PDECS (p+= parameterDecl )+ ) -> parametersDecl(parameters=$p);
    public final JPoclGenerator.parametersDecl_return parametersDecl() throws RecognitionException {
        JPoclGenerator.parametersDecl_return retval = new JPoclGenerator.parametersDecl_return();
        retval.start = input.LT(1);


        List list_p=null;
        RuleReturnScope p = null;
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:249:16: ( ^( PDECS (p+= parameterDecl )+ ) -> parametersDecl(parameters=$p))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:250:9: ^( PDECS (p+= parameterDecl )+ )
            {
            match(input,PDECS,FOLLOW_PDECS_in_parametersDecl1005); 

            match(input, Token.DOWN, null); 
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:250:18: (p+= parameterDecl )+
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
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:250:18: p+= parameterDecl
            	    {
            	    pushFollow(FOLLOW_parameterDecl_in_parametersDecl1009);
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
            // 250:36: -> parametersDecl(parameters=$p)
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:253:1: parameterDecl : ( ^( 'int' ID ) -> parameter(parameter=\"I\")| ^( 'bool' ID ) -> parameter(parameter=\"Z\")| ^( 'struct' structType= ID structID= ID ) -> parameter(parameter=\"L\"+packageDst+\"/\"+$structType.text+\";\"));
    public final JPoclGenerator.parameterDecl_return parameterDecl() throws RecognitionException {
        JPoclGenerator.parameterDecl_return retval = new JPoclGenerator.parameterDecl_return();
        retval.start = input.LT(1);


        TypeTree structType=null;
        TypeTree structID=null;
        TypeTree ID18=null;
        TypeTree ID19=null;


        								String pid = "";
        							
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:260:8: ( ^( 'int' ID ) -> parameter(parameter=\"I\")| ^( 'bool' ID ) -> parameter(parameter=\"Z\")| ^( 'struct' structType= ID structID= ID ) -> parameter(parameter=\"L\"+packageDst+\"/\"+$structType.text+\";\"))
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:261:8: ^( 'int' ID )
                    {
                    match(input,41,FOLLOW_41_in_parameterDecl1075); 

                    match(input, Token.DOWN, null); 
                    ID18=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1077); 

                    pid = (ID18!=null?ID18.getText():null);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 261:38: -> parameter(parameter=\"I\")
                    {
                        retval.st = templateLib.getInstanceOf("parameter",new STAttrMap().put("parameter", "I"));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:262:9: ^( 'bool' ID )
                    {
                    match(input,39,FOLLOW_39_in_parameterDecl1100); 

                    match(input, Token.DOWN, null); 
                    ID19=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1102); 

                    pid = (ID19!=null?ID19.getText():null);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 262:40: -> parameter(parameter=\"Z\")
                    {
                        retval.st = templateLib.getInstanceOf("parameter",new STAttrMap().put("parameter", "Z"));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:263:9: ^( 'struct' structType= ID structID= ID )
                    {
                    match(input,43,FOLLOW_43_in_parameterDecl1125); 

                    match(input, Token.DOWN, null); 
                    structType=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1129); 

                    structID=(TypeTree)match(input,ID,FOLLOW_ID_in_parameterDecl1133); 

                    pid = (structID!=null?structID.getText():null);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 264:9: -> parameter(parameter=\"L\"+packageDst+\"/\"+$structType.text+\";\")
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:267:1: structDecl : ^( 'struct' ID (sm+= structMember[visibility] )+ ) -> structDecl(id=$ID.textvisibility=visibilitypackageDst=packageDstconstructor=structType.buildConstructor(visibility, packageDst)member=$smtoStringMethod=structType.buildToString(packageDst)equalsMethod=structType.buildEquals(packageDst));
    public final JPoclGenerator.structDecl_return structDecl() throws RecognitionException {
        JPoclGenerator.structDecl_return retval = new JPoclGenerator.structDecl_return();
        retval.start = input.LT(1);


        TypeTree ID20=null;
        List list_sm=null;
        RuleReturnScope sm = null;

        					 		String visibility;
        					 
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:271:7: ( ^( 'struct' ID (sm+= structMember[visibility] )+ ) -> structDecl(id=$ID.textvisibility=visibilitypackageDst=packageDstconstructor=structType.buildConstructor(visibility, packageDst)member=$smtoStringMethod=structType.buildToString(packageDst)equalsMethod=structType.buildEquals(packageDst)))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:271:8: ^( 'struct' ID (sm+= structMember[visibility] )+ )
            {
            visibility = isTopLevel(((TypeTree)retval.start)) ? "public" : "";

            match(input,43,FOLLOW_43_in_structDecl1196); 

            match(input, Token.DOWN, null); 
            ID20=(TypeTree)match(input,ID,FOLLOW_ID_in_structDecl1198); 

            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:272:23: (sm+= structMember[visibility] )+
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
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:272:23: sm+= structMember[visibility]
            	    {
            	    pushFollow(FOLLOW_structMember_in_structDecl1202);
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



            					 	StructType structType = dataTypes.get((ID20!=null?ID20.getText():null));
            					 

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 275:7: -> structDecl(id=$ID.textvisibility=visibilitypackageDst=packageDstconstructor=structType.buildConstructor(visibility, packageDst)member=$smtoStringMethod=structType.buildToString(packageDst)equalsMethod=structType.buildEquals(packageDst))
            {
                retval.st = templateLib.getInstanceOf("structDecl",new STAttrMap().put("id", (ID20!=null?ID20.getText():null)).put("visibility", visibility).put("packageDst", packageDst).put("constructor", structType.buildConstructor(visibility, packageDst)).put("member", list_sm).put("toStringMethod", structType.buildToString(packageDst)).put("equalsMethod", structType.buildEquals(packageDst)));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:281:1: structMember[String visibility] : ( ^( 'int' ID ) -> structMember(type=\"I\"id=$ID.textvisibility=$visibility)| ^( 'bool' id2= ID ) -> structMember(type=\"Z\"id=$ID.textvisibility=$visibility)| ^( 'struct' structType= ID structID= ID ) -> structMember(type=dataTypes.get($structType.text).getObjectType(packageDst)id=$structID.textvisibility=$visibility));
    public final JPoclGenerator.structMember_return structMember(String visibility) throws RecognitionException {
        JPoclGenerator.structMember_return retval = new JPoclGenerator.structMember_return();
        retval.start = input.LT(1);


        TypeTree id2=null;
        TypeTree structType=null;
        TypeTree structID=null;
        TypeTree ID21=null;

        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:281:33: ( ^( 'int' ID ) -> structMember(type=\"I\"id=$ID.textvisibility=$visibility)| ^( 'bool' id2= ID ) -> structMember(type=\"Z\"id=$ID.textvisibility=$visibility)| ^( 'struct' structType= ID structID= ID ) -> structMember(type=dataTypes.get($structType.text).getObjectType(packageDst)id=$structID.textvisibility=$visibility))
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:282:7: ^( 'int' ID )
                    {
                    match(input,41,FOLLOW_41_in_structMember1314); 

                    match(input, Token.DOWN, null); 
                    ID21=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1316); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 282:19: -> structMember(type=\"I\"id=$ID.textvisibility=$visibility)
                    {
                        retval.st = templateLib.getInstanceOf("structMember",new STAttrMap().put("type", "I").put("id", (ID21!=null?ID21.getText():null)).put("visibility", visibility));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:283:9: ^( 'bool' id2= ID )
                    {
                    match(input,39,FOLLOW_39_in_structMember1347); 

                    match(input, Token.DOWN, null); 
                    id2=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1351); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 283:26: -> structMember(type=\"Z\"id=$ID.textvisibility=$visibility)
                    {
                        retval.st = templateLib.getInstanceOf("structMember",new STAttrMap().put("type", "Z").put("id", (id2!=null?id2.getText():null)).put("visibility", visibility));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:284:9: ^( 'struct' structType= ID structID= ID )
                    {
                    match(input,43,FOLLOW_43_in_structMember1382); 

                    match(input, Token.DOWN, null); 
                    structType=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1386); 

                    structID=(TypeTree)match(input,ID,FOLLOW_ID_in_structMember1390); 

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 285:8: -> structMember(type=dataTypes.get($structType.text).getObjectType(packageDst)id=$structID.textvisibility=$visibility)
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:289:1: expr returns [int stackSize] : ( ^( '^' t1= expr t2= expr ) -> pow(leftExpr=$t1.strightExpr=$t2.st)| ^( '+' t1= expr t2= expr ) -> add(leftExpr=$t1.strightExpr=$t2.st)| ^( '-' t1= expr t2= expr ) -> sub(leftExpr=$t1.strightExpr=$t2.st)| ^( '%' t1= expr t2= expr ) -> remainder(leftExpr=$t1.strightExpr=$t2.st)| ^( '*' t1= expr t2= expr ) -> mul(leftExpr=$t1.strightExpr=$t2.st)| ^( '/' t1= expr t2= expr ) -> div(leftExpr=$t1.strightExpr=$t2.st)| ^( '!' t= expr ) -> fact(expr=$t.stpackageDst=packageDst)| ^( '~' t= expr ) -> not(expr=$t.sttokenNum=$start.getIndex()packageDst=packageDst)| ^( PLUS t= expr ) -> unaryPlus(expr=$t.st)| ^( SIGN t= expr ) -> unaryMinus(expr=$t.st)| ^( '<' t1= expr t2= expr ) -> less(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '<=' t1= expr t2= expr ) -> lessEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>' t1= expr t2= expr ) -> greater(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>=' t1= expr t2= expr ) -> greaterEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| equals | ^( '&&' t1= expr t2= expr ) -> and(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '||' t1= expr t2= expr ) -> or(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( DOT ( functionCall | structValue ) qualifiedID ) -> oneTimeAccess(function=$functionCall.ststructValue=$structValue.sttype=typeqid=$qualifiedID.st)| functionCall | structValue | qualifiedID | INT -> constant(value=$INT.text)| BOOLEAN -> constant(value=$BOOLEAN.text.equals(\"true\") ? \"1\" : \"0\"));
    public final JPoclGenerator.expr_return expr() throws RecognitionException {
        JPoclGenerator.expr_return retval = new JPoclGenerator.expr_return();
        retval.start = input.LT(1);


        TypeTree INT29=null;
        TypeTree BOOLEAN30=null;
        JPoclGenerator.expr_return t1 =null;

        JPoclGenerator.expr_return t2 =null;

        JPoclGenerator.expr_return t =null;

        JPoclGenerator.equals_return equals22 =null;

        JPoclGenerator.functionCall_return functionCall23 =null;

        JPoclGenerator.structValue_return structValue24 =null;

        JPoclGenerator.qualifiedID_return qualifiedID25 =null;

        JPoclGenerator.functionCall_return functionCall26 =null;

        JPoclGenerator.structValue_return structValue27 =null;

        JPoclGenerator.qualifiedID_return qualifiedID28 =null;


        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:290:5: ( ^( '^' t1= expr t2= expr ) -> pow(leftExpr=$t1.strightExpr=$t2.st)| ^( '+' t1= expr t2= expr ) -> add(leftExpr=$t1.strightExpr=$t2.st)| ^( '-' t1= expr t2= expr ) -> sub(leftExpr=$t1.strightExpr=$t2.st)| ^( '%' t1= expr t2= expr ) -> remainder(leftExpr=$t1.strightExpr=$t2.st)| ^( '*' t1= expr t2= expr ) -> mul(leftExpr=$t1.strightExpr=$t2.st)| ^( '/' t1= expr t2= expr ) -> div(leftExpr=$t1.strightExpr=$t2.st)| ^( '!' t= expr ) -> fact(expr=$t.stpackageDst=packageDst)| ^( '~' t= expr ) -> not(expr=$t.sttokenNum=$start.getIndex()packageDst=packageDst)| ^( PLUS t= expr ) -> unaryPlus(expr=$t.st)| ^( SIGN t= expr ) -> unaryMinus(expr=$t.st)| ^( '<' t1= expr t2= expr ) -> less(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '<=' t1= expr t2= expr ) -> lessEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>' t1= expr t2= expr ) -> greater(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '>=' t1= expr t2= expr ) -> greaterEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| equals | ^( '&&' t1= expr t2= expr ) -> and(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( '||' t1= expr t2= expr ) -> or(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())| ^( DOT ( functionCall | structValue ) qualifiedID ) -> oneTimeAccess(function=$functionCall.ststructValue=$structValue.sttype=typeqid=$qualifiedID.st)| functionCall | structValue | qualifiedID | INT -> constant(value=$INT.text)| BOOLEAN -> constant(value=$BOOLEAN.text.equals(\"true\") ? \"1\" : \"0\"))
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:291:6: ^( '^' t1= expr t2= expr )
                    {
                    match(input,38,FOLLOW_38_in_expr1458); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1462);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1466);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize, 3);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 292:6: -> pow(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("pow",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:294:7: ^( '+' t1= expr t2= expr )
                    {
                    match(input,27,FOLLOW_27_in_expr1502); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1506);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1510);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 295:7: -> add(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("add",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:297:7: ^( '-' t1= expr t2= expr )
                    {
                    match(input,29,FOLLOW_29_in_expr1548); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1552);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1556);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 298:6: -> sub(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("sub",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:300:7: ^( '%' t1= expr t2= expr )
                    {
                    match(input,22,FOLLOW_22_in_expr1592); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1596);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1600);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 301:6: -> remainder(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("remainder",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:303:7: ^( '*' t1= expr t2= expr )
                    {
                    match(input,26,FOLLOW_26_in_expr1636); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1640);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1644);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 304:7: -> mul(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("mul",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:306:7: ^( '/' t1= expr t2= expr )
                    {
                    match(input,30,FOLLOW_30_in_expr1682); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1686);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1690);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 307:7: -> div(leftExpr=$t1.strightExpr=$t2.st)
                    {
                        retval.st = templateLib.getInstanceOf("div",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
                    }



                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:309:8: ^( '!' t= expr )
                    {
                    match(input,21,FOLLOW_21_in_expr1729); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1733);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 310:7: -> fact(expr=$t.stpackageDst=packageDst)
                    {
                        retval.st = templateLib.getInstanceOf("fact",new STAttrMap().put("expr", (t!=null?t.st:null)).put("packageDst", packageDst));
                    }



                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:312:7: ^( '~' t= expr )
                    {
                    match(input,49,FOLLOW_49_in_expr1772); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1776);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 313:6: -> not(expr=$t.sttokenNum=$start.getIndex()packageDst=packageDst)
                    {
                        retval.st = templateLib.getInstanceOf("not",new STAttrMap().put("expr", (t!=null?t.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()).put("packageDst", packageDst));
                    }



                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:315:8: ^( PLUS t= expr )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expr1818); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1822);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 316:8: -> unaryPlus(expr=$t.st)
                    {
                        retval.st = templateLib.getInstanceOf("unaryPlus",new STAttrMap().put("expr", (t!=null?t.st:null)));
                    }



                    }
                    break;
                case 10 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:318:8: ^( SIGN t= expr )
                    {
                    match(input,SIGN,FOLLOW_SIGN_in_expr1859); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1863);
                    t=expr();

                    state._fsp--;


                     retval.stackSize = t.stackSize;

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 319:8: -> unaryMinus(expr=$t.st)
                    {
                        retval.st = templateLib.getInstanceOf("unaryMinus",new STAttrMap().put("expr", (t!=null?t.st:null)));
                    }



                    }
                    break;
                case 11 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:321:7: ^( '<' t1= expr t2= expr )
                    {
                    match(input,32,FOLLOW_32_in_expr1899); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1903);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1907);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 322:6: -> less(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("less",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 12 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:324:7: ^( '<=' t1= expr t2= expr )
                    {
                    match(input,33,FOLLOW_33_in_expr1947); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr1951);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr1955);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 325:7: -> lessEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("lessEqual",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 13 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:327:7: ^( '>' t1= expr t2= expr )
                    {
                    match(input,36,FOLLOW_36_in_expr1996); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2000);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2004);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 328:7: -> greater(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("greater",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 14 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:330:7: ^( '>=' t1= expr t2= expr )
                    {
                    match(input,37,FOLLOW_37_in_expr2045); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2049);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2053);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 331:7: -> greaterEqual(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("greaterEqual",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 15 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:333:7: equals
                    {
                    pushFollow(FOLLOW_equals_in_expr2093);
                    equals22=equals();

                    state._fsp--;


                    retval.st = (equals22!=null?equals22.st:null); retval.stackSize = (equals22!=null?equals22.stackSize:0);

                    }
                    break;
                case 16 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:335:7: ^( '&&' t1= expr t2= expr )
                    {
                    match(input,23,FOLLOW_23_in_expr2109); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2113);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2117);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 336:6: -> and(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("and",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 17 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:338:7: ^( '||' t1= expr t2= expr )
                    {
                    match(input,47,FOLLOW_47_in_expr2157); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr2161);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr2165);
                    t2=expr();

                    state._fsp--;


                     retval.stackSize = calculateStackLimit(t1.stackSize, t2.stackSize);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 339:6: -> or(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
                    {
                        retval.st = templateLib.getInstanceOf("or",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)).put("tokenNum", ((TypeTree)retval.start).getIndex()));
                    }



                    }
                    break;
                case 18 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:341:7: ^( DOT ( functionCall | structValue ) qualifiedID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_expr2205); 

                    Type type = null;

                    match(input, Token.DOWN, null); 
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:342:7: ( functionCall | structValue )
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
                            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:342:8: functionCall
                            {
                            pushFollow(FOLLOW_functionCall_in_expr2216);
                            functionCall23=functionCall();

                            state._fsp--;


                            type = (functionCall23!=null?((TypeTree)functionCall23.start):null).getStaticType(); retval.stackSize = (functionCall23!=null?functionCall23.stackSize:0);

                            }
                            break;
                        case 2 :
                            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:343:9: structValue
                            {
                            pushFollow(FOLLOW_structValue_in_expr2228);
                            structValue24=structValue();

                            state._fsp--;


                            type = (structValue24!=null?((TypeTree)structValue24.start):null).getStaticType(); retval.stackSize = (functionCall23!=null?functionCall23.stackSize:0);

                            }
                            break;

                    }


                    pushFollow(FOLLOW_qualifiedID_in_expr2240);
                    qualifiedID25=qualifiedID();

                    state._fsp--;


                    retval.stackSize =  Math.max(retval.stackSize,1);

                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 345:7: -> oneTimeAccess(function=$functionCall.ststructValue=$structValue.sttype=typeqid=$qualifiedID.st)
                    {
                        retval.st = templateLib.getInstanceOf("oneTimeAccess",new STAttrMap().put("function", (functionCall23!=null?functionCall23.st:null)).put("structValue", (structValue24!=null?structValue24.st:null)).put("type", type).put("qid", (qualifiedID25!=null?qualifiedID25.st:null)));
                    }



                    }
                    break;
                case 19 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:348:7: functionCall
                    {
                    pushFollow(FOLLOW_functionCall_in_expr2314);
                    functionCall26=functionCall();

                    state._fsp--;


                    retval.st = (functionCall26!=null?functionCall26.st:null); retval.stackSize = (functionCall26!=null?functionCall26.stackSize:0);

                    }
                    break;
                case 20 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:350:7: structValue
                    {
                    pushFollow(FOLLOW_structValue_in_expr2329);
                    structValue27=structValue();

                    state._fsp--;


                    retval.st = (structValue27!=null?structValue27.st:null); retval.stackSize = (structValue27!=null?structValue27.stackSize:0);

                    }
                    break;
                case 21 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:352:7: qualifiedID
                    {
                    pushFollow(FOLLOW_qualifiedID_in_expr2344);
                    qualifiedID28=qualifiedID();

                    state._fsp--;


                    retval.st = (qualifiedID28!=null?qualifiedID28.st:null); retval.stackSize = 1;

                    }
                    break;
                case 22 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:354:8: INT
                    {
                    INT29=(TypeTree)match(input,INT,FOLLOW_INT_in_expr2360); 

                    retval.stackSize = 1;

                    // TEMPLATE REWRITE
                    // 355:7: -> constant(value=$INT.text)
                    {
                        retval.st = templateLib.getInstanceOf("constant",new STAttrMap().put("value", (INT29!=null?INT29.getText():null)));
                    }



                    }
                    break;
                case 23 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:356:7: BOOLEAN
                    {
                    BOOLEAN30=(TypeTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expr2385); 

                    retval.stackSize = 1;

                    // TEMPLATE REWRITE
                    // 357:6: -> constant(value=$BOOLEAN.text.equals(\"true\") ? \"1\" : \"0\")
                    {
                        retval.st = templateLib.getInstanceOf("constant",new STAttrMap().put("value", (BOOLEAN30!=null?BOOLEAN30.getText():null).equals("true") ? "1" : "0"));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:360:1: equals returns [int stackSize] : ^( '==' t1= expr t2= expr ) -> {areStructs}? equalsStruct(leftExpr=$t1.strightExpr=$t2.st) -> equals(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex());
    public final JPoclGenerator.equals_return equals() throws RecognitionException {
        JPoclGenerator.equals_return retval = new JPoclGenerator.equals_return();
        retval.start = input.LT(1);


        JPoclGenerator.expr_return t1 =null;

        JPoclGenerator.expr_return t2 =null;


        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:360:31: ( ^( '==' t1= expr t2= expr ) -> {areStructs}? equalsStruct(leftExpr=$t1.strightExpr=$t2.st) -> equals(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex()))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:361:5: ^( '==' t1= expr t2= expr )
            {
            match(input,35,FOLLOW_35_in_equals2434); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_expr_in_equals2438);
            t1=expr();

            state._fsp--;


            pushFollow(FOLLOW_expr_in_equals2442);
            t2=expr();

            state._fsp--;


             boolean areStructs = (t1!=null?((TypeTree)t1.start):null).getStaticType() instanceof StructType;
            			 		retval.stackSize = calculateStackLimit((t1!=null?t1.stackSize:0), (t2!=null?t2.stackSize:0));			 								 
            			 	

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 366:5: -> {areStructs}? equalsStruct(leftExpr=$t1.strightExpr=$t2.st)
            if (areStructs) {
                retval.st = templateLib.getInstanceOf("equalsStruct",new STAttrMap().put("leftExpr", (t1!=null?t1.st:null)).put("rightExpr", (t2!=null?t2.st:null)));
            }

            else // 368:5: -> equals(leftExpr=$t1.strightExpr=$t2.sttokenNum=$start.getIndex())
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:371:1: functionCall returns [int stackSize] : ^( FUNCCALL ID (e+= expr )* ) -> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}? functionCall(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e) -> functionCallAndPop(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e);
    public final JPoclGenerator.functionCall_return functionCall() throws RecognitionException {
        JPoclGenerator.functionCall_return retval = new JPoclGenerator.functionCall_return();
        retval.start = input.LT(1);


        TypeTree ID31=null;
        List list_e=null;
        RuleReturnScope e = null;
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:372:7: ( ^( FUNCCALL ID (e+= expr )* ) -> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}? functionCall(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e) -> functionCallAndPop(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:373:7: ^( FUNCCALL ID (e+= expr )* )
            {
            match(input,FUNCCALL,FOLLOW_FUNCCALL_in_functionCall2544); 

            match(input, Token.DOWN, null); 
            ID31=(TypeTree)match(input,ID,FOLLOW_ID_in_functionCall2546); 

            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:373:22: (e+= expr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==BOOLEAN||LA14_0==DOT||LA14_0==FUNCCALL||(LA14_0 >= ID && LA14_0 <= INT)||(LA14_0 >= PLUS && LA14_0 <= SIGN)||(LA14_0 >= 21 && LA14_0 <= 23)||(LA14_0 >= 26 && LA14_0 <= 27)||(LA14_0 >= 29 && LA14_0 <= 30)||(LA14_0 >= 32 && LA14_0 <= 33)||(LA14_0 >= 35 && LA14_0 <= 38)||LA14_0==43||LA14_0==47||LA14_0==49) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:373:23: e+= expr
            	    {
            	    pushFollow(FOLLOW_expr_in_functionCall2552);
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



            							FunctionType functionType = api.get((ID31!=null?ID31.getText():null));
            							Type returnType = functionType.getReturnType();
            							if(!returnType.equalsTo(PrimType.VOID)){
            								retval.stackSize += 1;
            							}
            							String returnTypeRappresentation = returnType.getObjectType(packageDst);
            						

            match(input, Token.UP, null); 


            // TEMPLATE REWRITE
            // 381:7: -> {hasReturnValueConsumed($start) || returnType.equalsTo(PrimType.VOID)}? functionCall(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e)
            if (hasReturnValueConsumed(((TypeTree)retval.start)) || returnType.equalsTo(PrimType.VOID)) {
                retval.st = templateLib.getInstanceOf("functionCall",new STAttrMap().put("package", packageDst).put("fid", (ID31!=null?ID31.getText():null)).put("returnType", returnTypeRappresentation).put("signature", functionType.getParametersSignature(packageDst)).put("expr", list_e));
            }

            else // 385:7: -> functionCallAndPop(package=packageDstfid=$ID.textreturnType=returnTypeRappresentationsignature=functionType.getParametersSignature(packageDst)expr=$e)
            {
                retval.st = templateLib.getInstanceOf("functionCallAndPop",new STAttrMap().put("package", packageDst).put("fid", (ID31!=null?ID31.getText():null)).put("returnType", returnTypeRappresentation).put("signature", functionType.getParametersSignature(packageDst)).put("expr", list_e));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:390:1: structValue returns [int stackSize] : ^( 'struct' ID (e+= expr )+ ) -> structValue(id=$ID.textpackageDst=packageDstconstructorSignature=dataTypes.get($ID.text).getFieldsSignature(packageDst)expr=$e);
    public final JPoclGenerator.structValue_return structValue() throws RecognitionException {
        JPoclGenerator.structValue_return retval = new JPoclGenerator.structValue_return();
        retval.start = input.LT(1);


        TypeTree ID32=null;
        List list_e=null;
        RuleReturnScope e = null;
        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:394:7: ( ^( 'struct' ID (e+= expr )+ ) -> structValue(id=$ID.textpackageDst=packageDstconstructorSignature=dataTypes.get($ID.text).getFieldsSignature(packageDst)expr=$e))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:395:8: ^( 'struct' ID (e+= expr )+ )
            {
            match(input,43,FOLLOW_43_in_structValue2735); 

            match(input, Token.DOWN, null); 
            ID32=(TypeTree)match(input,ID,FOLLOW_ID_in_structValue2737); 

            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:395:22: (e+= expr )+
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
            	    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:395:23: e+= expr
            	    {
            	    pushFollow(FOLLOW_expr_in_structValue2742);
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
            // 396:8: -> structValue(id=$ID.textpackageDst=packageDstconstructorSignature=dataTypes.get($ID.text).getFieldsSignature(packageDst)expr=$e)
            {
                retval.st = templateLib.getInstanceOf("structValue",new STAttrMap().put("id", (ID32!=null?ID32.getText():null)).put("packageDst", packageDst).put("constructorSignature", dataTypes.get((ID32!=null?ID32.getText():null)).getFieldsSignature(packageDst)).put("expr", list_e));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:400:1: qualifiedID : ( id | ^( DOT id qid= qualifiedID ) -> {$start.parent.token.getType() != DOT}? qualifiedIDAndLoad(qualifiedID=$qid.stlocalNum=getVariableLocalNumber($id.text)) -> qualifiedID(id=$id.stqualifiedID=$qid.st));
    public final JPoclGenerator.qualifiedID_return qualifiedID() throws RecognitionException {
        JPoclGenerator.qualifiedID_return retval = new JPoclGenerator.qualifiedID_return();
        retval.start = input.LT(1);


        JPoclGenerator.qualifiedID_return qid =null;

        JPoclGenerator.id_return id33 =null;

        JPoclGenerator.id_return id34 =null;


        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:400:13: ( id | ^( DOT id qid= qualifiedID ) -> {$start.parent.token.getType() != DOT}? qualifiedIDAndLoad(qualifiedID=$qid.stlocalNum=getVariableLocalNumber($id.text)) -> qualifiedID(id=$id.stqualifiedID=$qid.st))
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
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:401:7: id
                    {
                    pushFollow(FOLLOW_id_in_qualifiedID2812);
                    id33=id();

                    state._fsp--;


                    retval.st = (id33!=null?id33.st:null);

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:402:9: ^( DOT id qid= qualifiedID )
                    {
                    match(input,DOT,FOLLOW_DOT_in_qualifiedID2825); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_id_in_qualifiedID2827);
                    id34=id();

                    state._fsp--;


                    pushFollow(FOLLOW_qualifiedID_in_qualifiedID2831);
                    qid=qualifiedID();

                    state._fsp--;


                    match(input, Token.UP, null); 


                    // TEMPLATE REWRITE
                    // 403:8: -> {$start.parent.token.getType() != DOT}? qualifiedIDAndLoad(qualifiedID=$qid.stlocalNum=getVariableLocalNumber($id.text))
                    if (((TypeTree)retval.start).parent.token.getType() != DOT) {
                        retval.st = templateLib.getInstanceOf("qualifiedIDAndLoad",new STAttrMap().put("qualifiedID", (qid!=null?qid.st:null)).put("localNum", getVariableLocalNumber((id34!=null?id34.text:null))));
                    }

                    else // 405:8: -> qualifiedID(id=$id.stqualifiedID=$qid.st)
                    {
                        retval.st = templateLib.getInstanceOf("qualifiedID",new STAttrMap().put("id", (id34!=null?id34.st:null)).put("qualifiedID", (qid!=null?qid.st:null)));
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
    // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:408:1: id returns [String text] : ID -> {!isRootDOT && isLeftValue}? store(store=type.getStorer()localNum=getVariableLocalNumber($ID.text)) -> {!isRootDOT}? load(load=type.getLoader()localNum=getVariableLocalNumber($ID.text)) -> {isLeftValue}? putField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst)) -> getField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst));
    public final JPoclGenerator.id_return id() throws RecognitionException {
        JPoclGenerator.id_return retval = new JPoclGenerator.id_return();
        retval.start = input.LT(1);


        TypeTree ID35=null;

        try {
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:408:26: ( ID -> {!isRootDOT && isLeftValue}? store(store=type.getStorer()localNum=getVariableLocalNumber($ID.text)) -> {!isRootDOT}? load(load=type.getLoader()localNum=getVariableLocalNumber($ID.text)) -> {isLeftValue}? putField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst)) -> getField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst)))
            // C:\\Users\\pc\\Documents\\GitHub\\JPocl\\JPoclGenerator.g:408:28: ID
            {
            ID35=(TypeTree)match(input,ID,FOLLOW_ID_in_id2910); 

            retval.text = (ID35!=null?ID35.getText():null);


            							if(((TypeTree)retval.start).parent.token.getType() != DOT && !isDeclared(retval.text)){
            								addVariableToLocals((ID35!=null?ID35.getText():null));
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
            // 421:9: -> {!isRootDOT && isLeftValue}? store(store=type.getStorer()localNum=getVariableLocalNumber($ID.text))
            if (!isRootDOT && isLeftValue) {
                retval.st = templateLib.getInstanceOf("store",new STAttrMap().put("store", type.getStorer()).put("localNum", getVariableLocalNumber((ID35!=null?ID35.getText():null))));
            }

            else // 423:8: -> {!isRootDOT}? load(load=type.getLoader()localNum=getVariableLocalNumber($ID.text))
            if (!isRootDOT) {
                retval.st = templateLib.getInstanceOf("load",new STAttrMap().put("load", type.getLoader()).put("localNum", getVariableLocalNumber((ID35!=null?ID35.getText():null))));
            }

            else // 425:8: -> {isLeftValue}? putField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst))
            if (isLeftValue) {
                retval.st = templateLib.getInstanceOf("putField",new STAttrMap().put("field", (ID35!=null?ID35.getText():null)).put("struct", siblingType).put("type", type.getObjectType(packageDst)));
            }

            else // 427:8: -> getField(field=$ID.textstruct=siblingTypetype=type.getObjectType(packageDst))
            {
                retval.st = templateLib.getInstanceOf("getField",new STAttrMap().put("field", (ID35!=null?ID35.getText():null)).put("struct", siblingType).put("type", type.getObjectType(packageDst)));
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
    public static final BitSet FOLLOW_echo_in_stat198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_stat211 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat213 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_stat215 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functionCall_in_stat280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_stat296 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat298 = new BitSet(new long[]{0x00002D0000000E30L});
    public static final BitSet FOLLOW_stat_in_stat302 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_45_in_stat349 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_stat351 = new BitSet(new long[]{0x00002D0000000E30L});
    public static final BitSet FOLLOW_stat_in_stat355 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_stat400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structDecl_in_stat410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDecl_in_stat420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStat_in_stat430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ECHO_in_echo450 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_echo454 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block533 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_stat_in_block538 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_FUNCDECL_in_functionDecl600 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_returnFunctionDecl_in_functionDecl602 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_functionDecl604 = new BitSet(new long[]{0x00002D0000008E38L});
    public static final BitSet FOLLOW_parametersDecl_in_functionDecl606 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_stat_in_functionDecl610 = new BitSet(new long[]{0x00002D0000000E38L});
    public static final BitSet FOLLOW_41_in_returnFunctionDecl800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_returnFunctionDecl823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_returnFunctionDecl846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_returnFunctionDecl869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStat902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStat921 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_returnStat923 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PDECS_in_parametersDecl1005 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_parameterDecl_in_parametersDecl1009 = new BitSet(new long[]{0x00000A8000000008L});
    public static final BitSet FOLLOW_41_in_parameterDecl1075 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1077 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_parameterDecl1100 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1102 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_parameterDecl1125 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1129 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl1133 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_structDecl1196 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structDecl1198 = new BitSet(new long[]{0x00000A8000000000L});
    public static final BitSet FOLLOW_structMember_in_structDecl1202 = new BitSet(new long[]{0x00000A8000000008L});
    public static final BitSet FOLLOW_41_in_structMember1314 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember1316 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_39_in_structMember1347 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember1351 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_43_in_structMember1382 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structMember1386 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember1390 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_38_in_expr1458 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1462 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1466 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_27_in_expr1502 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1506 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1510 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_29_in_expr1548 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1552 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1556 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_22_in_expr1592 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1596 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1600 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_26_in_expr1636 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1640 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1644 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_30_in_expr1682 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1686 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1690 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_21_in_expr1729 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1733 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_49_in_expr1772 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1776 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expr1818 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1822 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SIGN_in_expr1859 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1863 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_32_in_expr1899 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1903 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1907 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_33_in_expr1947 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr1951 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr1955 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_36_in_expr1996 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2000 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2004 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_37_in_expr2045 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2049 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2053 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_equals_in_expr2093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_expr2109 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2113 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2117 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_47_in_expr2157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr2161 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_expr2165 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOT_in_expr2205 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_functionCall_in_expr2216 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_structValue_in_expr2228 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_expr2240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_functionCall_in_expr2314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structValue_in_expr2329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedID_in_expr2344 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_expr2360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_expr2385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_equals2434 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_equals2438 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_equals2442 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNCCALL_in_functionCall2544 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_functionCall2546 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_expr_in_functionCall2552 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_43_in_structValue2735 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_structValue2737 = new BitSet(new long[]{0x0002887B6CE33540L});
    public static final BitSet FOLLOW_expr_in_structValue2742 = new BitSet(new long[]{0x0002887B6CE33548L});
    public static final BitSet FOLLOW_id_in_qualifiedID2812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_qualifiedID2825 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_id_in_qualifiedID2827 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_qualifiedID_in_qualifiedID2831 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ID_in_id2910 = new BitSet(new long[]{0x0000000000000002L});

}