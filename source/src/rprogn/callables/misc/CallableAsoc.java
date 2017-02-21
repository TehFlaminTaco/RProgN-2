package rprogn.callables.misc;

import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarString;

public class CallableAsoc implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var name = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var var = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		
		if(name instanceof VarString && var != null){
			Functions funcs = scope.functions;
			while (funcs.parent!=null){
				funcs = funcs.parent;
			}
			funcs.custom_asoc.put(((VarString)name).data, var.getCallable());
		}
		
		return -1;
	}

	@Override
	public String describe() {
		return "Associate a variable with a concept";
	}

}
