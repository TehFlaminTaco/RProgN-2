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
	public void Call(Interpreter interpreter, Scope scope) {
		func_a.Call(interpreter, scope);
		func_b.Call(interpreter, scope);
	}

	@Override
	public String describe() {
		return func_a.toString() + func_b.toString();
	}
	
}
