package rprogn.callables;

import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallablePushVar implements Callable {
	
	Var data;

	public CallablePushVar(Var data){
		this.data = data;
	}
	
	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(data);
		return -1;
	}
	
	@Override
	public String describe(){
		return "Push the "+this.data.type()+" \""+data.toString()+"\" to the stack.";
	}
	
}
