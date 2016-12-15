package rprogn.variable;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;

public class VarCallable extends Var implements Callable {
	public Concept[] data;
	
	public int Call(Interpreter interpreter, Functions scope){
		return -1;
	}
	
	public String describe(){
		return "A Variable that holds an array of concepts, which when called, parses those concepts.";
	}
	
}
