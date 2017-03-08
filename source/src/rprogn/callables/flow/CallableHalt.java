package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableHalt implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		scope.iPointer = scope.concepts.length;
	}

	@Override
	public String describe() {
		return "End the program.";
	}
	
}
