package rprogn.variable;

import rprogn.callables.Callable;
import rprogn.callables.CallablePushVar;

public class Var {

	public Callable getCallable() {
		return new CallablePushVar(this);
	}
	
	public String toString(){
		return "nil";
	}

}
