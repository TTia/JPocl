package test;

import java.io.IOException;
import java.util.LinkedList;

import junit.framework.Assert;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.junit.Before;
import org.junit.Test;

import core.ShellException;

import ast.TypeTree;

public class LocalsLimit extends JPoclUnitTesting {

	LinkedList<String> textLines;

	protected void testCodeGeneration() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		String text = mergeTextLines(textLines);
		ANTLRStringStream inputStream = core.getInputStream(text);
		TokenStream tokenStream = core.lexInputStream(inputStream);
		TypeTree ast = core.buildAST(tokenStream);
		CommonTreeNodeStream nodes = core.typeCheck(ast, tokenStream);
		core.generateByteCode(nodes);
	}

	@Before
	public void initTextLines() {
		this.textLines = new LinkedList<>();
	}

	@Test
	public void calculateLocals_topLevel_noBlock() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		textLines.add("a = 0");
		textLines.add("b = 0");
		textLines.add("c = 0");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_singleBlock() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		textLines.add("{");
		textLines.add("a = 0");
		textLines.add("b = 0");
		textLines.add("c = 0");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_nested_singleBlock()
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		textLines.add("_a = 1");
		textLines.add("{");
		textLines.add("a = 0");
		textLines.add("b = 0");
		textLines.add("c = 0");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_nested_multipleBlocks()
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		textLines.add("_a = 1");
		textLines.add("{");
		textLines.add("a = 0");
		textLines.add("b = 0");
		textLines.add("c = 0");
		textLines.add("{");
		textLines.add("d = 0");
		textLines.add("}");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_functionParameter()
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		textLines.add("a = 1");
		textLines.add("void vFunction(int a){");
		textLines.add("return");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_functionParametersAndVariables()
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		textLines.add("a = 1");
		textLines.add("void vFunction(int a, int b){");
		textLines.add("c = 1");
		textLines.add("c = 2");
		textLines.add("d = 2");
		textLines.add("d = 2");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_functionParametersAndVariables_2()
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		textLines.add("a = 1");
		textLines.add("void vFunction(int a, int b){");
		textLines.add("c = 1");
		textLines.add("c = 2");
		textLines.add("d = 2");
		textLines.add("echo 42");
		textLines.add("echo 24");
		textLines.add("return");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}

	@Test
	public void calculateLocals_WhileBlock() throws RecognitionException,
			IOException, ShellException, InterruptedException {
		textLines.add("i = true");
		textLines.add("while(i){");
		textLines.add("echo i");
		textLines.add("i = false");
		textLines.add("}");

		testCodeGeneration();
		Assert.assertTrue(true); // A mano!
	}
}
