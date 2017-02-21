package rprogn.variable;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.interpreter.Parser;

public class VarCallable extends Var implements Callable {
	public Concept[] data;
	
	
	public VarCallable(Concept[] init){
		data = init;
	}
	
	
	public Callable getCallable(){
		return this;
	}
	
	public String toString(){
		return "[FUNCTION]";
	}
	
	public int Call(Interpreter interpreter, Scope scope){
		Parser.parse(data, interpreter, scope.functions);
		return -1;
	}
	
	public String describe(){
		return "A Variable that holds an array of concepts, which when called, parses those concepts.";
	}
	
	public boolean truthy(){
		return true;
	}
	
}
