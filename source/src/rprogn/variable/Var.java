package rprogn.variable;

import rprogn.callables.Callable;
import rprogn.callables.CallablePushVar;

public interface Var {

	public default Callable getCallable() {
		return new CallablePushVar(this);
	}
	
	public default boolean truthy(){
		return false;
	}
	
	public default String type(){
		return "unknown";
	}
	
	public default boolean equals(Var other){
		return false;
	}
}
