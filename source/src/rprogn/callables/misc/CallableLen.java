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
	public int Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if(var instanceof VarNumber){
			interpreter.reg.push(new VarNumber(String.valueOf(((VarNumber)var).data).length()));
		}
		if(var instanceof VarString){
			interpreter.reg.push(new VarNumber(((VarString) var).data.length()));
		}
		if(var instanceof VarStack){
			interpreter.reg.push(new VarNumber(((VarStack) var).data.size()));
		}
		if(var instanceof VarCallable){
			VarCallable func = (VarCallable) var;
			
			if(func.data!=null){
				interpreter.reg.push(new VarNumber(func.data.length));
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Push a new number based on the size of the top of the stack";
	}

}
