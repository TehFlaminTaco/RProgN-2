package rprogn.callable.tacted;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableTacted implements Callable {

	public Callable func_a;
	public Callable func_b;
	
	public CallableTacted(Callable func_a, Callable func_b){
		this.func_a = func_a;
		this.func_b = func_b;
	}
	
	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		func_a.Call(interpreter, scope);
		func_b.Call(interpreter, scope);
		return -1;
	}

	@Override
	public String describe() {
		return "Two functions added together.";
	}
	
}
