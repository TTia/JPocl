package test;

import java.io.IOException;
import java.util.LinkedList;

import junit.framework.Assert;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.junit.Before;
import org.junit.Test;

import type.TypeException;
import ast.TypeTree;


public class TypeChecking extends JPoclUnitTesting{

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
	public void echoIntParameter() throws RecognitionException, IOException {
		textLines.add("echo (--12+10) * 2 -2");

		testTypeChecking();
	}

	@Test
	public void echoBoolParameter() throws RecognitionException, IOException {
		textLines.add("echo true && (1 == 2)");
		
		testTypeChecking();
	}

	@Test
	public void echoStructPrimType() throws RecognitionException, IOException {
		this.addStructProgetto(textLines);
		textLines.add("d = struct data{31,12,2013}");
		textLines.add("echo d.mese");
		
		testTypeChecking();
	}

	@Test(expected=TypeException.class)
	public void echoVoidFunction() throws RecognitionException, IOException {
		addVoidFunction(textLines);
		textLines.add("echo vFunction()");

		testTypeChecking();
	}

	@Test
	public void echoIntegerFunction() throws RecognitionException, IOException {
		addIntFunction(textLines);
		textLines.add("echo iFunction()");

		testTypeChecking();
	}
	
	@Test
	public void echoStructID() throws RecognitionException, IOException {
		this.addStructProgetto(textLines);
		textLines.add("d = struct data{31,12,2013}");
		textLines.add("echo d");
		
		testTypeChecking();
	}
	
	@Test
	public void echoQID_MostInnerField() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		textLines.add("p = struct progetto{999, true, struct data {15,06,2013}}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void structValue_MismatchFieldType() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		textLines.add("p = struct progetto{999, true, struct data {false,06,2013}}");
		
		testTypeChecking();
	}

	@Test(expected=TypeException.class)
	public void structValue_MismatchFieldSize() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		textLines.add("p = struct progetto{999, true, struct data {false}}");
		
		testTypeChecking();
	}
	
	@Test
	public void structValue_WithExpression() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		addIntFunction(textLines);
		textLines.add("d = struct data{12-12+1, iFunction()*2, 2012}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void voidFunctionWithReturn() throws RecognitionException, IOException{
		textLines.add("void wasntItAVoidFunction(){");
		textLines.add("return 42");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test
	public void voidFunctionWithVoidReturn() throws RecognitionException, IOException{
		textLines.add("void voidFunction(){");
		textLines.add("return");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test
	public void functionReturnsStructType() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		textLines.add("struct data oggi(){");
		textLines.add("return struct data {01,01,2013}");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test
	public void functionReturnsStructTypeIndirectly() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		textLines.add("struct data oggi(){");
		textLines.add("a = struct data {01,01,2013}");
		textLines.add("return a");
		textLines.add("}");
		
		testTypeChecking();
	}

	@Test(expected=TypeException.class)
	public void nonVoidFunctionWithoutReturn_Int() throws RecognitionException, IOException{
		textLines.add("int zero(){");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void nonVoidFunctionWithoutReturn_Struct() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		textLines.add("struct data zero(){");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void function_multipleReturnStataments() throws RecognitionException, IOException{
		textLines.add("void vFunction(){");
		textLines.add("return");
		textLines.add("echo 1+2+3+4");
		textLines.add("return");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionDeclDuplicateParameters() throws RecognitionException, IOException{
		textLines.add("void vFunction(int a, bool a){");
		textLines.add("return");
		textLines.add("}");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionWithUndeclaratedParameterType() throws RecognitionException, IOException{
		addSommaDateFunction(textLines);
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionWrongParameterType() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		addSommaDateFunction(textLines);
		textLines.add("c = sommaDate(false, struct data{2,2,2012})");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionWrongParametersSize_Empty() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		addSommaDateFunction(textLines);
		textLines.add("c = sommaDate()");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void functionWrongParametersSize() throws RecognitionException, IOException{
		addStructProgetto(textLines);
		addSommaDateFunction(textLines);
		textLines.add("c = sommaDate(struct data{2,2,2012})");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void variableInitializationWithVoidReturn() throws RecognitionException, IOException{
		addVoidFunction(textLines);
		textLines.add("c = vFunction()");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void variableInitialization_DifferentTypes() throws RecognitionException, IOException{
		textLines.add("a = 40");
		textLines.add("a = false");
		
		testTypeChecking();	
	}
	
	@Test
	public void variableInitialization_SameType() throws RecognitionException, IOException{
		textLines.add("a = 40");
		textLines.add("a = 41");
		textLines.add("a = 41 + 1");
		
		testTypeChecking();	
	}
	
	@Test(expected=TypeException.class)
	public void undeclaratedVariable_Access() throws RecognitionException, IOException{
		textLines.add("echo x.y.z");
		
		testTypeChecking();	
	}
	
	@Test(expected=TypeException.class)
	public void undeclaratedField_Writing() throws RecognitionException, IOException{
		textLines.add("x.y = 40");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void qidAccess_wrongFieldName() throws RecognitionException, IOException{
		addStructData(textLines);
		textLines.add("d = struct data{1,1,2012}");
		textLines.add("echo d.nanosecondo");
		
		testTypeChecking();	
	}
	
	@Test(expected=TypeException.class)
	public void undeclaratedID_Echo() throws RecognitionException, IOException{
		textLines.add("echo x");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void undeclaratedID_Return() throws RecognitionException, IOException{
		textLines.add("int zero(){");
		textLines.add("return zero");
		textLines.add("}");
		
		testTypeChecking();	
	}
	
	@Test(expected=TypeException.class)
	public void undeclaradFunction_FunctionCall() throws RecognitionException, IOException{
		textLines.add("rtn = iFunction()");
		
		testTypeChecking();
	}
	
	@Test
	public void fieldWriting() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		textLines.add("s = struct data{1,1,2013}");
		textLines.add("s.giorno = 2");
		textLines.add("s.mese = 1");
		textLines.add("s.anno = 1492");
		
		testTypeChecking();
	}
	
	@Test
	public void fieldReading() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		textLines.add("s = struct data{1,1,2013}");
		textLines.add("echo s.giorno == 1");
		textLines.add("echo s.mese + 1");
		textLines.add("echo s.giorno < s.anno");
		
		testTypeChecking();
	}
	
	@Test
	public void dotOperator_function() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		this.addFunction_returnStructProgetto(textLines);
		textLines.add("echo progettoIPL().inizio.giorno");
		
		testTypeChecking();
	}

	@Test(expected=TypeException.class)
	public void dotOperator_voidFunction() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		this.addVoidFunction(textLines);
		textLines.add("echo vFunction().x.y.z");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void dotOperator_voidFunction_2() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		this.addVoidFunction(textLines);
		textLines.add("echo vFunction().x");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void dotOperator_primitiveType() throws RecognitionException, IOException{
		textLines.add("a = 42");
		textLines.add("echo a.a2");
		
		testTypeChecking();	
	}
	
	@Test
	public void dotOperator_struct() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		textLines.add("echo struct data{01,06,2013}.giorno");
		
		testTypeChecking();
	}
	
	@Test
	public void dotOperator_nestedStruct() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		this.addFunction_returnStructProgetto(textLines);
		textLines.add("ipl = progettoIPL()");
		textLines.add("echo ipl.inizio.mese");
		
		testTypeChecking();
	}
	
	@Test(expected=TypeException.class)
	public void dotOperator_nestedStructOverflow() throws RecognitionException, IOException{
		this.addStructProgetto(textLines);
		textLines.add("ipl = progettoIPL()");
		textLines.add("echo ipl.inizio.mese.x");
		
		testTypeChecking();
	}
	
	@Test
	public void emptyVoidFunction() throws RecognitionException, IOException{
		textLines.add("void vFunction(){");
		textLines.add("}");
		
		testTypeChecking();
	}
}
