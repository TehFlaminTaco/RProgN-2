package rprogn.callables.reg;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableRegPop implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		if(!interpreter.reg.isEmpty()){
			interpreter.reg.pop();
		}
	}

	@Override
	public String describe() {
		return "Pop the top value of the reg stack";
	}

}
