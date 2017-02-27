package rprogn.callables.logic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableNot implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		interpreter.reg.push(a.truthy() ? new VarNumber(0) : new VarNumber(1));
		return -1;
	}

	@Override
	public String describe() {
		return "Push 1 if the value on top of the stack is falsy, 0 otherwise.";
	}

}
