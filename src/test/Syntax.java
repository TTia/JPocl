package test;

import java.io.IOException;
import java.util.LinkedList;

import junit.framework.Assert;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.junit.Test;

public class Syntax extends JPoclUnitTesting{

	private void buildAST(LinkedList<String> textLines) throws RecognitionException, IOException{
		String text = mergeTextLines(textLines);
		ANTLRStringStream inputStream = core.getInputStream(text);
		TokenStream tokenStream = core.lexInputStream(inputStream);
		core.buildAST(tokenStream);
	}
	
	@Test(expected=RuntimeException.class)
	public void parseInteger_WithoutEcho() throws RecognitionException, IOException {
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("42");

		buildAST(textLines);
	}
	
	@Test
	public void parseEcho() throws RecognitionException, IOException {
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("echo true && (1 == 2)");
		textLines.add("echo min(1,max(1,2))");

		buildAST(textLines);
	}

	@Test
	public void parseFunctionDec() throws RecognitionException, IOException {
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("void f(){");
		textLines.add("return");
		textLines.add("}");
		
		buildAST(textLines);
	}

	@Test(expected = RuntimeException.class)
	public void functionDeclSemanticPredicates_Fail()
			throws RecognitionException, IOException {
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("void f(){");
		textLines.add("void f2(){");
		textLines.add("}");
		textLines.add("}");
		
		buildAST(textLines);
	}

	@Test(expected = RuntimeException.class)
	public void returnStmtSemanticPredicates_Fail()
			throws RecognitionException, IOException {
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("return");
		
		buildAST(textLines);
	}
	
	@Test
	public void assign_PrimValue() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("a = 12");
		textLines.add("b = false");
		textLines.add("c = a");
		
		buildAST(textLines);
	}

	@Test
	public void assign_StructValue() throws IOException, RecognitionException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("struct progetto{int giorniSpesi; bool refactoring; bool unitTesting;}");
		textLines.add("p = struct progetto{42, true, true}");
		textLines.add("echo p.giorniSpesi");
		
		buildAST(textLines);
	}
	
	@Test
	public void initStructValue_Expr() throws IOException, RecognitionException{
		LinkedList<String> textLines = new LinkedList<>();
		addStructProgetto(textLines);
		addIntFunction(textLines);
		textLines.add("d = struct data{12-12+1, iFunction()*2, 2012}");
		
		buildAST(textLines);
	}
	
	@Test
	public void function_minDecl() throws IOException, RecognitionException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("int min(int a, int b){");
		textLines.add("return -1");
		textLines.add("}");
		
		buildAST(textLines);
	}
	
	@Test
	public void function_withoutSideEffect() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		this.addIntFunction(textLines);
		this.addFunction_returnStructProgetto(textLines);
		this.addVoidFunction(textLines);
		
		textLines.add("iFunction()");
		textLines.add("progettoIPL()");
		textLines.add("vFunction()");
		
		buildAST(textLines);
	}
	
	@Test
	public void qid_StructValue() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("echo struct data{1,2,2012}.giorno");
		
		buildAST(textLines);
	}

	@Test
	public void qid_FunctionCall() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("echo oggi().giorno");
		
		buildAST(textLines);

	}
	
	@Test
	public void qid_ID() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("echo calendario.mioCompleanno.giorno");
		
		buildAST(textLines);
	}
	
	@Test
	public void qid_Parenthesis() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("echo (calendario.mioCompleanno.giorno)");
		
		buildAST(textLines);
	}
	
	@Test
	public void echoBoolParameter() throws RecognitionException, IOException {
		LinkedList<String> textLines = new LinkedList<>();
		textLines.add("echo true && (1 == 2)");
		
		buildAST(textLines);
	}
	
	@Test(expected = RuntimeException.class)
	public void functionCall_withoutClosedParenthesis() throws RecognitionException, IOException{
		LinkedList<String> textLines = new LinkedList<>();
		this.addIntFunction(textLines);
		textLines.add("r = iFunction(");
		
		buildAST(textLines);
	}
}
