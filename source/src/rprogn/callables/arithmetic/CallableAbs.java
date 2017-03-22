package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableAbs implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(a instanceof VarNumber){
			interpreter.push(((VarNumber)a).data.abs());
		}
	}

	@Override
	public String describe() {
		return "Return the Absolute value of the top of the stack.";
	}

}
