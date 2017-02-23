package rprogn.callables.reg;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableRegPop implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		if(!interpreter.reg.isEmpty()){
			interpreter.reg.pop();
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Pop the top value of the reg stack";
	}

}
