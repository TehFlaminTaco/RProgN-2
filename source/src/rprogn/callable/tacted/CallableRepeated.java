package rprogn.callable.tacted;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableRepeated implements Callable {

	public int count;
	public Callable parent;
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		for (int i=0; i < count; i++){
			parent.Call(interpreter, scope);
		}
	}

	@Override
	public String describe() {
		return "A function repeated many times.";
	}
	
}
