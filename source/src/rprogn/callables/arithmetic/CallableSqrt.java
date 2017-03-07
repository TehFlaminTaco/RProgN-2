package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.helpers.BigIntSqRoot;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableSqrt implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(a instanceof VarNumber){
			interpreter.push(new VarNumber(BigIntSqRoot.bigDecimalSqRootFloor(((VarNumber)a).data.toBigInteger())));
		}
	}

	@Override
	public String describe() {
		return "Get the Sqrt of the input.";
	}

}
