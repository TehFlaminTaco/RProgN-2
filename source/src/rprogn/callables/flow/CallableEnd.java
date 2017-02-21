package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableEnd implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		return -1; // Completely removed the old system of flow of control, this is only used in functions.
	}

	@Override
	public String describe() {
		return "Pop the top of the flow stack, and return to that position.";
	}

}
