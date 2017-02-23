package rprogn.callables.reg;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableRegSwap implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var b = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		
		if(a!=null){
			interpreter.reg.push(a);
		}
		if(b!=null){
			interpreter.reg.push(b);
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Swap the top two values on the reg stack.";
	}

}
