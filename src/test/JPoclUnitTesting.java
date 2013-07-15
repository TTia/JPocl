package test;

import java.util.LinkedList;

import org.junit.Before;

import core.Core;

public class JPoclUnitTesting {

	protected Core core;

	@Before
	public void beforeEachTest() {
		Core.setCore("output", true);
		core = Core.obtainCore();
	}

	protected void addStructData(LinkedList<String> textLines) {
		textLines.add("struct data{int giorno; int mese; int anno;}");
	}
	
	protected void addStructProgetto(LinkedList<String> textLines) {
		this.addStructData(textLines);
		textLines.add("struct progetto{int giorniSpesi; bool agile; struct data inizio;}");
	}
	
	protected void addVoidFunction(LinkedList<String> textLines){
		textLines.add("void vFunction(){");
		textLines.add("return");
		textLines.add("}");
	}

	protected void addIntFunction(LinkedList<String> textLines){
		textLines.add("int iFunction(){");
		textLines.add("return 0");
		textLines.add("}");
	}
	
	protected void addSommaDateFunction(LinkedList<String> textLines){
		textLines.add("struct data sommaDate(struct data a, struct data b){");
		textLines.add("a.giorno = a.giorno + b.giorno");
		textLines.add("a.mese = a.mese + b.mese");
		textLines.add("a.anno = a.anno + b.anno");
		textLines.add("return a");
		textLines.add("}");
	}
	
	protected void addFunction_returnStructProgetto(LinkedList<String> textLines){
		textLines.add("struct progetto progettoIPL(){");
		textLines.add("return struct progetto{999, true, struct data{01,06,2013}}");
		textLines.add("}");
	}
	
	protected String mergeTextLines(LinkedList<String> textLines) {
		String result = "";
		for(String line : textLines){
			result += line;
			result += "\n";
		}
		return result;
	}

}
