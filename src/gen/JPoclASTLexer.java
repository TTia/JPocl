// $ANTLR 3.4 C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g 2013-07-15 11:45:44

package gen;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class JPoclASTLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public JPoclASTLexer() {} 
    public JPoclASTLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public JPoclASTLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g"; }

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:6:7: ( '!' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:6:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:7:7: ( '%' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:7:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:8:7: ( '&&' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:8:9: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:9:7: ( '(' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:9:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:10:7: ( ')' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:10:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:11:7: ( '*' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:11:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:12:7: ( '+' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:12:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:13:7: ( ',' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:13:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:14:7: ( '-' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:14:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:15:7: ( '/' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:15:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:16:7: ( ';' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:16:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:17:7: ( '<' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:17:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:18:7: ( '<=' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:18:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:19:7: ( '=' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:19:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:20:7: ( '==' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:20:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:21:7: ( '>' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:21:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:22:7: ( '>=' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:22:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:23:7: ( '^' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:23:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:24:7: ( 'bool' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:24:9: 'bool'
            {
            match("bool"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:25:7: ( 'if' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:25:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:26:7: ( 'int' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:26:9: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:27:7: ( 'return' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:27:9: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:28:7: ( 'struct' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:28:9: 'struct'
            {
            match("struct"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:29:7: ( 'void' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:29:9: 'void'
            {
            match("void"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:30:7: ( 'while' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:30:9: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:31:7: ( '{' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:31:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:32:7: ( '||' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:32:9: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:33:7: ( '}' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:33:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:34:7: ( '~' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:34:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:144:9: ( 'false' | 'true' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='f') ) {
                alt1=1;
            }
            else if ( (LA1_0=='t') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:144:11: 'false'
                    {
                    match("false"); 



                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:144:21: 'true'
                    {
                    match("true"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "ECHO"
    public final void mECHO() throws RecognitionException {
        try {
            int _type = ECHO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:146:6: ( 'echo' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:146:8: 'echo'
            {
            match("echo"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ECHO"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:148:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:148:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:148:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:150:5: ( ( '0' .. '9' )+ )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:150:7: ( '0' .. '9' )+
            {
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:150:7: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:153:5: ( ( ' ' | '\\t' ) )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:153:9: ( ' ' | '\\t' )
            {
            if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            skip();

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "NL"
    public final void mNL() throws RecognitionException {
        try {
            int _type = NL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:158:5: ( ( '\\r' )? '\\n' | '\\r' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\r') ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1=='\n') ) {
                    alt5=1;
                }
                else {
                    alt5=2;
                }
            }
            else if ( (LA5_0=='\n') ) {
                alt5=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:158:7: ( '\\r' )? '\\n'
                    {
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:158:7: ( '\\r' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='\r') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:158:7: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }


                    match('\n'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:158:20: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NL"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:160:5: ( '.' )
            // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:160:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:8: ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | BOOLEAN | ECHO | ID | INT | WS | NL | DOT )
        int alt6=36;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:10: T__21
                {
                mT__21(); 


                }
                break;
            case 2 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:16: T__22
                {
                mT__22(); 


                }
                break;
            case 3 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:22: T__23
                {
                mT__23(); 


                }
                break;
            case 4 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:28: T__24
                {
                mT__24(); 


                }
                break;
            case 5 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:34: T__25
                {
                mT__25(); 


                }
                break;
            case 6 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:40: T__26
                {
                mT__26(); 


                }
                break;
            case 7 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:46: T__27
                {
                mT__27(); 


                }
                break;
            case 8 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:52: T__28
                {
                mT__28(); 


                }
                break;
            case 9 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:58: T__29
                {
                mT__29(); 


                }
                break;
            case 10 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:64: T__30
                {
                mT__30(); 


                }
                break;
            case 11 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:70: T__31
                {
                mT__31(); 


                }
                break;
            case 12 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:76: T__32
                {
                mT__32(); 


                }
                break;
            case 13 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:82: T__33
                {
                mT__33(); 


                }
                break;
            case 14 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:88: T__34
                {
                mT__34(); 


                }
                break;
            case 15 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:94: T__35
                {
                mT__35(); 


                }
                break;
            case 16 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:100: T__36
                {
                mT__36(); 


                }
                break;
            case 17 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:106: T__37
                {
                mT__37(); 


                }
                break;
            case 18 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:112: T__38
                {
                mT__38(); 


                }
                break;
            case 19 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:118: T__39
                {
                mT__39(); 


                }
                break;
            case 20 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:124: T__40
                {
                mT__40(); 


                }
                break;
            case 21 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:130: T__41
                {
                mT__41(); 


                }
                break;
            case 22 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:136: T__42
                {
                mT__42(); 


                }
                break;
            case 23 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:142: T__43
                {
                mT__43(); 


                }
                break;
            case 24 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:148: T__44
                {
                mT__44(); 


                }
                break;
            case 25 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:154: T__45
                {
                mT__45(); 


                }
                break;
            case 26 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:160: T__46
                {
                mT__46(); 


                }
                break;
            case 27 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:166: T__47
                {
                mT__47(); 


                }
                break;
            case 28 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:172: T__48
                {
                mT__48(); 


                }
                break;
            case 29 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:178: T__49
                {
                mT__49(); 


                }
                break;
            case 30 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:184: BOOLEAN
                {
                mBOOLEAN(); 


                }
                break;
            case 31 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:192: ECHO
                {
                mECHO(); 


                }
                break;
            case 32 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:197: ID
                {
                mID(); 


                }
                break;
            case 33 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:200: INT
                {
                mINT(); 


                }
                break;
            case 34 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:204: WS
                {
                mWS(); 


                }
                break;
            case 35 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:207: NL
                {
                mNL(); 


                }
                break;
            case 36 :
                // C:\\Users\\pc\\Desktop\\IPL\\ew\\JPocl\\JPoclAST.g:1:210: DOT
                {
                mDOT(); 


                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\14\uffff\1\43\1\45\1\47\1\uffff\6\35\4\uffff\3\35\13\uffff\1\35"+
        "\1\63\11\35\1\uffff\1\75\7\35\1\105\1\uffff\2\35\1\110\2\35\1\113"+
        "\1\114\1\uffff\2\35\1\uffff\1\117\1\113\2\uffff\1\120\1\121\3\uffff";
    static final String DFA6_eofS =
        "\122\uffff";
    static final String DFA6_minS =
        "\1\11\13\uffff\3\75\1\uffff\1\157\1\146\1\145\1\164\1\157\1\150"+
        "\4\uffff\1\141\1\162\1\143\13\uffff\1\157\1\60\2\164\1\162\2\151"+
        "\1\154\1\165\1\150\1\154\1\uffff\1\60\2\165\1\144\1\154\1\163\1"+
        "\145\1\157\1\60\1\uffff\1\162\1\143\1\60\2\145\2\60\1\uffff\1\156"+
        "\1\164\1\uffff\2\60\2\uffff\2\60\3\uffff";
    static final String DFA6_maxS =
        "\1\176\13\uffff\3\75\1\uffff\1\157\1\156\1\145\1\164\1\157\1\150"+
        "\4\uffff\1\141\1\162\1\143\13\uffff\1\157\1\172\2\164\1\162\2\151"+
        "\1\154\1\165\1\150\1\154\1\uffff\1\172\2\165\1\144\1\154\1\163\1"+
        "\145\1\157\1\172\1\uffff\1\162\1\143\1\172\2\145\2\172\1\uffff\1"+
        "\156\1\164\1\uffff\2\172\2\uffff\2\172\3\uffff";
    static final String DFA6_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\3\uffff"+
        "\1\22\6\uffff\1\32\1\33\1\34\1\35\3\uffff\1\40\1\41\1\42\1\43\1"+
        "\44\1\15\1\14\1\17\1\16\1\21\1\20\13\uffff\1\24\11\uffff\1\25\7"+
        "\uffff\1\23\2\uffff\1\30\2\uffff\1\36\1\37\2\uffff\1\31\1\26\1\27";
    static final String DFA6_specialS =
        "\122\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\37\1\40\2\uffff\1\40\22\uffff\1\37\1\1\3\uffff\1\2\1\3\1"+
            "\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\41\1\12\12\36\1\uffff\1\13"+
            "\1\14\1\15\1\16\2\uffff\32\35\3\uffff\1\17\1\35\1\uffff\1\35"+
            "\1\20\2\35\1\34\1\32\2\35\1\21\10\35\1\22\1\23\1\33\1\35\1\24"+
            "\1\25\3\35\1\26\1\27\1\30\1\31",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\42",
            "\1\44",
            "\1\46",
            "",
            "\1\50",
            "\1\51\7\uffff\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "",
            "",
            "",
            "",
            "\1\57",
            "\1\60",
            "\1\61",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\62",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "",
            "\1\106",
            "\1\107",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "\1\111",
            "\1\112",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "",
            "\1\115",
            "\1\116",
            "",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "",
            "",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | BOOLEAN | ECHO | ID | INT | WS | NL | DOT );";
        }
    }
 

}