package rprogn.callables.stack;

import java.util.Stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableStartStack implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		interpreter.stored_reg.push(interpreter.reg);
		interpreter.reg = new Stack<Var>();
	}

	@Override
	public String describe() {
		return "Store reg, later to be restoed by CallableEndStack";
	}
	
}
