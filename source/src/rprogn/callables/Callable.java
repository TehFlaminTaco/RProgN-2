package rprogn.callables;

import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;

public interface Callable {
	public int Call(Interpreter interpreter, Scope scope);

	public String describe();
}
