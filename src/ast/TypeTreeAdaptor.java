package ast;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class TypeTreeAdaptor extends CommonTreeAdaptor {
    
    @Override
	public Object create(Token payload) {
	return new TypeTree(payload);
    }

    @Override
	public Object errorNode(TokenStream input,
				Token start,
				Token stop,
				RecognitionException e) {
	return new TypeErrorNode(input,start,stop,e);
    }
}
 