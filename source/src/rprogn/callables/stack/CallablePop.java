package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallablePop implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if (a instanceof VarStack){
			VarStack stack = (VarStack) a;
			if(!stack.isEmpty()){
				interpreter.push(stack.pop());
			}
		}
	}

	@Override
	public String describe() {
		return "Pop the top value of a stack to the stack.";
	}

}
