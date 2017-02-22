package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarStack;

public class CallableNewStack implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(new VarStack());
		return -1;
	}

	@Override
	public String describe() {
		return "Push a new empty stack to the reg stack.";
	}
	
}
