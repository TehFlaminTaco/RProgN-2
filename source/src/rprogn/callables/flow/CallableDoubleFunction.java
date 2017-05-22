package rprogn.callables.flow;

import java.util.Arrays;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarCallable;

public class CallableDoubleFunction implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Concept[] newFunc = Arrays.copyOfRange(scope.concepts, scope.iPointer+1, scope.iPointer+3);
		VarCallable callable = new VarCallable(newFunc);
		callable.left_brace = "²";
		interpreter.push(callable);
		scope.iPointer += 2;
	}

	@Override
	public String describe() {
		return "Take the next two functions as a function.";
	}

}
