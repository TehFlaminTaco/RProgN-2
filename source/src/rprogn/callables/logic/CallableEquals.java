package rprogn.callables.logic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableEquals implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		interpreter.push(a.equals(b) ? 1 : 0);
	}

	@Override
	public String describe() {
		return "Compare two values, push 1 if they are equal, 0 otherwise.";
	}
	
}
