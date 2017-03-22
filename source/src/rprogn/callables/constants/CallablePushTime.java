package rprogn.callables.constants;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public class CallablePushTime implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		interpreter.push(BigDecimal.valueOf(System.currentTimeMillis()).divide(new BigDecimal(1000)));
	}

	@Override
	public String describe() {
		return "Push the current time in seconds (to ms accuracy) to the stack.";
	}
	
}
