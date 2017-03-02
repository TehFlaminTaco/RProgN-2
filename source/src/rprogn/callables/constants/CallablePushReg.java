package rprogn.callables.constants;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarStack;

public class CallablePushReg implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(new VarStack(interpreter.reg));
		return -1;
	}

	@Override
	public String describe() {
		return "Push the reg stack to the reg stack.";
	}

}
