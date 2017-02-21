package rprogn.callables.misc;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;

public class CallableCall implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		if(func instanceof VarCallable){
			return func.getCallable().Call(interpreter, scope);
		}
		if(func!=null){
			interpreter.execute(func.toString());
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Call the function sitting on the top of the stack. If it's a string, try and run that.";
	}

}
