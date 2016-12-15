package rprogn.interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import rprogn.compiler.Compiler;
import rprogn.compiler.concept.Concept;
import rprogn.variable.Var;

public class Interpreter {
	public Stack<Var> reg;
	public Stack<Var> alt;
	
	public Interpreter(){
		reg = new Stack<Var>();
		alt = new Stack<Var>();
	}
	
	public void execute(File file) throws FileNotFoundException, IOException{
		FileReader fileReader = new FileReader(file);
		char[] charBuffer = new char[32];
		String toExecute = new String();
		int x;
		while((x=fileReader.read(charBuffer))>0){toExecute+=new String(charBuffer).substring(0,x);} // Oh how DIRTY~ Uguu!
		fileReader.close();
		execute(toExecute);
	}
	
	public void execute(String string){
		Concept[] concepts = Compiler.compile(string);
		Parser.parse(concepts,this);
	}
	
}
