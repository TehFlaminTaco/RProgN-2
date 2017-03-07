package rprogn.callables.constants;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarCallable;

public class CallablePushSource implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		VarCallable c = new VarCallable(scope.concepts);
		c.braced = false;
		interpreter.push(c);
	}

	@Override
	public String describe() {
		return "Push the block of concepts (As a function) currently being executed.";
	}

}
