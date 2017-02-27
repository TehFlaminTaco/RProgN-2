package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarNumber;

public class CallableToNumber implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(new VarNumber(interpreter.pop()));
		return -1;
	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
