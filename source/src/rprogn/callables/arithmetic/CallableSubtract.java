package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableSubtract implements Callable {

	@Override
	public int Call(Interpreter interpreter, Functions scope) {
		Var a = interpreter.reg.pop();
		Var b = interpreter.reg.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.reg.push(new VarNumber(((VarNumber)b).data - ((VarNumber)a).data));
		}
		
		return -1;
	}

	@Override
	public String describe() {
		
		return "Pop the top two values from the active stack, The top value is subtracted from the value underneith, and the result is pushed to the active stack.";
	}

}
