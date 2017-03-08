package rprogn.callables;

import rprogn.compiler.concept.Concept;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public interface Callable {
	public void Call(Interpreter interpreter, Scope scope);

	public String describe();
	
	public default String describe(Concept[] c, int i){
		return describe();
	}
}
