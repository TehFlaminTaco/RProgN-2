package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarStack;

public class CallableNewStack implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(new VarStack());
	}

	@Override
	public String describe() {
		return "Push a new empty stack to the reg stack.";
	}
	
}
