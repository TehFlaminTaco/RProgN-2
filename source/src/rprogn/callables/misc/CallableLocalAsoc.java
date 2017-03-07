package rprogn.callables.misc;

import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarString;

public class CallableLocalAsoc implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var name = interpreter.pop();
		Var var = interpreter.pop();
		
		if(name instanceof VarString && var != null){
			Functions funcs = scope.functions;
			funcs.custom_asoc.put(((VarString)name).data, var.getCallable());
		}
		
	}

	@Override
	public String describe() {
		return "Associate a variable with a concept for just this function.";
	}
	
}
