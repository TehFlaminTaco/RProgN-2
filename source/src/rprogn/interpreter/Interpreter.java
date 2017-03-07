package rprogn.interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

import rprogn.Explain;
import rprogn.Flags;
import rprogn.compiler.Compiler;
import rprogn.compiler.concept.Concept;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class Interpreter {
	public Stack<Var> reg;
	public Stack<Stack<Var>> stored_reg; // You know it's good when you've got a 2d Stack.
	
	public Interpreter(){
		reg = new Stack<Var>();
		stored_reg = new Stack<Stack<Var>>();
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
		try{
			if(Flags.FlagToggled("d")){
				System.err.print(Explain.explain(concepts));
			}
			Parser.parse(concepts,this);
		}catch(Exception e){
			System.err.println(e.toString());
		}
	}
	
	public Var pop(){
		return reg.isEmpty() ? null : reg.pop();
	}
	
	public void push(Var var){
		reg.push(var);
	}
	
	public void push(String str){
		reg.push(new VarString(str));
	}
	
	public void push(double n){
		reg.push(new VarNumber(n));
	}
	
	public void push(BigDecimal n){
		reg.push(new VarNumber(n));
	}
	
	public void push(BigInteger n){
		reg.push(new VarNumber(new BigDecimal(n)));
	}
	
	public void push(Stack<Var> stack){
		reg.push(new VarStack(stack));
	}
	
	public void push(VarStack vStack){
		reg.push(vStack);
	}
	
}
