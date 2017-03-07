package rprogn.callables;

import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallableWrite implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		System.out.print(interpreter.reg.isEmpty() ? "null" : interpreter.reg.pop().toString());
	}

	@Override
	public String describe() {
		return "Pop the top of the active stack. Write it to STDOUT as is without a trailing newline.";
	}
	
}
