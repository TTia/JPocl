package test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import junit.framework.Assert;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.ShellException;

import ast.TypeTree;

public class Generation extends JPoclUnitTesting {

	LinkedList<String> textLines;

	protected String[] testCodeGeneration() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		String text = mergeTextLines(textLines);
		ANTLRStringStream inputStream = core.getInputStream(text);
		TokenStream tokenStream = core.lexInputStream(inputStream);
		TypeTree ast = core.buildAST(tokenStream);
		CommonTreeNodeStream nodes = core.typeCheck(ast, tokenStream);

		return core.generateByteCode(nodes);
	}

	@Before
	public void initTextLines() {
		this.textLines = new LinkedList<>();
	}

	@After
	public void waitForCompletion() throws InterruptedException {
		Thread.sleep(100);
	}

	@Test
	public void echo_Int() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 1");
		textLines.add("echo 2");
		textLines.add("echo 20");

		String[] expectedOutput = { "1", "2", "20" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Boolean() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo true");
		textLines.add("echo false");

		String[] expectedOutput = { "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Add() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 1+2");
		textLines.add("echo 1+11");

		String[] expectedOutput = { "3", "12" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Sub() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 1-2");
		textLines.add("echo 1-11");

		String[] expectedOutput = { "-1", "-10" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Mul() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 1100*2");
		textLines.add("echo 1*11");

		String[] expectedOutput = { "2200", "11" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Div() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 1100/2");
		textLines.add("echo 1/11");

		String[] expectedOutput = { "550", "0" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Fact() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 0!");
		textLines.add("echo 3!");

		String[] expectedOutput = { "1", "6" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Not() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo ~false");
		textLines.add("echo ~true");

		String[] expectedOutput = { "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_UnaryPlus() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo +++42");

		String[] expectedOutput = { "42" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_UnaryMinus() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo --42");
		textLines.add("echo --+-42");

		String[] expectedOutput = { "42", "-42" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Remainder() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 7%7");
		textLines.add("echo 49%5");
		textLines.add("echo (12+34-1)%5");

		String[] expectedOutput = { "0", "4", "0" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Pow() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 2^3");
		textLines.add("echo 2^-5");
		textLines.add("echo 2^0");

		String[] expectedOutput = { "8", "0", "1" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Less() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 7<7");
		textLines.add("echo (12*12*12)<12+12+12");
		textLines.add("echo 24/2<24/(--1)");

		String[] expectedOutput = { "false", "false", "true" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_LessEqual() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 7<=7");
		textLines.add("echo (12*12*12)<=12+12+12");
		textLines.add("echo 24/2<=24/(--1)");

		String[] expectedOutput = { "true", "false", "true" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Greater() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 7>7");
		textLines.add("echo (12*12*12)>12+12+12");
		textLines.add("echo 24/2>24/(--1)");

		String[] expectedOutput = { "false", "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_GreaterEqual() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 7>=7");
		textLines.add("echo (12*12*12)>=12+12+12");
		textLines.add("echo 24/2>=24/(--1)");

		String[] expectedOutput = { "true", "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_And() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo true && false");
		textLines.add("echo false && true");
		textLines.add("echo true && true");
		textLines.add("echo false && false");

		String[] expectedOutput = { "false", "false", "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_Or() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo true || false");
		textLines.add("echo false || true");
		textLines.add("echo true || true");
		textLines.add("echo false || false");

		String[] expectedOutput = { "true", "true", "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_LoadID() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("a = 0");
		textLines.add("b = 3 * 2 + 1");

		String[] expectedOutput = {};
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_StoreID() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("a = 0");
		textLines.add("b = a + 1");
		textLines.add("echo b");

		String[] expectedOutput = { "1" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_If() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("if(true) echo 1");
		textLines.add("if(false) echo 2");
		textLines.add("if(~false) echo 3");

		String[] expectedOutput = { "1", "3" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_While() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("i = true");
		textLines.add("while(i){");
		textLines.add("echo i");
		textLines.add("i = false");
		textLines.add("}");

		String[] expectedOutput = { "true" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_While_1() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("i = 0");
		textLines.add("while(i<5){");
		textLines.add("echo i");
		textLines.add("i = i+1");
		textLines.add("}");

		String[] expectedOutput = { "0", "1", "2", "3", "4" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_WhileAndIf() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("i = 0");
		textLines.add("while(i<5){");
		textLines.add("if(i < 3) echo i");
		textLines.add("i = i+1");
		textLines.add("}");
		textLines.add("echo i");

		String[] expectedOutput = { "0", "1", "2", "5" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_functionDeclaration() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		this.addIntFunction(textLines);
		textLines.add("iFunction()");

		String[] expectedOutput = {};
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_functionDeclaration_1() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		this.addIntFunction(textLines);
		textLines.add("echo iFunction() + 1");

		String[] expectedOutput = { "1" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_MinMax() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo min(42,-42+42)");
		textLines.add("echo max(24,-42+42)");
		textLines.add("max(24,24)");

		String[] expectedOutput = { "0", "24" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void structDecl() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		addStructProgetto(textLines);
		String[] expectedOutput = {};
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_StructField_Get() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		addStructData(textLines);
		textLines.add("d = struct data{1,2,2013}");
		textLines.add("echo d.giorno");
		textLines.add("echo d.mese");
		textLines.add("echo d.anno");

		String[] expectedOutput = { "1", "2", "2013" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_StructField_GetPut() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		addStructData(textLines);
		textLines.add("d = struct data{1,2,2013}");
		textLines.add("d2 = struct data{1,2,1000}");
		textLines.add("d.anno = 1000");
		textLines.add("d.mese = d2.giorno");
		textLines.add("echo d.anno");
		textLines.add("echo d.mese");

		String[] expectedOutput = { "1000", "1" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_StructField_GetPut2() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		addStructData(textLines);
		textLines.add("d = struct data{1,2,3}");
		textLines.add("echo d.mese");
		textLines.add("d2 = struct data{3,4,5}");
		textLines.add("d = d2");
		textLines.add("d.mese = 12");
		textLines.add("giorno = d.giorno");
		textLines.add("echo d.mese");
		textLines.add("echo d2.mese");
		textLines.add("echo giorno");

		String[] expectedOutput = { "2", "12", "12", "3" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_StructField_GetFunctionReturn()
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		addStructProgetto(textLines);
		textLines.add("echo struct data{1,2,3}");
		textLines.add("echo struct data{1,2,3}.giorno");
		textLines.add("echo struct progetto{999, true, struct data{1,2,3}}");
		textLines.add("echo struct progetto{999, true, struct data{1,2,3}}.inizio.anno");

		String[] expectedOutput = { "data[1,2,3]", "1",
				"progetto[999,true,data[1,2,3]]", "3" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_ToString() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("echo 1 == 1");
		textLines.add("echo false == true");

		String[] expectedOutput = { "true", "false" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_ToString_2() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		addStructData(textLines);
		textLines.add("d = struct data{1,2,3}");
		textLines.add("d2 = struct data{1,2,4}");
		textLines.add("echo d == d2");
		textLines.add("d2.anno = 3");
		textLines.add("echo d == d2");
		textLines.add("echo d == d");

		String[] expectedOutput = { "false", "true", "true" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void echo_ToString_3() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		addStructProgetto(textLines);
		textLines.add("d = struct data{1,2,3}");
		textLines.add("d2 = struct data{1,2,4}");
		textLines.add("p =  struct progetto{999, true, d}");
		textLines.add("p2 =  struct progetto{999, true, d2}");
		textLines.add("echo d == d2");
		textLines.add("echo p == p2");
		textLines.add("p2.inizio = struct data{1,2,3}");
		textLines.add("echo p == p2");

		String[] expectedOutput = { "false", "false", "true" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}

	@Test
	public void assign_qid() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("struct data{int giorno; int mese; int anno;}");
		textLines.add("d = struct data{4,06,2013}");
		textLines.add("echo d");
		
		textLines.add("struct data returnData(struct data d){");
		textLines.add("return d");
		textLines.add("}");
		textLines.add("d.mese = 07");
		
		textLines.add("echo returnData(d).mese");
		
		String[] expectedOutput = { "data[4,6,2013]", "7" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}
	
	@Test
	public void functionDecl_Void() throws RecognitionException, IOException,
			ShellException, InterruptedException {
		textLines.add("void function(int N){");
		textLines.add("i = 0");
		textLines.add("while(i < N){");
		textLines.add("echo i");
		textLines.add("i = i+1");
		textLines.add("}");
		textLines.add("return");
		textLines.add("}");
		
		textLines.add("function(5)");
		
		String[] expectedOutput = { "0", "1", "2", "3", "4" };
		String[] output = testCodeGeneration();
		Assert.assertTrue(Arrays.equals(expectedOutput, output));
	}
}
