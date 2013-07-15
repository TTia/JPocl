package ast;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

public class TypeErrorNode extends TypeTree {
    private CommonErrorNode cen;

    public TypeErrorNode(TokenStream input, Token start, Token stop,RecognitionException trappedException) {
	cen = new CommonErrorNode(input,start,stop,trappedException);
    } 
    @Override
	public String	getText(){return cen.getText();} 
    @Override
	public int	getType(){return cen.getType();}
    @Override
	public boolean	isNil(){return cen.isNil();}
    @Override
	public String	toString(){return cen.toString();}
}
