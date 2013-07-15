package ast;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import type.Type;

public class TypeTree extends CommonTree {

	private Type staticType;

	public Type getStaticType() {
		return staticType;
	}

	public void setStaticType(Type type) {
		this.staticType = type;
	}

	public TypeTree() {
	}

	public TypeTree(CommonTree node) {
		super(node);
	}

	public TypeTree(Token t) {
		super(t);
	}

	@Override
	public Tree dupNode() {
		return new TypeTree(this);
	}

	public int getIndex() {
		return getToken().getTokenIndex();
	}
}