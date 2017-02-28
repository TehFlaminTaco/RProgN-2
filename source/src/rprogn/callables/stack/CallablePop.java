package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallablePop implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if (a instanceof VarStack){
			VarStack stack = (VarStack) a;
			if(!stack.data.isEmpty()){
				interpreter.reg.push(stack.data.pop());
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Pop the top value of a stack to the stack.";
	}

}
