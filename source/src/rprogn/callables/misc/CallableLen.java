package rprogn.callables.misc;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableLen implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if(var instanceof VarNumber){
			interpreter.push(String.valueOf(((VarNumber)var).data).length());
		}
		if(var instanceof VarString){
			interpreter.push(((VarString) var).data.length());
		}
		if(var instanceof VarStack){
			interpreter.push(((VarStack) var).size());
		}
		if(var instanceof VarCallable){
			VarCallable func = (VarCallable) var;
			
			if(func.data!=null){
				interpreter.push(func.data.length);
			}
		}
	}

	@Override
	public String describe() {
		return "Push a new number based on the size of the top of the stack";
	}

}
