package rprogn.callables.constants;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarStack;

public class CallablePushReg implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		interpreter.reg.push(new VarStack(interpreter.reg));
	}

	@Override
	public String describe() {
		return "Push the reg stack to the reg stack.";
	}

}
