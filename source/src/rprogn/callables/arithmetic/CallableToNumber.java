package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarNumber;

public class CallableToNumber implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(new VarNumber(interpreter.pop()));
	}

	@Override
	public String describe() {
		return "Convert a Variable to a Number.";
	}

}
