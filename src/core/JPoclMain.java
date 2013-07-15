package core;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import ast.TypeTree;

public class JPoclMain {

	private String inputFileName, outputDst = "output";
	private boolean isLinux = true;

	public static void main(String[] args) {
		try {
			new JPoclMain().start(args);
			System.exit(0);
		} catch (IOException | RecognitionException | ShellException | RuntimeException e) {
			System.err.println(e.getMessage());
		} catch (InterruptedException e) {
			System.err.println("Unexpected error: "+e.getMessage());
		}
		System.exit(1);
	}

	private void start(String[] args) throws IOException, RecognitionException,
			ShellException, InterruptedException {
		checkInputArguments(args);

		Core core = Core.obtainCore();
		ANTLRStringStream inputStream = core.getInputStream(new File(inputFileName));
		TokenStream tokenStream = core.lexInputStream(inputStream);
		TypeTree ast = core.buildAST(tokenStream);
		CommonTreeNodeStream nodes = core.typeCheck(ast, tokenStream);

		String[] output = core.generateByteCode(nodes);
		printOutput(output);
	}

	private void checkInputArguments(String[] args) {
		int i = 0;
		while (i < args.length) {
			switch (args[i]) {
			case "-i":
				handleInputFile(args, i);
				i+=2;
				break;
			case "-o":
				handleOutputDirectory(args, i);
				i+=2;
				break;
			case "-os":
				handleOS(args, i);
				i+=2;				
				break;
			default:
				printFormatHelp();
				System.exit(2);
			}
		}
		if(inputFileName == null){
			printFormatHelp();
			System.exit(2);
		}
		Core.setCore(outputDst, isLinux);
	}

	private void handleOS(String[] args, int i) {
		if(!args[i+1].equals("W") && !args[i+1].equals("L")){
			System.err.println("JPocl is compatible with Windows(W) or Linux(L)");
			System.exit(2);
		}
		isLinux = args[i+1].equals("W") ? false : true;
	}
	
	private void handleOutputDirectory(String[] args, int i) {
		File outputDir = new File(args[i+1]);
		if(outputDir.exists() && !outputDir.isDirectory()){
			System.err.println(String.format("%s is not a directory", args[i+1]));
			System.exit(2);
		}
		outputDst = args[i+1];
	}

	private void handleInputFile(String[] args, int i) {
		File inputFile = new File(args[i+1]);
		if(!inputFile.exists() || !inputFile.canRead()){
			System.err.println(String.format("%s doesn't exists or is not readble", args[i+1]));
			System.exit(2);			
		}
		inputFileName = args[i+1];
	}

	private void printFormatHelp() {
		System.err.println("JPocl usage: jpocl.jar -i InputFile [-o OutputDirectory] [-os OS]");
		System.err.println("Default output directory is ./output");
		System.err.println("Default operative system is Linux(L)");
	}

	private void printOutput(String[] output) {
		System.out.println(String.format("Executed: %s",inputFileName));
		System.out.println("\n------");
		for(int i = 0; i<output.length; i++){
			System.out.println(output[i]);
		}
		System.out.println("------\n");
	}
}
