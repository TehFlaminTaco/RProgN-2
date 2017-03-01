package rprogn.callables.constants;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarCallable;

public class CallablePushSource implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		VarCallable c = new VarCallable(scope.concepts);
		c.braced = false;
		interpreter.reg.push(c);
		return -1;
	}

	@Override
	public String describe() {
		return "Push the block of concepts (As a function) currently being executed.";
	}

}
