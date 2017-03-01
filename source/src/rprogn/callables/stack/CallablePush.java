package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallablePush implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		if(!(a instanceof VarStack) && b instanceof VarStack){
			Var c = a;
			a = b;
			b = c;
		}
		if(a instanceof VarStack){
			VarStack stack = (VarStack) a;
			stack.data.push(b);
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Push a value to a stack.";
	}

}