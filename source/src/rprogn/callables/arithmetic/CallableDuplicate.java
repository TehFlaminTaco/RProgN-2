package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableDuplicate implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(interpreter.reg.peek());
		return -1;
	}

	@Override
	public String describe() {
		return "Duplicate the top of the reg stack.";
	}
	
}
