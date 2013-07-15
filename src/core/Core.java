package core;

import gen.JPoclASTLexer;
import gen.JPoclASTParser;
import gen.JPoclGenerator;
import gen.JPoclTypedAST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.stringtemplate.StringTemplateGroup;

import type.FunctionType;
import type.FunctionTypeDec;
import type.PrimType;
import type.StructType;
import type.StructTypeDec;
import ast.TypeTree;
import ast.TypeTreeAdaptor;

public class Core {
	private static final String defaultPackage = "jpocl";
	private static final String defaulApiClassName = "Api";
	
	private LinkedHashMap<String,FunctionType> api;
	private LinkedHashMap<String,StructType> dataTypes;
	private String outputDirectory;
	private boolean isLinux;
	
	public Core() {
		this.initAPI();
		this.initDataTypes();
	}
	
	public ANTLRStringStream getInputStream(File inputFile) throws IOException {
		FileInputStream fis = new FileInputStream(inputFile.getAbsoluteFile());
		ANTLRStringStream inputStream = new ANTLRInputStream(fis);
		fis.close();
		return inputStream;
	}

	public ANTLRStringStream getInputStream(String inputText) {
		return new ANTLRStringStream(inputText + "\n");
	}

	public TokenStream lexInputStream(ANTLRStringStream inputStream) {
		JPoclASTLexer lexer = new JPoclASTLexer(inputStream);
		return new CommonTokenStream(lexer);
	}

	public TypeTree buildAST(TokenStream tokenStream)
			throws RecognitionException, IOException {
		JPoclASTParser parser = new JPoclASTParser(tokenStream);
		TypeTreeAdaptor ttad = new TypeTreeAdaptor();
		parser.setTreeAdaptor(ttad);

		JPoclASTParser.calc_return r1 = parser.calc();
		return (TypeTree) r1.getTree();
	}

	public CommonTreeNodeStream typeCheck(TypeTree ast, TokenStream tokenStream)
			throws RecognitionException {
		CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
		nodes.setTokenStream(tokenStream);

		JPoclTypedAST typeWalker = new JPoclTypedAST(
				nodes);
		JPoclTypedAST.calc_return r = typeWalker.calc(api, dataTypes);
		for(String fid : r._api.keySet()){
			if(!this.api.containsKey(fid))
				this.api.put(fid, r._api.get(fid));
		}
		for(String sid : r._dataTypes.keySet()){
			if(!this.dataTypes.containsKey(sid))
				this.dataTypes.put(sid, r._dataTypes.get(sid));
		}
		
		return new CommonTreeNodeStream(ast);
	}

	public String[] generateByteCode(CommonTreeNodeStream nodes)
			throws RecognitionException, IOException, ShellException,
			InterruptedException {
		JPoclGenerator genWalker = new JPoclGenerator(nodes);

		InputStream stg = getClass().getResourceAsStream("JPoclGenerator.stg");
		BufferedReader input = new BufferedReader(new InputStreamReader(stg));
		StringTemplateGroup templates = new StringTemplateGroup(input);
		input.close();
		genWalker.setTemplateLib(templates);
		JPoclGenerator.calc_return r3 = genWalker.calc(defaultPackage, api, dataTypes);

		File srcDir = splitGeneratedCode(outputDirectory, r3.getTemplate()
				.toString());
		compileBytecode(srcDir);
		return runGeneratedScript(srcDir.getAbsolutePath(),defaultPackage);
	}

	private String[] runGeneratedScript(String outputDirectory, String defaultPackage)
			throws ShellException, IOException, InterruptedException {
		String[] cmd = new String[3];;
		if(isLinux){
			cmd[0] = "/bin/sh";
			cmd[1] = "-c";
		}else{
			cmd[0] = "cmd";
			cmd[1] = "/C";
		}
		cmd[2] = String.format("cd %s && java %s.Api", outputDirectory, defaultPackage);
		
		Process p = Runtime.getRuntime().exec(cmd);
		int result = p.waitFor();

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				p.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(
				p.getErrorStream()));

		String line;
		LinkedList<String> output = new LinkedList<>();
		while ((line = stdInput.readLine()) != null) {
			output.add(line);
		}

		if (result != 0) {
			String error = "";
			while ((line = stdError.readLine()) != null) {
				error += line + "\n";
			}
			throw new ShellException(error);
		}
		return output.toArray(new String[0]);
	}

	private File splitGeneratedCode(String packageFilePath, String jpoclOutput)
			throws IOException {
		File packageDirectory = new File(packageFilePath);

		if (!packageDirectory.exists()) {
			packageDirectory.mkdir();
			packageDirectory.createNewFile();			
		}
		
		String[] splittedSourceCode = jpoclOutput.split("##");
		writeJasminFile(splittedSourceCode[0], packageDirectory, defaulApiClassName);
		for(int i = 1; i<splittedSourceCode.length; i+=2){
			String fileName = splittedSourceCode[i];
			String sourceCode = splittedSourceCode[i+1];
			writeJasminFile(sourceCode, packageDirectory, fileName);
		}

		return packageDirectory;
	}

	private void writeJasminFile(String sourceCode, File packageDirectory, String fileName)
			throws IOException {
		File file = new File(packageDirectory, fileName+".j");
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(sourceCode);
		bw.close();
	}

	private void compileBytecode(File srcDir) {
		String[] jasmineSourceFilesPath = srcDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".j");
			}
		});

		for (int i = 0; i < jasmineSourceFilesPath.length; i++) {
			String[] jArguments = { "-d", srcDir.getAbsolutePath(),
					srcDir.getAbsolutePath() + "/" + jasmineSourceFilesPath[i] };
			jasmin.Main.main(jArguments);
		}
	}

	private void initAPI() {
		this.api = new LinkedHashMap<>();

		FunctionTypeDec minDec = new FunctionTypeDec();
		minDec.addParameter("a", PrimType.INT);
		minDec.addParameter("b", PrimType.INT);
		api.put("min",new FunctionType("min", PrimType.INT, minDec));

		FunctionTypeDec maxDec = new FunctionTypeDec();
		maxDec.addParameter("a", PrimType.INT);
		maxDec.addParameter("b", PrimType.INT);
		api.put("max",new FunctionType("max", PrimType.INT, maxDec));

		FunctionTypeDec fact = new FunctionTypeDec();
		fact.addParameter("n", PrimType.INT);
		api.put("fact",new FunctionType("fact", PrimType.INT, fact));

		api.put("runScript",new FunctionType("runScript", PrimType.VOID,
				new FunctionTypeDec()));
	}

	private void initDataTypes() {
		this.dataTypes = new LinkedHashMap<>();

		dataTypes.put("Api",new StructType("Api", new StructTypeDec()));
	}

	private static Core core;
	public static void setCore(String outputDirectory, boolean isLinux){
		core = new Core();
		core.outputDirectory = outputDirectory;
		core.isLinux = isLinux;
	}
	public static Core obtainCore() {
		return core;
	}
}
