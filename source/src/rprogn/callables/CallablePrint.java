package rprogn.callables;

import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallablePrint implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		System.out.println(interpreter.reg.isEmpty() ? "null" : interpreter.reg.pop().toString());
		return -1;
	}

	@Override
	public String describe(){
		return "Pop the top of the active stack. Write it to STDOUT with a trailing newline.";
	}
	
}
