package test;

import java.io.IOException;
import java.util.LinkedList;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.junit.Before;
import org.junit.Test;

import type.TypeException;
import ast.TypeTree;

public class Scoping extends JPoclUnitTesting {
	
	LinkedList<String> textLines;
	
	protected void testTypeChecking() throws RecognitionException, IOException{
		String text = mergeTextLines(textLines);
		ANTLRStringStream inputStream = core.getInputStream(text);
		TokenStream tokenStream = core.lexInputStream(inputStream);
		TypeTree ast = core.buildAST(tokenStream);
		core.typeCheck(ast, tokenStream);
	}
	
	@Before
	public void initTextLines(){
		this.textLines = new LinkedList<>();
	}
	
	@Test
	public void parameterHideOuterVariable() throws RecognitionException, IOException{
		textLines.add("a = 0");
		textLines.add("void f(bool a){");
		textLines.add("echo a && false");
		textLines.add("}");
		
		testTypeChecking();
	}

	@Test
	public void parameterEcho() throws RecognitionException, IOException{
		textLines.add("void f(bool a){");
		textLines.add("echo a && false");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void parameterVisibility() throws RecognitionException, IOException{
		textLines.add("void f(bool a){");
		textLines.add("}");
		textLines.add("echo a");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void parameterVisibility_2() throws RecognitionException, IOException{
		textLines.add("a = false");
		textLines.add("void f(){");
		textLines.add("echo a");		
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test
	public void structVisibility() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		textLines.add("void f(){");
		textLines.add("echo struct data{1,2,2013}.giorno");		
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionMultipleDeclaration_sameScope() throws RecognitionException, IOException{
		addIntFunction(textLines);
		addIntFunction(textLines);
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionMultipleDeclaration_innerScope() throws RecognitionException, IOException{
		textLines.add("{");
		addIntFunction(textLines);
		textLines.add("{");
		addIntFunction(textLines);	
		textLines.add("}");
		textLines.add("}");
		
		testTypeChecking();		
	}
	
	@Test(expected=TypeException.class)
	public void functionMultipleDeclaration_separatedScope() throws RecognitionException, IOException{
		textLines.add("{");
		this.addIntFunction(textLines);
		textLines.add("}");
		textLines.add("{");
		this.addIntFunction(textLines);
		textLines.add("}");
		
		testTypeChecking();		
	}
	
	@Test(expected=TypeException.class)
	public void structMultipleDeclaration_sameScope() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		addStructProgetto(textLines);
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void structMultipleDeclaration_innerScope() throws RecognitionException, IOException{
		textLines.add("{");
		this.addStructProgetto(textLines);
		textLines.add("{");
		this.addStructProgetto(textLines);		
		textLines.add("}");
		textLines.add("}");
		
		testTypeChecking();		
	}
	
	@Test(expected=TypeException.class)
	public void structMultipleDeclaration_separatedScope() throws RecognitionException, IOException{
		textLines.add("{");
		this.addStructProgetto(textLines);
		textLines.add("}");
		textLines.add("{");
		this.addStructProgetto(textLines);
		textLines.add("}");
		
		testTypeChecking();		
	}
	
	@Test(expected=TypeException.class)
	public void variableInitialization_DifferentTypes() throws RecognitionException, IOException{
		textLines.add("a = 0");
		textLines.add("{");
		textLines.add("a = false");
		textLines.add("}");
		
		testTypeChecking();
	}

	@Test
	public void variableInitialization_SameTypes() throws RecognitionException, IOException{
		textLines.add("a = 0");
		textLines.add("{");
		textLines.add("a = 1");
		textLines.add("}");
		
		testTypeChecking();
	}

	@Test
	public void functionPlainRecursion() throws RecognitionException, IOException{
		textLines.add("int weirdCount(int c){");
		textLines.add("if(c >= 10) return c");
		textLines.add("return weirdCount(c+1)");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionIndirectRecursion() throws RecognitionException, IOException{
		textLines.add("int weirdCount_1(int c){");
		textLines.add("if(c >= 10) return c");
		textLines.add("return weirdCount_2(c+1)");
		textLines.add("}");
		
		textLines.add("int weirdCount_2(int c){");
		textLines.add("if(c >= 10)");
		textLines.add("return c");
		textLines.add("return weirdCount_1(c+1)");
		textLines.add("}");
	
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionMinUserDeclaration_Overloading() throws RecognitionException, IOException{
		textLines.add("int min(int a, int b, int c){");
		textLines.add("return -1");
		textLines.add("}");
		
		testTypeChecking();
	}

	@Test(expected=TypeException.class)
	public void functionMaxUserDeclaration_Identical() throws RecognitionException, IOException{
		textLines.add("int max(int a, int b){");
		textLines.add("return -1");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test
	public void recursiveStructDeclaration() throws RecognitionException, IOException{
		textLines.add("struct iList {int value; struct iList next;}");
		
		testTypeChecking();
	}
}
