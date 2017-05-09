package rprogn.callables.flow;

import java.util.Arrays;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarCallable;

public class CallableTripleFunction implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Concept[] newFunc = Arrays.copyOfRange(scope.concepts, scope.iPointer+1, scope.iPointer+4);
		interpreter.push(new VarCallable(newFunc));
		scope.iPointer += 3;
	}

	@Override
	public String describe() {
		return "Take the next three functions as a function.";
	}

}
