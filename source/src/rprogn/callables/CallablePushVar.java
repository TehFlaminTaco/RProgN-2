package rprogn.callables;

import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallablePushVar implements Callable {
	
	Var data;

	public CallablePushVar(Var data){
		this.data = data;
	}
	
	public CallablePushVar(String str){
		this.data = new VarString(str);
	}
	
	public CallablePushVar(double d){
		this.data = new VarNumber(d);
	}
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(data);
	}
	
	@Override
	public String describe(){
		return "Push the "+this.data.type()+" \""+data.toString()+"\" to the stack.";
	}
	
}
