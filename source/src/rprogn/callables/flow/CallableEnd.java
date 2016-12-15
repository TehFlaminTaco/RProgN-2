package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;

public class CallableEnd implements Callable {

	@Override
	public int Call(Interpreter interpreter, Functions scope) {
		return Flow.flow.isEmpty() ? -1 : Flow.flow.pop();
	}

	@Override
	public String describe() {
		return "Pop the top of the flow stack, and return to that position.";
	}

}
