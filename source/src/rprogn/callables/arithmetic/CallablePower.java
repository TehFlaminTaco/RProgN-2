package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallablePower implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.reg.push(new VarNumber(((VarNumber)b).data.pow(((VarNumber)a).data.intValue())));
		}
		
		return -1;
	}

	@Override
	public String describe() {
		
		return "Pop two values from the reg stack, the value under the top is raised to the top power, and pushed to the reg stack.";
	}

}
