package rprogn.callables.reg;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableRegSwap implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a!=null){
			interpreter.push(a);
		}
		if(b!=null){
			interpreter.push(b);
		}
	}

	@Override
	public String describe() {
		return "Swap the top two values on the reg stack.";
	}

}
