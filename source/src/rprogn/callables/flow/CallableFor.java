package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;

public class CallableFor implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		if (func instanceof VarCallable){
			VarCallable funcC = (VarCallable) func;
			Var key = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
			if (key instanceof VarStack){
				VarStack stack = (VarStack) key;
				for (int i=0; i < stack.data.size(); i++){
					interpreter.reg.push(stack.data.get(i));
					funcC.Call(interpreter, scope);
				}
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Iterate through something, calling a function on each element.";
	}

}
