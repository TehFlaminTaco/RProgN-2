package rprogn.callables.reg;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableDuplicate implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		if(!interpreter.reg.isEmpty()){
			interpreter.push(interpreter.reg.peek());
		}
	}

	@Override
	public String describe() {
		return "Duplicate the top of the reg stack.";
	}
	
}
