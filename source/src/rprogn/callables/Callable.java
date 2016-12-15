package rprogn.callables;

import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;

public interface Callable {
	public int Call(Interpreter interpreter, Functions scope);

	public String describe();
}
