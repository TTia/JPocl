// $ANTLR 3.4 C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g 2013-07-15 11:45:44

package gen;

import ast.TypeTree;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class JPoclASTParser extends Parser {
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public JPoclASTParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public JPoclASTParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return JPoclASTParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g"; }


    	boolean insideFunctionDecl = false;
    	@Override
      public void reportError(RecognitionException e) {
     	    throw new RuntimeException("Syntax error.\n");
      }


    public static class calc_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "calc"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:36:1: calc : ( stmt )+ -> ^( CALC ( stmt )+ ) ;
    public final JPoclASTParser.calc_return calc() throws RecognitionException {
        JPoclASTParser.calc_return retval = new JPoclASTParser.calc_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        JPoclASTParser.stmt_return stmt1 =null;


        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:36:7: ( ( stmt )+ -> ^( CALC ( stmt )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:36:9: ( stmt )+
            {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:36:9: ( stmt )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ECHO||LA1_0==ID||LA1_0==NL||LA1_0==40||LA1_0==43||(LA1_0 >= 45 && LA1_0 <= 46)) ) {
                    alt1=1;
                }
                else if ( (LA1_0==39||LA1_0==41||LA1_0==44) && ((!insideFunctionDecl))) {
                    alt1=1;
                }
                else if ( (LA1_0==42) && ((insideFunctionDecl))) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:36:9: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_calc84);
            	    stmt1=stmt();

            	    state._fsp--;

            	    stream_stmt.add(stmt1.getTree());

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


            // AST REWRITE
            // elements: stmt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 36:15: -> ^( CALC ( stmt )+ )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:36:18: ^( CALC ( stmt )+ )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                (TypeTree)adaptor.create(CALC, "CALC")
                , root_1);

                if ( !(stream_stmt.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_stmt.hasNext() ) {
                    adaptor.addChild(root_1, stream_stmt.nextTree());

                }
                stream_stmt.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "calc"


    public static class stmt_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "stmt"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:39:1: stmt : ( NL ->| ECHO instr NL -> ^( ECHO instr ) | qualifiedID tk= '=' bexpr0 NL -> ^( ASSIGN[$tk,$tk.text] bexpr0 qualifiedID ) | functionCall | 'if' ^ '(' ! bexpr0 ')' ! stmt | 'while' ^ '(' ! bexpr0 ')' ! stmt | block | structDecl | functionDecl | returnStmt );
    public final JPoclASTParser.stmt_return stmt() throws RecognitionException {
        JPoclASTParser.stmt_return retval = new JPoclASTParser.stmt_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token tk=null;
        Token NL2=null;
        Token ECHO3=null;
        Token NL5=null;
        Token NL8=null;
        Token string_literal10=null;
        Token char_literal11=null;
        Token char_literal13=null;
        Token string_literal15=null;
        Token char_literal16=null;
        Token char_literal18=null;
        JPoclASTParser.instr_return instr4 =null;

        JPoclASTParser.qualifiedID_return qualifiedID6 =null;

        JPoclASTParser.bexpr0_return bexpr07 =null;

        JPoclASTParser.functionCall_return functionCall9 =null;

        JPoclASTParser.bexpr0_return bexpr012 =null;

        JPoclASTParser.stmt_return stmt14 =null;

        JPoclASTParser.bexpr0_return bexpr017 =null;

        JPoclASTParser.stmt_return stmt19 =null;

        JPoclASTParser.block_return block20 =null;

        JPoclASTParser.structDecl_return structDecl21 =null;

        JPoclASTParser.functionDecl_return functionDecl22 =null;

        JPoclASTParser.returnStmt_return returnStmt23 =null;


        TypeTree tk_tree=null;
        TypeTree NL2_tree=null;
        TypeTree ECHO3_tree=null;
        TypeTree NL5_tree=null;
        TypeTree NL8_tree=null;
        TypeTree string_literal10_tree=null;
        TypeTree char_literal11_tree=null;
        TypeTree char_literal13_tree=null;
        TypeTree string_literal15_tree=null;
        TypeTree char_literal16_tree=null;
        TypeTree char_literal18_tree=null;
        RewriteRuleTokenStream stream_ECHO=new RewriteRuleTokenStream(adaptor,"token ECHO");
        RewriteRuleTokenStream stream_NL=new RewriteRuleTokenStream(adaptor,"token NL");
        RewriteRuleTokenStream stream_34=new RewriteRuleTokenStream(adaptor,"token 34");
        RewriteRuleSubtreeStream stream_qualifiedID=new RewriteRuleSubtreeStream(adaptor,"rule qualifiedID");
        RewriteRuleSubtreeStream stream_bexpr0=new RewriteRuleSubtreeStream(adaptor,"rule bexpr0");
        RewriteRuleSubtreeStream stream_instr=new RewriteRuleSubtreeStream(adaptor,"rule instr");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:39:6: ( NL ->| ECHO instr NL -> ^( ECHO instr ) | qualifiedID tk= '=' bexpr0 NL -> ^( ASSIGN[$tk,$tk.text] bexpr0 qualifiedID ) | functionCall | 'if' ^ '(' ! bexpr0 ')' ! stmt | 'while' ^ '(' ! bexpr0 ')' ! stmt | block | structDecl | functionDecl | returnStmt )
            int alt2=10;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==NL) ) {
                alt2=1;
            }
            else if ( (LA2_0==ECHO) ) {
                alt2=2;
            }
            else if ( (LA2_0==ID) ) {
                int LA2_3 = input.LA(2);

                if ( (LA2_3==DOT||LA2_3==34) ) {
                    alt2=3;
                }
                else if ( (LA2_3==24) ) {
                    alt2=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA2_0==40) ) {
                alt2=5;
            }
            else if ( (LA2_0==45) ) {
                alt2=6;
            }
            else if ( (LA2_0==46) ) {
                alt2=7;
            }
            else if ( (LA2_0==43) ) {
                int LA2_7 = input.LA(2);

                if ( (LA2_7==ID) ) {
                    int LA2_12 = input.LA(3);

                    if ( (LA2_12==46) ) {
                        alt2=8;
                    }
                    else if ( (LA2_12==ID) && ((!insideFunctionDecl))) {
                        alt2=9;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 12, input);

                        throw nvae;

                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 7, input);

                    throw nvae;

                }
            }
            else if ( (LA2_0==39||LA2_0==41||LA2_0==44) && ((!insideFunctionDecl))) {
                alt2=9;
            }
            else if ( (LA2_0==42) && ((insideFunctionDecl))) {
                alt2=10;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:39:8: NL
                    {
                    NL2=(Token)match(input,NL,FOLLOW_NL_in_stmt107);  
                    stream_NL.add(NL2);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 39:11: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:40:6: ECHO instr NL
                    {
                    ECHO3=(Token)match(input,ECHO,FOLLOW_ECHO_in_stmt116);  
                    stream_ECHO.add(ECHO3);


                    pushFollow(FOLLOW_instr_in_stmt118);
                    instr4=instr();

                    state._fsp--;

                    stream_instr.add(instr4.getTree());

                    NL5=(Token)match(input,NL,FOLLOW_NL_in_stmt120);  
                    stream_NL.add(NL5);


                    // AST REWRITE
                    // elements: instr, ECHO
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 40:20: -> ^( ECHO instr )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:40:23: ^( ECHO instr )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_ECHO.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_instr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:41:6: qualifiedID tk= '=' bexpr0 NL
                    {
                    pushFollow(FOLLOW_qualifiedID_in_stmt135);
                    qualifiedID6=qualifiedID();

                    state._fsp--;

                    stream_qualifiedID.add(qualifiedID6.getTree());

                    tk=(Token)match(input,34,FOLLOW_34_in_stmt139);  
                    stream_34.add(tk);


                    pushFollow(FOLLOW_bexpr0_in_stmt141);
                    bexpr07=bexpr0();

                    state._fsp--;

                    stream_bexpr0.add(bexpr07.getTree());

                    NL8=(Token)match(input,NL,FOLLOW_NL_in_stmt143);  
                    stream_NL.add(NL8);


                    // AST REWRITE
                    // elements: bexpr0, qualifiedID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 41:35: -> ^( ASSIGN[$tk,$tk.text] bexpr0 qualifiedID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:41:38: ^( ASSIGN[$tk,$tk.text] bexpr0 qualifiedID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        (TypeTree)adaptor.create(ASSIGN, tk, (tk!=null?tk.getText():null))
                        , root_1);

                        adaptor.addChild(root_1, stream_bexpr0.nextTree());

                        adaptor.addChild(root_1, stream_qualifiedID.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:42:6: functionCall
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_stmt161);
                    functionCall9=functionCall();

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall9.getTree());

                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:43:6: 'if' ^ '(' ! bexpr0 ')' ! stmt
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    string_literal10=(Token)match(input,40,FOLLOW_40_in_stmt171); 
                    string_literal10_tree = 
                    (TypeTree)adaptor.create(string_literal10)
                    ;
                    root_0 = (TypeTree)adaptor.becomeRoot(string_literal10_tree, root_0);


                    char_literal11=(Token)match(input,24,FOLLOW_24_in_stmt174); 

                    pushFollow(FOLLOW_bexpr0_in_stmt177);
                    bexpr012=bexpr0();

                    state._fsp--;

                    adaptor.addChild(root_0, bexpr012.getTree());

                    char_literal13=(Token)match(input,25,FOLLOW_25_in_stmt179); 

                    pushFollow(FOLLOW_stmt_in_stmt182);
                    stmt14=stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, stmt14.getTree());

                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:44:6: 'while' ^ '(' ! bexpr0 ')' ! stmt
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    string_literal15=(Token)match(input,45,FOLLOW_45_in_stmt189); 
                    string_literal15_tree = 
                    (TypeTree)adaptor.create(string_literal15)
                    ;
                    root_0 = (TypeTree)adaptor.becomeRoot(string_literal15_tree, root_0);


                    char_literal16=(Token)match(input,24,FOLLOW_24_in_stmt192); 

                    pushFollow(FOLLOW_bexpr0_in_stmt195);
                    bexpr017=bexpr0();

                    state._fsp--;

                    adaptor.addChild(root_0, bexpr017.getTree());

                    char_literal18=(Token)match(input,25,FOLLOW_25_in_stmt197); 

                    pushFollow(FOLLOW_stmt_in_stmt200);
                    stmt19=stmt();

                    state._fsp--;

                    adaptor.addChild(root_0, stmt19.getTree());

                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:45:6: block
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_block_in_stmt207);
                    block20=block();

                    state._fsp--;

                    adaptor.addChild(root_0, block20.getTree());

                    }
                    break;
                case 8 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:46:6: structDecl
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_structDecl_in_stmt214);
                    structDecl21=structDecl();

                    state._fsp--;

                    adaptor.addChild(root_0, structDecl21.getTree());

                    }
                    break;
                case 9 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:47:6: functionDecl
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_functionDecl_in_stmt221);
                    functionDecl22=functionDecl();

                    state._fsp--;

                    adaptor.addChild(root_0, functionDecl22.getTree());

                    }
                    break;
                case 10 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:48:6: returnStmt
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_returnStmt_in_stmt228);
                    returnStmt23=returnStmt();

                    state._fsp--;

                    adaptor.addChild(root_0, returnStmt23.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "stmt"


    public static class block_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:51:1: block : tk= '{' ( stmt )+ '}' NL -> ^( BLOCK[$tk,$tk.text] ( stmt )+ ) ;
    public final JPoclASTParser.block_return block() throws RecognitionException {
        JPoclASTParser.block_return retval = new JPoclASTParser.block_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token tk=null;
        Token char_literal25=null;
        Token NL26=null;
        JPoclASTParser.stmt_return stmt24 =null;


        TypeTree tk_tree=null;
        TypeTree char_literal25_tree=null;
        TypeTree NL26_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_NL=new RewriteRuleTokenStream(adaptor,"token NL");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:51:7: (tk= '{' ( stmt )+ '}' NL -> ^( BLOCK[$tk,$tk.text] ( stmt )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:51:9: tk= '{' ( stmt )+ '}' NL
            {
            tk=(Token)match(input,46,FOLLOW_46_in_block242);  
            stream_46.add(tk);


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:51:16: ( stmt )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==ECHO||LA3_0==ID||LA3_0==NL||LA3_0==40||LA3_0==43||(LA3_0 >= 45 && LA3_0 <= 46)) ) {
                    alt3=1;
                }
                else if ( (LA3_0==39||LA3_0==41||LA3_0==44) && ((!insideFunctionDecl))) {
                    alt3=1;
                }
                else if ( (LA3_0==42) && ((insideFunctionDecl))) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:51:16: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_block244);
            	    stmt24=stmt();

            	    state._fsp--;

            	    stream_stmt.add(stmt24.getTree());

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


            char_literal25=(Token)match(input,48,FOLLOW_48_in_block247);  
            stream_48.add(char_literal25);


            NL26=(Token)match(input,NL,FOLLOW_NL_in_block249);  
            stream_NL.add(NL26);


            // AST REWRITE
            // elements: stmt
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 51:29: -> ^( BLOCK[$tk,$tk.text] ( stmt )+ )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:51:32: ^( BLOCK[$tk,$tk.text] ( stmt )+ )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                (TypeTree)adaptor.create(BLOCK, tk, (tk!=null?tk.getText():null))
                , root_1);

                if ( !(stream_stmt.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_stmt.hasNext() ) {
                    adaptor.addChild(root_1, stream_stmt.nextTree());

                }
                stream_stmt.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "block"


    public static class functionDecl_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:53:1: functionDecl :{...}? => returnFunctionDecl ID '(' ( parametersDecl )? ')' '{' ( stmt )* '}' NL -> ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stmt )* ) ;
    public final JPoclASTParser.functionDecl_return functionDecl() throws RecognitionException {
        JPoclASTParser.functionDecl_return retval = new JPoclASTParser.functionDecl_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token ID28=null;
        Token char_literal29=null;
        Token char_literal31=null;
        Token char_literal32=null;
        Token char_literal34=null;
        Token NL35=null;
        JPoclASTParser.returnFunctionDecl_return returnFunctionDecl27 =null;

        JPoclASTParser.parametersDecl_return parametersDecl30 =null;

        JPoclASTParser.stmt_return stmt33 =null;


        TypeTree ID28_tree=null;
        TypeTree char_literal29_tree=null;
        TypeTree char_literal31_tree=null;
        TypeTree char_literal32_tree=null;
        TypeTree char_literal34_tree=null;
        TypeTree NL35_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_NL=new RewriteRuleTokenStream(adaptor,"token NL");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
        RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
        RewriteRuleSubtreeStream stream_parametersDecl=new RewriteRuleSubtreeStream(adaptor,"rule parametersDecl");
        RewriteRuleSubtreeStream stream_returnFunctionDecl=new RewriteRuleSubtreeStream(adaptor,"rule returnFunctionDecl");
        RewriteRuleSubtreeStream stream_stmt=new RewriteRuleSubtreeStream(adaptor,"rule stmt");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:53:13: ({...}? => returnFunctionDecl ID '(' ( parametersDecl )? ')' '{' ( stmt )* '}' NL -> ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stmt )* ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:53:15: {...}? => returnFunctionDecl ID '(' ( parametersDecl )? ')' '{' ( stmt )* '}' NL
            {
            if ( !((!insideFunctionDecl)) ) {
                throw new FailedPredicateException(input, "functionDecl", "!insideFunctionDecl");
            }

            insideFunctionDecl = true;

            pushFollow(FOLLOW_returnFunctionDecl_in_functionDecl288);
            returnFunctionDecl27=returnFunctionDecl();

            state._fsp--;

            stream_returnFunctionDecl.add(returnFunctionDecl27.getTree());

            ID28=(Token)match(input,ID,FOLLOW_ID_in_functionDecl290);  
            stream_ID.add(ID28);


            char_literal29=(Token)match(input,24,FOLLOW_24_in_functionDecl292);  
            stream_24.add(char_literal29);


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:55:35: ( parametersDecl )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==39||LA4_0==41||LA4_0==43) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:55:35: parametersDecl
                    {
                    pushFollow(FOLLOW_parametersDecl_in_functionDecl294);
                    parametersDecl30=parametersDecl();

                    state._fsp--;

                    stream_parametersDecl.add(parametersDecl30.getTree());

                    }
                    break;

            }


            char_literal31=(Token)match(input,25,FOLLOW_25_in_functionDecl297);  
            stream_25.add(char_literal31);


            char_literal32=(Token)match(input,46,FOLLOW_46_in_functionDecl298);  
            stream_46.add(char_literal32);


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:56:8: ( stmt )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==ECHO||LA5_0==ID||LA5_0==NL||LA5_0==40||LA5_0==43||(LA5_0 >= 45 && LA5_0 <= 46)) ) {
                    alt5=1;
                }
                else if ( (LA5_0==39||LA5_0==41||LA5_0==44) && ((!insideFunctionDecl))) {
                    alt5=1;
                }
                else if ( (LA5_0==42) && ((insideFunctionDecl))) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:56:8: stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_functionDecl307);
            	    stmt33=stmt();

            	    state._fsp--;

            	    stream_stmt.add(stmt33.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            char_literal34=(Token)match(input,48,FOLLOW_48_in_functionDecl317);  
            stream_48.add(char_literal34);


            NL35=(Token)match(input,NL,FOLLOW_NL_in_functionDecl318);  
            stream_NL.add(NL35);


            insideFunctionDecl = false;

            // AST REWRITE
            // elements: parametersDecl, returnFunctionDecl, stmt, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 59:8: -> ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stmt )* )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:59:11: ^( FUNCDECL returnFunctionDecl ID ( parametersDecl )? ( stmt )* )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                (TypeTree)adaptor.create(FUNCDECL, "FUNCDECL")
                , root_1);

                adaptor.addChild(root_1, stream_returnFunctionDecl.nextTree());

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:59:44: ( parametersDecl )?
                if ( stream_parametersDecl.hasNext() ) {
                    adaptor.addChild(root_1, stream_parametersDecl.nextTree());

                }
                stream_parametersDecl.reset();

                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:59:60: ( stmt )*
                while ( stream_stmt.hasNext() ) {
                    adaptor.addChild(root_1, stream_stmt.nextTree());

                }
                stream_stmt.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionDecl"


    public static class returnFunctionDecl_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "returnFunctionDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:62:1: returnFunctionDecl : ( 'int' | 'bool' | 'void' | 'struct' ! ID ^);
    public final JPoclASTParser.returnFunctionDecl_return returnFunctionDecl() throws RecognitionException {
        JPoclASTParser.returnFunctionDecl_return retval = new JPoclASTParser.returnFunctionDecl_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal36=null;
        Token string_literal37=null;
        Token string_literal38=null;
        Token string_literal39=null;
        Token ID40=null;

        TypeTree string_literal36_tree=null;
        TypeTree string_literal37_tree=null;
        TypeTree string_literal38_tree=null;
        TypeTree string_literal39_tree=null;
        TypeTree ID40_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:62:20: ( 'int' | 'bool' | 'void' | 'struct' ! ID ^)
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
            case 43:
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:62:22: 'int'
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    string_literal36=(Token)match(input,41,FOLLOW_41_in_returnFunctionDecl365); 
                    string_literal36_tree = 
                    (TypeTree)adaptor.create(string_literal36)
                    ;
                    adaptor.addChild(root_0, string_literal36_tree);


                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:63:13: 'bool'
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    string_literal37=(Token)match(input,39,FOLLOW_39_in_returnFunctionDecl379); 
                    string_literal37_tree = 
                    (TypeTree)adaptor.create(string_literal37)
                    ;
                    adaptor.addChild(root_0, string_literal37_tree);


                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:64:13: 'void'
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    string_literal38=(Token)match(input,44,FOLLOW_44_in_returnFunctionDecl393); 
                    string_literal38_tree = 
                    (TypeTree)adaptor.create(string_literal38)
                    ;
                    adaptor.addChild(root_0, string_literal38_tree);


                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:65:13: 'struct' ! ID ^
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    string_literal39=(Token)match(input,43,FOLLOW_43_in_returnFunctionDecl407); 

                    ID40=(Token)match(input,ID,FOLLOW_ID_in_returnFunctionDecl410); 
                    ID40_tree = 
                    (TypeTree)adaptor.create(ID40)
                    ;
                    root_0 = (TypeTree)adaptor.becomeRoot(ID40_tree, root_0);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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


    public static class returnStmt_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "returnStmt"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:1: returnStmt :{...}? => 'return' ( bexpr0 )? NL -> ^( 'return' ( bexpr0 )? ) ;
    public final JPoclASTParser.returnStmt_return returnStmt() throws RecognitionException {
        JPoclASTParser.returnStmt_return retval = new JPoclASTParser.returnStmt_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal41=null;
        Token NL43=null;
        JPoclASTParser.bexpr0_return bexpr042 =null;


        TypeTree string_literal41_tree=null;
        TypeTree NL43_tree=null;
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_NL=new RewriteRuleTokenStream(adaptor,"token NL");
        RewriteRuleSubtreeStream stream_bexpr0=new RewriteRuleSubtreeStream(adaptor,"rule bexpr0");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:12: ({...}? => 'return' ( bexpr0 )? NL -> ^( 'return' ( bexpr0 )? ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:14: {...}? => 'return' ( bexpr0 )? NL
            {
            if ( !((insideFunctionDecl)) ) {
                throw new FailedPredicateException(input, "returnStmt", "insideFunctionDecl");
            }

            string_literal41=(Token)match(input,42,FOLLOW_42_in_returnStmt436);  
            stream_42.add(string_literal41);


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:48: ( bexpr0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==BOOLEAN||(LA7_0 >= ID && LA7_0 <= INT)||LA7_0==24||LA7_0==27||LA7_0==29||LA7_0==43||LA7_0==49) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:48: bexpr0
                    {
                    pushFollow(FOLLOW_bexpr0_in_returnStmt438);
                    bexpr042=bexpr0();

                    state._fsp--;

                    stream_bexpr0.add(bexpr042.getTree());

                    }
                    break;

            }


            NL43=(Token)match(input,NL,FOLLOW_NL_in_returnStmt441);  
            stream_NL.add(NL43);


            // AST REWRITE
            // elements: bexpr0, 42
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 68:58: -> ^( 'return' ( bexpr0 )? )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:61: ^( 'return' ( bexpr0 )? )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                stream_42.nextNode()
                , root_1);

                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:68:72: ( bexpr0 )?
                if ( stream_bexpr0.hasNext() ) {
                    adaptor.addChild(root_1, stream_bexpr0.nextTree());

                }
                stream_bexpr0.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "returnStmt"


    public static class parametersDecl_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parametersDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:70:1: parametersDecl : parameterDecl ( ',' parameterDecl )* -> ^( PDECS ( parameterDecl )+ ) ;
    public final JPoclASTParser.parametersDecl_return parametersDecl() throws RecognitionException {
        JPoclASTParser.parametersDecl_return retval = new JPoclASTParser.parametersDecl_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token char_literal45=null;
        JPoclASTParser.parameterDecl_return parameterDecl44 =null;

        JPoclASTParser.parameterDecl_return parameterDecl46 =null;


        TypeTree char_literal45_tree=null;
        RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
        RewriteRuleSubtreeStream stream_parameterDecl=new RewriteRuleSubtreeStream(adaptor,"rule parameterDecl");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:70:16: ( parameterDecl ( ',' parameterDecl )* -> ^( PDECS ( parameterDecl )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:70:18: parameterDecl ( ',' parameterDecl )*
            {
            pushFollow(FOLLOW_parameterDecl_in_parametersDecl457);
            parameterDecl44=parameterDecl();

            state._fsp--;

            stream_parameterDecl.add(parameterDecl44.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:70:32: ( ',' parameterDecl )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==28) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:70:33: ',' parameterDecl
            	    {
            	    char_literal45=(Token)match(input,28,FOLLOW_28_in_parametersDecl460);  
            	    stream_28.add(char_literal45);


            	    pushFollow(FOLLOW_parameterDecl_in_parametersDecl462);
            	    parameterDecl46=parameterDecl();

            	    state._fsp--;

            	    stream_parameterDecl.add(parameterDecl46.getTree());

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            // AST REWRITE
            // elements: parameterDecl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 71:10: -> ^( PDECS ( parameterDecl )+ )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:71:13: ^( PDECS ( parameterDecl )+ )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                (TypeTree)adaptor.create(PDECS, "PDECS")
                , root_1);

                if ( !(stream_parameterDecl.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_parameterDecl.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameterDecl.nextTree());

                }
                stream_parameterDecl.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parametersDecl"


    public static class parameterDecl_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameterDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:74:1: parameterDecl : ( 'int' ID -> ^( 'int' ID ) | 'bool' ID -> ^( 'bool' ID ) | 'struct' structType= ID structID= ID -> ^( 'struct' $structType $structID) );
    public final JPoclASTParser.parameterDecl_return parameterDecl() throws RecognitionException {
        JPoclASTParser.parameterDecl_return retval = new JPoclASTParser.parameterDecl_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token structType=null;
        Token structID=null;
        Token string_literal47=null;
        Token ID48=null;
        Token string_literal49=null;
        Token ID50=null;
        Token string_literal51=null;

        TypeTree structType_tree=null;
        TypeTree structID_tree=null;
        TypeTree string_literal47_tree=null;
        TypeTree ID48_tree=null;
        TypeTree string_literal49_tree=null;
        TypeTree ID50_tree=null;
        TypeTree string_literal51_tree=null;
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:74:15: ( 'int' ID -> ^( 'int' ID ) | 'bool' ID -> ^( 'bool' ID ) | 'struct' structType= ID structID= ID -> ^( 'struct' $structType $structID) )
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:74:17: 'int' ID
                    {
                    string_literal47=(Token)match(input,41,FOLLOW_41_in_parameterDecl498);  
                    stream_41.add(string_literal47);


                    ID48=(Token)match(input,ID,FOLLOW_ID_in_parameterDecl500);  
                    stream_ID.add(ID48);


                    // AST REWRITE
                    // elements: ID, 41
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 74:26: -> ^( 'int' ID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:74:29: ^( 'int' ID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_41.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:75:10: 'bool' ID
                    {
                    string_literal49=(Token)match(input,39,FOLLOW_39_in_parameterDecl519);  
                    stream_39.add(string_literal49);


                    ID50=(Token)match(input,ID,FOLLOW_ID_in_parameterDecl521);  
                    stream_ID.add(ID50);


                    // AST REWRITE
                    // elements: ID, 39
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 75:20: -> ^( 'bool' ID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:75:23: ^( 'bool' ID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_39.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:76:10: 'struct' structType= ID structID= ID
                    {
                    string_literal51=(Token)match(input,43,FOLLOW_43_in_parameterDecl540);  
                    stream_43.add(string_literal51);


                    structType=(Token)match(input,ID,FOLLOW_ID_in_parameterDecl544);  
                    stream_ID.add(structType);


                    structID=(Token)match(input,ID,FOLLOW_ID_in_parameterDecl548);  
                    stream_ID.add(structID);


                    // AST REWRITE
                    // elements: structID, 43, structType
                    // token labels: structType, structID
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_structType=new RewriteRuleTokenStream(adaptor,"token structType",structType);
                    RewriteRuleTokenStream stream_structID=new RewriteRuleTokenStream(adaptor,"token structID",structID);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 76:45: -> ^( 'struct' $structType $structID)
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:76:48: ^( 'struct' $structType $structID)
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_43.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_structType.nextNode());

                        adaptor.addChild(root_1, stream_structID.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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


    public static class structDecl_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structDecl"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:79:1: structDecl : 'struct' ID '{' ( structMember )+ '}' -> ^( 'struct' ID ( structMember )+ ) ;
    public final JPoclASTParser.structDecl_return structDecl() throws RecognitionException {
        JPoclASTParser.structDecl_return retval = new JPoclASTParser.structDecl_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal52=null;
        Token ID53=null;
        Token char_literal54=null;
        Token char_literal56=null;
        JPoclASTParser.structMember_return structMember55 =null;


        TypeTree string_literal52_tree=null;
        TypeTree ID53_tree=null;
        TypeTree char_literal54_tree=null;
        TypeTree char_literal56_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_structMember=new RewriteRuleSubtreeStream(adaptor,"rule structMember");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:79:12: ( 'struct' ID '{' ( structMember )+ '}' -> ^( 'struct' ID ( structMember )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:79:14: 'struct' ID '{' ( structMember )+ '}'
            {
            string_literal52=(Token)match(input,43,FOLLOW_43_in_structDecl576);  
            stream_43.add(string_literal52);


            ID53=(Token)match(input,ID,FOLLOW_ID_in_structDecl578);  
            stream_ID.add(ID53);


            char_literal54=(Token)match(input,46,FOLLOW_46_in_structDecl580);  
            stream_46.add(char_literal54);


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:79:29: ( structMember )+
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
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:79:29: structMember
            	    {
            	    pushFollow(FOLLOW_structMember_in_structDecl581);
            	    structMember55=structMember();

            	    state._fsp--;

            	    stream_structMember.add(structMember55.getTree());

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


            char_literal56=(Token)match(input,48,FOLLOW_48_in_structDecl583);  
            stream_48.add(char_literal56);


            // AST REWRITE
            // elements: ID, 43, structMember
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 79:46: -> ^( 'struct' ID ( structMember )+ )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:79:49: ^( 'struct' ID ( structMember )+ )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                stream_43.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                if ( !(stream_structMember.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_structMember.hasNext() ) {
                    adaptor.addChild(root_1, stream_structMember.nextTree());

                }
                stream_structMember.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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


    public static class structMember_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structMember"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:82:1: structMember : ( 'int' ID ';' -> ^( 'int' ID ) | 'bool' ID ';' -> ^( 'bool' ID ) | 'struct' structType= ID structID= ID ';' -> ^( 'struct' $structType $structID) );
    public final JPoclASTParser.structMember_return structMember() throws RecognitionException {
        JPoclASTParser.structMember_return retval = new JPoclASTParser.structMember_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token structType=null;
        Token structID=null;
        Token string_literal57=null;
        Token ID58=null;
        Token char_literal59=null;
        Token string_literal60=null;
        Token ID61=null;
        Token char_literal62=null;
        Token string_literal63=null;
        Token char_literal64=null;

        TypeTree structType_tree=null;
        TypeTree structID_tree=null;
        TypeTree string_literal57_tree=null;
        TypeTree ID58_tree=null;
        TypeTree char_literal59_tree=null;
        TypeTree string_literal60_tree=null;
        TypeTree ID61_tree=null;
        TypeTree char_literal62_tree=null;
        TypeTree string_literal63_tree=null;
        TypeTree char_literal64_tree=null;
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_41=new RewriteRuleTokenStream(adaptor,"token 41");
        RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:82:14: ( 'int' ID ';' -> ^( 'int' ID ) | 'bool' ID ';' -> ^( 'bool' ID ) | 'struct' structType= ID structID= ID ';' -> ^( 'struct' $structType $structID) )
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
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:82:16: 'int' ID ';'
                    {
                    string_literal57=(Token)match(input,41,FOLLOW_41_in_structMember609);  
                    stream_41.add(string_literal57);


                    ID58=(Token)match(input,ID,FOLLOW_ID_in_structMember611);  
                    stream_ID.add(ID58);


                    char_literal59=(Token)match(input,31,FOLLOW_31_in_structMember613);  
                    stream_31.add(char_literal59);


                    // AST REWRITE
                    // elements: ID, 41
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 82:29: -> ^( 'int' ID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:82:32: ^( 'int' ID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_41.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:83:10: 'bool' ID ';'
                    {
                    string_literal60=(Token)match(input,39,FOLLOW_39_in_structMember632);  
                    stream_39.add(string_literal60);


                    ID61=(Token)match(input,ID,FOLLOW_ID_in_structMember634);  
                    stream_ID.add(ID61);


                    char_literal62=(Token)match(input,31,FOLLOW_31_in_structMember636);  
                    stream_31.add(char_literal62);


                    // AST REWRITE
                    // elements: 39, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 83:24: -> ^( 'bool' ID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:83:27: ^( 'bool' ID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_39.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:84:10: 'struct' structType= ID structID= ID ';'
                    {
                    string_literal63=(Token)match(input,43,FOLLOW_43_in_structMember655);  
                    stream_43.add(string_literal63);


                    structType=(Token)match(input,ID,FOLLOW_ID_in_structMember659);  
                    stream_ID.add(structType);


                    structID=(Token)match(input,ID,FOLLOW_ID_in_structMember663);  
                    stream_ID.add(structID);


                    char_literal64=(Token)match(input,31,FOLLOW_31_in_structMember665);  
                    stream_31.add(char_literal64);


                    // AST REWRITE
                    // elements: 43, structID, structType
                    // token labels: structType, structID
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_structType=new RewriteRuleTokenStream(adaptor,"token structType",structType);
                    RewriteRuleTokenStream stream_structID=new RewriteRuleTokenStream(adaptor,"token structID",structID);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 84:49: -> ^( 'struct' $structType $structID)
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:84:52: ^( 'struct' $structType $structID)
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_43.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_structType.nextNode());

                        adaptor.addChild(root_1, stream_structID.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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


    public static class instr_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "instr"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:87:1: instr : bexpr0 ;
    public final JPoclASTParser.instr_return instr() throws RecognitionException {
        JPoclASTParser.instr_return retval = new JPoclASTParser.instr_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        JPoclASTParser.bexpr0_return bexpr065 =null;



        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:87:9: ( bexpr0 )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:87:11: bexpr0
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_bexpr0_in_instr695);
            bexpr065=bexpr0();

            state._fsp--;

            adaptor.addChild(root_0, bexpr065.getTree());

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "instr"


    public static class bexpr0_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bexpr0"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:90:1: bexpr0 : bexpr1 ( '||' ^ bexpr1 )* ;
    public final JPoclASTParser.bexpr0_return bexpr0() throws RecognitionException {
        JPoclASTParser.bexpr0_return retval = new JPoclASTParser.bexpr0_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal67=null;
        JPoclASTParser.bexpr1_return bexpr166 =null;

        JPoclASTParser.bexpr1_return bexpr168 =null;


        TypeTree string_literal67_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:90:9: ( bexpr1 ( '||' ^ bexpr1 )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:90:11: bexpr1 ( '||' ^ bexpr1 )*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_bexpr1_in_bexpr0709);
            bexpr166=bexpr1();

            state._fsp--;

            adaptor.addChild(root_0, bexpr166.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:90:18: ( '||' ^ bexpr1 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==47) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:90:19: '||' ^ bexpr1
            	    {
            	    string_literal67=(Token)match(input,47,FOLLOW_47_in_bexpr0712); 
            	    string_literal67_tree = 
            	    (TypeTree)adaptor.create(string_literal67)
            	    ;
            	    root_0 = (TypeTree)adaptor.becomeRoot(string_literal67_tree, root_0);


            	    pushFollow(FOLLOW_bexpr1_in_bexpr0715);
            	    bexpr168=bexpr1();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bexpr168.getTree());

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bexpr0"


    public static class bexpr1_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bexpr1"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:93:1: bexpr1 : bexpr2 ( '&&' ^ bexpr2 )* ;
    public final JPoclASTParser.bexpr1_return bexpr1() throws RecognitionException {
        JPoclASTParser.bexpr1_return retval = new JPoclASTParser.bexpr1_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal70=null;
        JPoclASTParser.bexpr2_return bexpr269 =null;

        JPoclASTParser.bexpr2_return bexpr271 =null;


        TypeTree string_literal70_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:93:9: ( bexpr2 ( '&&' ^ bexpr2 )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:93:11: bexpr2 ( '&&' ^ bexpr2 )*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_bexpr2_in_bexpr1731);
            bexpr269=bexpr2();

            state._fsp--;

            adaptor.addChild(root_0, bexpr269.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:93:18: ( '&&' ^ bexpr2 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==23) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:93:19: '&&' ^ bexpr2
            	    {
            	    string_literal70=(Token)match(input,23,FOLLOW_23_in_bexpr1734); 
            	    string_literal70_tree = 
            	    (TypeTree)adaptor.create(string_literal70)
            	    ;
            	    root_0 = (TypeTree)adaptor.becomeRoot(string_literal70_tree, root_0);


            	    pushFollow(FOLLOW_bexpr2_in_bexpr1737);
            	    bexpr271=bexpr2();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bexpr271.getTree());

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bexpr1"


    public static class bexpr2_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bexpr2"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:96:1: bexpr2 : bexpr3 ( '==' ^ bexpr3 )* ;
    public final JPoclASTParser.bexpr2_return bexpr2() throws RecognitionException {
        JPoclASTParser.bexpr2_return retval = new JPoclASTParser.bexpr2_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal73=null;
        JPoclASTParser.bexpr3_return bexpr372 =null;

        JPoclASTParser.bexpr3_return bexpr374 =null;


        TypeTree string_literal73_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:96:9: ( bexpr3 ( '==' ^ bexpr3 )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:96:11: bexpr3 ( '==' ^ bexpr3 )*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_bexpr3_in_bexpr2754);
            bexpr372=bexpr3();

            state._fsp--;

            adaptor.addChild(root_0, bexpr372.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:96:18: ( '==' ^ bexpr3 )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==35) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:96:19: '==' ^ bexpr3
            	    {
            	    string_literal73=(Token)match(input,35,FOLLOW_35_in_bexpr2757); 
            	    string_literal73_tree = 
            	    (TypeTree)adaptor.create(string_literal73)
            	    ;
            	    root_0 = (TypeTree)adaptor.becomeRoot(string_literal73_tree, root_0);


            	    pushFollow(FOLLOW_bexpr3_in_bexpr2760);
            	    bexpr374=bexpr3();

            	    state._fsp--;

            	    adaptor.addChild(root_0, bexpr374.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bexpr2"


    public static class bexpr3_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "bexpr3"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:99:1: bexpr3 : expr0 ( ( '<' | '<=' | '>' | '>=' ) ^ expr0 )* ;
    public final JPoclASTParser.bexpr3_return bexpr3() throws RecognitionException {
        JPoclASTParser.bexpr3_return retval = new JPoclASTParser.bexpr3_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token set76=null;
        JPoclASTParser.expr0_return expr075 =null;

        JPoclASTParser.expr0_return expr077 =null;


        TypeTree set76_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:99:9: ( expr0 ( ( '<' | '<=' | '>' | '>=' ) ^ expr0 )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:99:11: expr0 ( ( '<' | '<=' | '>' | '>=' ) ^ expr0 )*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_expr0_in_bexpr3775);
            expr075=expr0();

            state._fsp--;

            adaptor.addChild(root_0, expr075.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:99:17: ( ( '<' | '<=' | '>' | '>=' ) ^ expr0 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0 >= 32 && LA15_0 <= 33)||(LA15_0 >= 36 && LA15_0 <= 37)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:99:18: ( '<' | '<=' | '>' | '>=' ) ^ expr0
            	    {
            	    set76=(Token)input.LT(1);

            	    set76=(Token)input.LT(1);

            	    if ( (input.LA(1) >= 32 && input.LA(1) <= 33)||(input.LA(1) >= 36 && input.LA(1) <= 37) ) {
            	        input.consume();
            	        root_0 = (TypeTree)adaptor.becomeRoot(
            	        (TypeTree)adaptor.create(set76)
            	        , root_0);
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_expr0_in_bexpr3789);
            	    expr077=expr0();

            	    state._fsp--;

            	    adaptor.addChild(root_0, expr077.getTree());

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "bexpr3"


    public static class expr0_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr0"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:102:1: expr0 : expr1 ( ( '+' | '-' ) ^ expr1 )* ;
    public final JPoclASTParser.expr0_return expr0() throws RecognitionException {
        JPoclASTParser.expr0_return retval = new JPoclASTParser.expr0_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token set79=null;
        JPoclASTParser.expr1_return expr178 =null;

        JPoclASTParser.expr1_return expr180 =null;


        TypeTree set79_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:102:8: ( expr1 ( ( '+' | '-' ) ^ expr1 )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:102:10: expr1 ( ( '+' | '-' ) ^ expr1 )*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_expr1_in_expr0806);
            expr178=expr1();

            state._fsp--;

            adaptor.addChild(root_0, expr178.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:102:16: ( ( '+' | '-' ) ^ expr1 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==27||LA16_0==29) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:102:17: ( '+' | '-' ) ^ expr1
            	    {
            	    set79=(Token)input.LT(1);

            	    set79=(Token)input.LT(1);

            	    if ( input.LA(1)==27||input.LA(1)==29 ) {
            	        input.consume();
            	        root_0 = (TypeTree)adaptor.becomeRoot(
            	        (TypeTree)adaptor.create(set79)
            	        , root_0);
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_expr1_in_expr0818);
            	    expr180=expr1();

            	    state._fsp--;

            	    adaptor.addChild(root_0, expr180.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr0"


    public static class expr1_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr1"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:105:1: expr1 : expr2 ( ( '*' | '/' | '%' ) ^ expr2 )* ;
    public final JPoclASTParser.expr1_return expr1() throws RecognitionException {
        JPoclASTParser.expr1_return retval = new JPoclASTParser.expr1_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token set82=null;
        JPoclASTParser.expr2_return expr281 =null;

        JPoclASTParser.expr2_return expr283 =null;


        TypeTree set82_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:105:8: ( expr2 ( ( '*' | '/' | '%' ) ^ expr2 )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:105:10: expr2 ( ( '*' | '/' | '%' ) ^ expr2 )*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_expr2_in_expr1833);
            expr281=expr2();

            state._fsp--;

            adaptor.addChild(root_0, expr281.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:105:16: ( ( '*' | '/' | '%' ) ^ expr2 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==22||LA17_0==26||LA17_0==30) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:105:18: ( '*' | '/' | '%' ) ^ expr2
            	    {
            	    set82=(Token)input.LT(1);

            	    set82=(Token)input.LT(1);

            	    if ( input.LA(1)==22||input.LA(1)==26||input.LA(1)==30 ) {
            	        input.consume();
            	        root_0 = (TypeTree)adaptor.becomeRoot(
            	        (TypeTree)adaptor.create(set82)
            	        , root_0);
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_expr2_in_expr1849);
            	    expr283=expr2();

            	    state._fsp--;

            	    adaptor.addChild(root_0, expr283.getTree());

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr1"


    public static class expr2_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr2"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:1: expr2 : (tk= '+' expr2 -> ^( PLUS[$tk,\"plus\"] expr2 ) |tk= '-' expr2 -> ^( SIGN[$tk,\"sign\"] expr2 ) | expr3 );
    public final JPoclASTParser.expr2_return expr2() throws RecognitionException {
        JPoclASTParser.expr2_return retval = new JPoclASTParser.expr2_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token tk=null;
        JPoclASTParser.expr2_return expr284 =null;

        JPoclASTParser.expr2_return expr285 =null;

        JPoclASTParser.expr3_return expr386 =null;


        TypeTree tk_tree=null;
        RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
        RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");
        RewriteRuleSubtreeStream stream_expr2=new RewriteRuleSubtreeStream(adaptor,"rule expr2");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:7: (tk= '+' expr2 -> ^( PLUS[$tk,\"plus\"] expr2 ) |tk= '-' expr2 -> ^( SIGN[$tk,\"sign\"] expr2 ) | expr3 )
            int alt18=3;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt18=1;
                }
                break;
            case 29:
                {
                alt18=2;
                }
                break;
            case BOOLEAN:
            case ID:
            case INT:
            case 24:
            case 43:
            case 49:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;

            }

            switch (alt18) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:9: tk= '+' expr2
                    {
                    tk=(Token)match(input,27,FOLLOW_27_in_expr2863);  
                    stream_27.add(tk);


                    pushFollow(FOLLOW_expr2_in_expr2865);
                    expr284=expr2();

                    state._fsp--;

                    stream_expr2.add(expr284.getTree());

                    // AST REWRITE
                    // elements: expr2
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 108:22: -> ^( PLUS[$tk,\"plus\"] expr2 )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:25: ^( PLUS[$tk,\"plus\"] expr2 )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        (TypeTree)adaptor.create(PLUS, tk, "plus")
                        , root_1);

                        adaptor.addChild(root_1, stream_expr2.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:53: tk= '-' expr2
                    {
                    tk=(Token)match(input,29,FOLLOW_29_in_expr2880);  
                    stream_29.add(tk);


                    pushFollow(FOLLOW_expr2_in_expr2882);
                    expr285=expr2();

                    state._fsp--;

                    stream_expr2.add(expr285.getTree());

                    // AST REWRITE
                    // elements: expr2
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 108:66: -> ^( SIGN[$tk,\"sign\"] expr2 )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:69: ^( SIGN[$tk,\"sign\"] expr2 )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        (TypeTree)adaptor.create(SIGN, tk, "sign")
                        , root_1);

                        adaptor.addChild(root_1, stream_expr2.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:108:97: expr3
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_expr3_in_expr2895);
                    expr386=expr3();

                    state._fsp--;

                    adaptor.addChild(root_0, expr386.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr2"


    public static class expr2b_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr2b"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:1: expr2b : (tk= '+' expr2b -> ^( PLUS[$tk,\"plus\"] expr2b ) |tk= '-' expr2b -> ^( SIGN[$tk,\"sign\"] expr2b ) | expr4 );
    public final JPoclASTParser.expr2b_return expr2b() throws RecognitionException {
        JPoclASTParser.expr2b_return retval = new JPoclASTParser.expr2b_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token tk=null;
        JPoclASTParser.expr2b_return expr2b87 =null;

        JPoclASTParser.expr2b_return expr2b88 =null;

        JPoclASTParser.expr4_return expr489 =null;


        TypeTree tk_tree=null;
        RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
        RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");
        RewriteRuleSubtreeStream stream_expr2b=new RewriteRuleSubtreeStream(adaptor,"rule expr2b");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:8: (tk= '+' expr2b -> ^( PLUS[$tk,\"plus\"] expr2b ) |tk= '-' expr2b -> ^( SIGN[$tk,\"sign\"] expr2b ) | expr4 )
            int alt19=3;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt19=1;
                }
                break;
            case 29:
                {
                alt19=2;
                }
                break;
            case BOOLEAN:
            case ID:
            case INT:
            case 24:
            case 43:
            case 49:
                {
                alt19=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;

            }

            switch (alt19) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:10: tk= '+' expr2b
                    {
                    tk=(Token)match(input,27,FOLLOW_27_in_expr2b909);  
                    stream_27.add(tk);


                    pushFollow(FOLLOW_expr2b_in_expr2b911);
                    expr2b87=expr2b();

                    state._fsp--;

                    stream_expr2b.add(expr2b87.getTree());

                    // AST REWRITE
                    // elements: expr2b
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 111:24: -> ^( PLUS[$tk,\"plus\"] expr2b )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:27: ^( PLUS[$tk,\"plus\"] expr2b )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        (TypeTree)adaptor.create(PLUS, tk, "plus")
                        , root_1);

                        adaptor.addChild(root_1, stream_expr2b.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:56: tk= '-' expr2b
                    {
                    tk=(Token)match(input,29,FOLLOW_29_in_expr2b926);  
                    stream_29.add(tk);


                    pushFollow(FOLLOW_expr2b_in_expr2b928);
                    expr2b88=expr2b();

                    state._fsp--;

                    stream_expr2b.add(expr2b88.getTree());

                    // AST REWRITE
                    // elements: expr2b
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 111:70: -> ^( SIGN[$tk,\"sign\"] expr2b )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:73: ^( SIGN[$tk,\"sign\"] expr2b )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        (TypeTree)adaptor.create(SIGN, tk, "sign")
                        , root_1);

                        adaptor.addChild(root_1, stream_expr2b.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:111:102: expr4
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_expr4_in_expr2b941);
                    expr489=expr4();

                    state._fsp--;

                    adaptor.addChild(root_0, expr489.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr2b"


    public static class expr3_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr3"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:114:1: expr3 : expr4 ( '!' ^)* ;
    public final JPoclASTParser.expr3_return expr3() throws RecognitionException {
        JPoclASTParser.expr3_return retval = new JPoclASTParser.expr3_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token char_literal91=null;
        JPoclASTParser.expr4_return expr490 =null;


        TypeTree char_literal91_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:114:7: ( expr4 ( '!' ^)* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:114:9: expr4 ( '!' ^)*
            {
            root_0 = (TypeTree)adaptor.nil();


            pushFollow(FOLLOW_expr4_in_expr3954);
            expr490=expr4();

            state._fsp--;

            adaptor.addChild(root_0, expr490.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:114:18: ( '!' ^)*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==21) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:114:18: '!' ^
            	    {
            	    char_literal91=(Token)match(input,21,FOLLOW_21_in_expr3956); 
            	    char_literal91_tree = 
            	    (TypeTree)adaptor.create(char_literal91)
            	    ;
            	    root_0 = (TypeTree)adaptor.becomeRoot(char_literal91_tree, root_0);


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr3"


    public static class expr4_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr4"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:1: expr4 : ( expr5 -> expr5 ) ( ( '!' -> ^( '!' $expr4) )* '^' expr2b -> ^( '^' $expr4 expr2b ) )? ;
    public final JPoclASTParser.expr4_return expr4() throws RecognitionException {
        JPoclASTParser.expr4_return retval = new JPoclASTParser.expr4_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token char_literal93=null;
        Token char_literal94=null;
        JPoclASTParser.expr5_return expr592 =null;

        JPoclASTParser.expr2b_return expr2b95 =null;


        TypeTree char_literal93_tree=null;
        TypeTree char_literal94_tree=null;
        RewriteRuleTokenStream stream_21=new RewriteRuleTokenStream(adaptor,"token 21");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_expr2b=new RewriteRuleSubtreeStream(adaptor,"rule expr2b");
        RewriteRuleSubtreeStream stream_expr5=new RewriteRuleSubtreeStream(adaptor,"rule expr5");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:7: ( ( expr5 -> expr5 ) ( ( '!' -> ^( '!' $expr4) )* '^' expr2b -> ^( '^' $expr4 expr2b ) )? )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:9: ( expr5 -> expr5 ) ( ( '!' -> ^( '!' $expr4) )* '^' expr2b -> ^( '^' $expr4 expr2b ) )?
            {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:9: ( expr5 -> expr5 )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:10: expr5
            {
            pushFollow(FOLLOW_expr5_in_expr4970);
            expr592=expr5();

            state._fsp--;

            stream_expr5.add(expr592.getTree());

            // AST REWRITE
            // elements: expr5
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 117:16: -> expr5
            {
                adaptor.addChild(root_0, stream_expr5.nextTree());

            }


            retval.tree = root_0;

            }


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:26: ( ( '!' -> ^( '!' $expr4) )* '^' expr2b -> ^( '^' $expr4 expr2b ) )?
            int alt22=2;
            alt22 = dfa22.predict(input);
            switch (alt22) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:27: ( '!' -> ^( '!' $expr4) )* '^' expr2b
                    {
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:27: ( '!' -> ^( '!' $expr4) )*
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==21) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:28: '!'
                    	    {
                    	    char_literal93=(Token)match(input,21,FOLLOW_21_in_expr4979);  
                    	    stream_21.add(char_literal93);


                    	    // AST REWRITE
                    	    // elements: 21, expr4
                    	    // token labels: 
                    	    // rule labels: retval
                    	    // token list labels: 
                    	    // rule list labels: 
                    	    // wildcard labels: 
                    	    retval.tree = root_0;
                    	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    	    root_0 = (TypeTree)adaptor.nil();
                    	    // 117:32: -> ^( '!' $expr4)
                    	    {
                    	        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:35: ^( '!' $expr4)
                    	        {
                    	        TypeTree root_1 = (TypeTree)adaptor.nil();
                    	        root_1 = (TypeTree)adaptor.becomeRoot(
                    	        stream_21.nextNode()
                    	        , root_1);

                    	        adaptor.addChild(root_1, stream_retval.nextTree());

                    	        adaptor.addChild(root_0, root_1);
                    	        }

                    	    }


                    	    retval.tree = root_0;

                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);


                    char_literal94=(Token)match(input,38,FOLLOW_38_in_expr4992);  
                    stream_38.add(char_literal94);


                    pushFollow(FOLLOW_expr2b_in_expr4994);
                    expr2b95=expr2b();

                    state._fsp--;

                    stream_expr2b.add(expr2b95.getTree());

                    // AST REWRITE
                    // elements: expr4, expr2b, 38
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 117:62: -> ^( '^' $expr4 expr2b )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:117:65: ^( '^' $expr4 expr2b )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_38.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_1, stream_expr2b.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr4"


    public static class expr5_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr5"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:120:1: expr5 : ( INT | BOOLEAN | '(' ! bexpr0 ^ ')' !| '~' ^ expr5 | functionCall | structValue | qualifiedID );
    public final JPoclASTParser.expr5_return expr5() throws RecognitionException {
        JPoclASTParser.expr5_return retval = new JPoclASTParser.expr5_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token INT96=null;
        Token BOOLEAN97=null;
        Token char_literal98=null;
        Token char_literal100=null;
        Token char_literal101=null;
        JPoclASTParser.bexpr0_return bexpr099 =null;

        JPoclASTParser.expr5_return expr5102 =null;

        JPoclASTParser.functionCall_return functionCall103 =null;

        JPoclASTParser.structValue_return structValue104 =null;

        JPoclASTParser.qualifiedID_return qualifiedID105 =null;


        TypeTree INT96_tree=null;
        TypeTree BOOLEAN97_tree=null;
        TypeTree char_literal98_tree=null;
        TypeTree char_literal100_tree=null;
        TypeTree char_literal101_tree=null;

        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:120:7: ( INT | BOOLEAN | '(' ! bexpr0 ^ ')' !| '~' ^ expr5 | functionCall | structValue | qualifiedID )
            int alt23=7;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt23=1;
                }
                break;
            case BOOLEAN:
                {
                alt23=2;
                }
                break;
            case 24:
                {
                alt23=3;
                }
                break;
            case 49:
                {
                alt23=4;
                }
                break;
            case ID:
                {
                int LA23_5 = input.LA(2);

                if ( (LA23_5==24) ) {
                    alt23=5;
                }
                else if ( (LA23_5==DOT||LA23_5==NL||(LA23_5 >= 21 && LA23_5 <= 23)||(LA23_5 >= 25 && LA23_5 <= 30)||(LA23_5 >= 32 && LA23_5 <= 33)||(LA23_5 >= 35 && LA23_5 <= 38)||(LA23_5 >= 47 && LA23_5 <= 48)) ) {
                    alt23=7;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 5, input);

                    throw nvae;

                }
                }
                break;
            case 43:
                {
                alt23=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:120:9: INT
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    INT96=(Token)match(input,INT,FOLLOW_INT_in_expr51017); 
                    INT96_tree = 
                    (TypeTree)adaptor.create(INT96)
                    ;
                    adaptor.addChild(root_0, INT96_tree);


                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:121:7: BOOLEAN
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    BOOLEAN97=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_expr51026); 
                    BOOLEAN97_tree = 
                    (TypeTree)adaptor.create(BOOLEAN97)
                    ;
                    adaptor.addChild(root_0, BOOLEAN97_tree);


                    }
                    break;
                case 3 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:122:7: '(' ! bexpr0 ^ ')' !
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    char_literal98=(Token)match(input,24,FOLLOW_24_in_expr51035); 

                    pushFollow(FOLLOW_bexpr0_in_expr51038);
                    bexpr099=bexpr0();

                    state._fsp--;

                    root_0 = (TypeTree)adaptor.becomeRoot(bexpr099.getTree(), root_0);

                    char_literal100=(Token)match(input,25,FOLLOW_25_in_expr51041); 

                    }
                    break;
                case 4 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:123:7: '~' ^ expr5
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    char_literal101=(Token)match(input,49,FOLLOW_49_in_expr51050); 
                    char_literal101_tree = 
                    (TypeTree)adaptor.create(char_literal101)
                    ;
                    root_0 = (TypeTree)adaptor.becomeRoot(char_literal101_tree, root_0);


                    pushFollow(FOLLOW_expr5_in_expr51053);
                    expr5102=expr5();

                    state._fsp--;

                    adaptor.addChild(root_0, expr5102.getTree());

                    }
                    break;
                case 5 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:124:7: functionCall
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_functionCall_in_expr51061);
                    functionCall103=functionCall();

                    state._fsp--;

                    adaptor.addChild(root_0, functionCall103.getTree());

                    }
                    break;
                case 6 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:125:7: structValue
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_structValue_in_expr51069);
                    structValue104=structValue();

                    state._fsp--;

                    adaptor.addChild(root_0, structValue104.getTree());

                    }
                    break;
                case 7 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:126:7: qualifiedID
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    pushFollow(FOLLOW_qualifiedID_in_expr51077);
                    qualifiedID105=qualifiedID();

                    state._fsp--;

                    adaptor.addChild(root_0, qualifiedID105.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr5"


    public static class functionCall_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionCall"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:1: functionCall : ( ID '(' ( parameters )? ')' -> ^( FUNCCALL ID ( parameters )? ) ) ( DOT qualifiedID -> ^( DOT $functionCall qualifiedID ) )? ;
    public final JPoclASTParser.functionCall_return functionCall() throws RecognitionException {
        JPoclASTParser.functionCall_return retval = new JPoclASTParser.functionCall_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token ID106=null;
        Token char_literal107=null;
        Token char_literal109=null;
        Token DOT110=null;
        JPoclASTParser.parameters_return parameters108 =null;

        JPoclASTParser.qualifiedID_return qualifiedID111 =null;


        TypeTree ID106_tree=null;
        TypeTree char_literal107_tree=null;
        TypeTree char_literal109_tree=null;
        TypeTree DOT110_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
        RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
        RewriteRuleSubtreeStream stream_qualifiedID=new RewriteRuleSubtreeStream(adaptor,"rule qualifiedID");
        RewriteRuleSubtreeStream stream_parameters=new RewriteRuleSubtreeStream(adaptor,"rule parameters");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:14: ( ( ID '(' ( parameters )? ')' -> ^( FUNCCALL ID ( parameters )? ) ) ( DOT qualifiedID -> ^( DOT $functionCall qualifiedID ) )? )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:16: ( ID '(' ( parameters )? ')' -> ^( FUNCCALL ID ( parameters )? ) ) ( DOT qualifiedID -> ^( DOT $functionCall qualifiedID ) )?
            {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:16: ( ID '(' ( parameters )? ')' -> ^( FUNCCALL ID ( parameters )? ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:17: ID '(' ( parameters )? ')'
            {
            ID106=(Token)match(input,ID,FOLLOW_ID_in_functionCall1091);  
            stream_ID.add(ID106);


            char_literal107=(Token)match(input,24,FOLLOW_24_in_functionCall1092);  
            stream_24.add(char_literal107);


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:23: ( parameters )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==BOOLEAN||(LA24_0 >= ID && LA24_0 <= INT)||LA24_0==24||LA24_0==27||LA24_0==29||LA24_0==43||LA24_0==49) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:23: parameters
                    {
                    pushFollow(FOLLOW_parameters_in_functionCall1094);
                    parameters108=parameters();

                    state._fsp--;

                    stream_parameters.add(parameters108.getTree());

                    }
                    break;

            }


            char_literal109=(Token)match(input,25,FOLLOW_25_in_functionCall1097);  
            stream_25.add(char_literal109);


            // AST REWRITE
            // elements: parameters, ID
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 129:39: -> ^( FUNCCALL ID ( parameters )? )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:42: ^( FUNCCALL ID ( parameters )? )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                (TypeTree)adaptor.create(FUNCCALL, "FUNCCALL")
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:129:56: ( parameters )?
                if ( stream_parameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_parameters.nextTree());

                }
                stream_parameters.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:130:8: ( DOT qualifiedID -> ^( DOT $functionCall qualifiedID ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==DOT) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:130:9: DOT qualifiedID
                    {
                    DOT110=(Token)match(input,DOT,FOLLOW_DOT_in_functionCall1119);  
                    stream_DOT.add(DOT110);


                    pushFollow(FOLLOW_qualifiedID_in_functionCall1121);
                    qualifiedID111=qualifiedID();

                    state._fsp--;

                    stream_qualifiedID.add(qualifiedID111.getTree());

                    // AST REWRITE
                    // elements: functionCall, qualifiedID, DOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 130:25: -> ^( DOT $functionCall qualifiedID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:130:28: ^( DOT $functionCall qualifiedID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_DOT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_1, stream_qualifiedID.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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


    public static class parameters_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parameters"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:133:1: parameters : bexpr0 ( ',' bexpr0 )* -> ( bexpr0 )+ ;
    public final JPoclASTParser.parameters_return parameters() throws RecognitionException {
        JPoclASTParser.parameters_return retval = new JPoclASTParser.parameters_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token char_literal113=null;
        JPoclASTParser.bexpr0_return bexpr0112 =null;

        JPoclASTParser.bexpr0_return bexpr0114 =null;


        TypeTree char_literal113_tree=null;
        RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
        RewriteRuleSubtreeStream stream_bexpr0=new RewriteRuleSubtreeStream(adaptor,"rule bexpr0");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:133:12: ( bexpr0 ( ',' bexpr0 )* -> ( bexpr0 )+ )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:133:14: bexpr0 ( ',' bexpr0 )*
            {
            pushFollow(FOLLOW_bexpr0_in_parameters1150);
            bexpr0112=bexpr0();

            state._fsp--;

            stream_bexpr0.add(bexpr0112.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:133:21: ( ',' bexpr0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==28) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:133:22: ',' bexpr0
            	    {
            	    char_literal113=(Token)match(input,28,FOLLOW_28_in_parameters1153);  
            	    stream_28.add(char_literal113);


            	    pushFollow(FOLLOW_bexpr0_in_parameters1155);
            	    bexpr0114=bexpr0();

            	    state._fsp--;

            	    stream_bexpr0.add(bexpr0114.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            // AST REWRITE
            // elements: bexpr0
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 133:35: -> ( bexpr0 )+
            {
                if ( !(stream_bexpr0.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_bexpr0.hasNext() ) {
                    adaptor.addChild(root_0, stream_bexpr0.nextTree());

                }
                stream_bexpr0.reset();

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }

        catch (RecognitionException re) {
        	reportError(re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parameters"


    public static class structValue_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "structValue"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:1: structValue : ( 'struct' ID '{' bexpr0 ( ',' bexpr0 )* '}' -> ^( 'struct' ID ( bexpr0 )+ ) ) ( DOT qualifiedID -> ^( DOT $structValue qualifiedID ) )? ;
    public final JPoclASTParser.structValue_return structValue() throws RecognitionException {
        JPoclASTParser.structValue_return retval = new JPoclASTParser.structValue_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token string_literal115=null;
        Token ID116=null;
        Token char_literal117=null;
        Token char_literal119=null;
        Token char_literal121=null;
        Token DOT122=null;
        JPoclASTParser.bexpr0_return bexpr0118 =null;

        JPoclASTParser.bexpr0_return bexpr0120 =null;

        JPoclASTParser.qualifiedID_return qualifiedID123 =null;


        TypeTree string_literal115_tree=null;
        TypeTree ID116_tree=null;
        TypeTree char_literal117_tree=null;
        TypeTree char_literal119_tree=null;
        TypeTree char_literal121_tree=null;
        TypeTree DOT122_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");
        RewriteRuleSubtreeStream stream_qualifiedID=new RewriteRuleSubtreeStream(adaptor,"rule qualifiedID");
        RewriteRuleSubtreeStream stream_bexpr0=new RewriteRuleSubtreeStream(adaptor,"rule bexpr0");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:13: ( ( 'struct' ID '{' bexpr0 ( ',' bexpr0 )* '}' -> ^( 'struct' ID ( bexpr0 )+ ) ) ( DOT qualifiedID -> ^( DOT $structValue qualifiedID ) )? )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:15: ( 'struct' ID '{' bexpr0 ( ',' bexpr0 )* '}' -> ^( 'struct' ID ( bexpr0 )+ ) ) ( DOT qualifiedID -> ^( DOT $structValue qualifiedID ) )?
            {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:15: ( 'struct' ID '{' bexpr0 ( ',' bexpr0 )* '}' -> ^( 'struct' ID ( bexpr0 )+ ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:16: 'struct' ID '{' bexpr0 ( ',' bexpr0 )* '}'
            {
            string_literal115=(Token)match(input,43,FOLLOW_43_in_structValue1178);  
            stream_43.add(string_literal115);


            ID116=(Token)match(input,ID,FOLLOW_ID_in_structValue1180);  
            stream_ID.add(ID116);


            char_literal117=(Token)match(input,46,FOLLOW_46_in_structValue1182);  
            stream_46.add(char_literal117);


            pushFollow(FOLLOW_bexpr0_in_structValue1184);
            bexpr0118=bexpr0();

            state._fsp--;

            stream_bexpr0.add(bexpr0118.getTree());

            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:39: ( ',' bexpr0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==28) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:40: ',' bexpr0
            	    {
            	    char_literal119=(Token)match(input,28,FOLLOW_28_in_structValue1187);  
            	    stream_28.add(char_literal119);


            	    pushFollow(FOLLOW_bexpr0_in_structValue1189);
            	    bexpr0120=bexpr0();

            	    state._fsp--;

            	    stream_bexpr0.add(bexpr0120.getTree());

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            char_literal121=(Token)match(input,48,FOLLOW_48_in_structValue1192);  
            stream_48.add(char_literal121);


            // AST REWRITE
            // elements: bexpr0, ID, 43
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (TypeTree)adaptor.nil();
            // 136:56: -> ^( 'struct' ID ( bexpr0 )+ )
            {
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:136:59: ^( 'struct' ID ( bexpr0 )+ )
                {
                TypeTree root_1 = (TypeTree)adaptor.nil();
                root_1 = (TypeTree)adaptor.becomeRoot(
                stream_43.nextNode()
                , root_1);

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                if ( !(stream_bexpr0.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_bexpr0.hasNext() ) {
                    adaptor.addChild(root_1, stream_bexpr0.nextTree());

                }
                stream_bexpr0.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:137:8: ( DOT qualifiedID -> ^( DOT $structValue qualifiedID ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==DOT) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:137:9: DOT qualifiedID
                    {
                    DOT122=(Token)match(input,DOT,FOLLOW_DOT_in_structValue1214);  
                    stream_DOT.add(DOT122);


                    pushFollow(FOLLOW_qualifiedID_in_structValue1216);
                    qualifiedID123=qualifiedID();

                    state._fsp--;

                    stream_qualifiedID.add(qualifiedID123.getTree());

                    // AST REWRITE
                    // elements: structValue, DOT, qualifiedID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 137:25: -> ^( DOT $structValue qualifiedID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:137:28: ^( DOT $structValue qualifiedID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_DOT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_1, stream_qualifiedID.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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


    public static class qualifiedID_return extends ParserRuleReturnScope {
        TypeTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qualifiedID"
    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:140:1: qualifiedID : ( ID | ID DOT qualifiedID -> ^( DOT ID qualifiedID ) );
    public final JPoclASTParser.qualifiedID_return qualifiedID() throws RecognitionException {
        JPoclASTParser.qualifiedID_return retval = new JPoclASTParser.qualifiedID_return();
        retval.start = input.LT(1);


        TypeTree root_0 = null;

        Token ID124=null;
        Token ID125=null;
        Token DOT126=null;
        JPoclASTParser.qualifiedID_return qualifiedID127 =null;


        TypeTree ID124_tree=null;
        TypeTree ID125_tree=null;
        TypeTree DOT126_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleSubtreeStream stream_qualifiedID=new RewriteRuleSubtreeStream(adaptor,"rule qualifiedID");
        try {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:140:13: ( ID | ID DOT qualifiedID -> ^( DOT ID qualifiedID ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==ID) ) {
                int LA29_1 = input.LA(2);

                if ( (LA29_1==DOT) ) {
                    alt29=2;
                }
                else if ( (LA29_1==EOF||LA29_1==ECHO||LA29_1==ID||LA29_1==NL||(LA29_1 >= 21 && LA29_1 <= 23)||(LA29_1 >= 25 && LA29_1 <= 30)||(LA29_1 >= 32 && LA29_1 <= 48)) ) {
                    alt29=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;

            }
            switch (alt29) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:140:15: ID
                    {
                    root_0 = (TypeTree)adaptor.nil();


                    ID124=(Token)match(input,ID,FOLLOW_ID_in_qualifiedID1250); 
                    ID124_tree = 
                    (TypeTree)adaptor.create(ID124)
                    ;
                    adaptor.addChild(root_0, ID124_tree);


                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:141:9: ID DOT qualifiedID
                    {
                    ID125=(Token)match(input,ID,FOLLOW_ID_in_qualifiedID1260);  
                    stream_ID.add(ID125);


                    DOT126=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedID1262);  
                    stream_DOT.add(DOT126);


                    pushFollow(FOLLOW_qualifiedID_in_qualifiedID1264);
                    qualifiedID127=qualifiedID();

                    state._fsp--;

                    stream_qualifiedID.add(qualifiedID127.getTree());

                    // AST REWRITE
                    // elements: DOT, qualifiedID, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (TypeTree)adaptor.nil();
                    // 141:28: -> ^( DOT ID qualifiedID )
                    {
                        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:141:31: ^( DOT ID qualifiedID )
                        {
                        TypeTree root_1 = (TypeTree)adaptor.nil();
                        root_1 = (TypeTree)adaptor.becomeRoot(
                        stream_DOT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_qualifiedID.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (TypeTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

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

    // Delegated rules


    protected DFA22 dfa22 = new DFA22(this);
    static final String DFA22_eotS =
        "\4\uffff";
    static final String DFA22_eofS =
        "\4\uffff";
    static final String DFA22_minS =
        "\2\16\2\uffff";
    static final String DFA22_maxS =
        "\2\60\2\uffff";
    static final String DFA22_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA22_specialS =
        "\4\uffff}>";
    static final String[] DFA22_transitionS = {
            "\1\3\6\uffff\1\1\2\3\1\uffff\6\3\1\uffff\2\3\1\uffff\3\3\1\2"+
            "\10\uffff\2\3",
            "\1\3\6\uffff\1\1\2\3\1\uffff\6\3\1\uffff\2\3\1\uffff\3\3\1"+
            "\2\10\uffff\2\3",
            "",
            ""
    };

    static final short[] DFA22_eot = DFA.unpackEncodedString(DFA22_eotS);
    static final short[] DFA22_eof = DFA.unpackEncodedString(DFA22_eofS);
    static final char[] DFA22_min = DFA.unpackEncodedStringToUnsignedChars(DFA22_minS);
    static final char[] DFA22_max = DFA.unpackEncodedStringToUnsignedChars(DFA22_maxS);
    static final short[] DFA22_accept = DFA.unpackEncodedString(DFA22_acceptS);
    static final short[] DFA22_special = DFA.unpackEncodedString(DFA22_specialS);
    static final short[][] DFA22_transition;

    static {
        int numStates = DFA22_transitionS.length;
        DFA22_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA22_transition[i] = DFA.unpackEncodedString(DFA22_transitionS[i]);
        }
    }

    class DFA22 extends DFA {

        public DFA22(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 22;
            this.eot = DFA22_eot;
            this.eof = DFA22_eof;
            this.min = DFA22_min;
            this.max = DFA22_max;
            this.accept = DFA22_accept;
            this.special = DFA22_special;
            this.transition = DFA22_transition;
        }
        public String getDescription() {
            return "117:26: ( ( '!' -> ^( '!' $expr4) )* '^' expr2b -> ^( '^' $expr4 expr2b ) )?";
        }
    }
 

    public static final BitSet FOLLOW_stmt_in_calc84 = new BitSet(new long[]{0x00007F8000005202L});
    public static final BitSet FOLLOW_NL_in_stmt107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ECHO_in_stmt116 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_instr_in_stmt118 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_NL_in_stmt120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedID_in_stmt135 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_stmt139 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_stmt141 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_NL_in_stmt143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_stmt161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_stmt171 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_stmt174 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_stmt177 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_stmt179 = new BitSet(new long[]{0x00007F8000005200L});
    public static final BitSet FOLLOW_stmt_in_stmt182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_stmt189 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_stmt192 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_stmt195 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_stmt197 = new BitSet(new long[]{0x00007F8000005200L});
    public static final BitSet FOLLOW_stmt_in_stmt200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_stmt207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structDecl_in_stmt214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDecl_in_stmt221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStmt_in_stmt228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_block242 = new BitSet(new long[]{0x00007F8000005200L});
    public static final BitSet FOLLOW_stmt_in_block244 = new BitSet(new long[]{0x00017F8000005200L});
    public static final BitSet FOLLOW_48_in_block247 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_NL_in_block249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnFunctionDecl_in_functionDecl288 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_functionDecl290 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_functionDecl292 = new BitSet(new long[]{0x00000A8002000000L});
    public static final BitSet FOLLOW_parametersDecl_in_functionDecl294 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_functionDecl297 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_functionDecl298 = new BitSet(new long[]{0x00017F8000005200L});
    public static final BitSet FOLLOW_stmt_in_functionDecl307 = new BitSet(new long[]{0x00017F8000005200L});
    public static final BitSet FOLLOW_48_in_functionDecl317 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_NL_in_functionDecl318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_returnFunctionDecl365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_returnFunctionDecl379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_returnFunctionDecl393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_returnFunctionDecl407 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_returnFunctionDecl410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_returnStmt436 = new BitSet(new long[]{0x0002080029007040L});
    public static final BitSet FOLLOW_bexpr0_in_returnStmt438 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_NL_in_returnStmt441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameterDecl_in_parametersDecl457 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_parametersDecl460 = new BitSet(new long[]{0x00000A8000000000L});
    public static final BitSet FOLLOW_parameterDecl_in_parametersDecl462 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_41_in_parameterDecl498 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_parameterDecl519 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_parameterDecl540 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl544 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_parameterDecl548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_structDecl576 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structDecl578 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_structDecl580 = new BitSet(new long[]{0x00000A8000000000L});
    public static final BitSet FOLLOW_structMember_in_structDecl581 = new BitSet(new long[]{0x00010A8000000000L});
    public static final BitSet FOLLOW_48_in_structDecl583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_structMember609 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember611 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_structMember613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_structMember632 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember634 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_structMember636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_structMember655 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember659 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structMember663 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_structMember665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bexpr0_in_instr695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bexpr1_in_bexpr0709 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_47_in_bexpr0712 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr1_in_bexpr0715 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_bexpr2_in_bexpr1731 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_bexpr1734 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr2_in_bexpr1737 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_bexpr3_in_bexpr2754 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_bexpr2757 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr3_in_bexpr2760 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_expr0_in_bexpr3775 = new BitSet(new long[]{0x0000003300000002L});
    public static final BitSet FOLLOW_set_in_bexpr3778 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr0_in_bexpr3789 = new BitSet(new long[]{0x0000003300000002L});
    public static final BitSet FOLLOW_expr1_in_expr0806 = new BitSet(new long[]{0x0000000028000002L});
    public static final BitSet FOLLOW_set_in_expr0809 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr1_in_expr0818 = new BitSet(new long[]{0x0000000028000002L});
    public static final BitSet FOLLOW_expr2_in_expr1833 = new BitSet(new long[]{0x0000000044400002L});
    public static final BitSet FOLLOW_set_in_expr1837 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr2_in_expr1849 = new BitSet(new long[]{0x0000000044400002L});
    public static final BitSet FOLLOW_27_in_expr2863 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr2_in_expr2865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_expr2880 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr2_in_expr2882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr3_in_expr2895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_expr2b909 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr2b_in_expr2b911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_expr2b926 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr2b_in_expr2b928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr4_in_expr2b941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr4_in_expr3954 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_expr3956 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_expr5_in_expr4970 = new BitSet(new long[]{0x0000004000200002L});
    public static final BitSet FOLLOW_21_in_expr4979 = new BitSet(new long[]{0x0000004000200000L});
    public static final BitSet FOLLOW_38_in_expr4992 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_expr2b_in_expr4994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_expr51017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_expr51026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_expr51035 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_expr51038 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_expr51041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_expr51050 = new BitSet(new long[]{0x0002080001003040L});
    public static final BitSet FOLLOW_expr5_in_expr51053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_expr51061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_structValue_in_expr51069 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedID_in_expr51077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_functionCall1091 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_functionCall1092 = new BitSet(new long[]{0x000208002B003040L});
    public static final BitSet FOLLOW_parameters_in_functionCall1094 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_functionCall1097 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_DOT_in_functionCall1119 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_qualifiedID_in_functionCall1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bexpr0_in_parameters1150 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_parameters1153 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_parameters1155 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_43_in_structValue1178 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_ID_in_structValue1180 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_structValue1182 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_structValue1184 = new BitSet(new long[]{0x0001000010000000L});
    public static final BitSet FOLLOW_28_in_structValue1187 = new BitSet(new long[]{0x0002080029003040L});
    public static final BitSet FOLLOW_bexpr0_in_structValue1189 = new BitSet(new long[]{0x0001000010000000L});
    public static final BitSet FOLLOW_48_in_structValue1192 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_DOT_in_structValue1214 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_qualifiedID_in_structValue1216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedID1250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_qualifiedID1260 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_DOT_in_qualifiedID1262 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_qualifiedID_in_qualifiedID1264 = new BitSet(new long[]{0x0000000000000002L});

}