package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.helpers.RUtil;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallablePower implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.push(RUtil.pow(((VarNumber)b).data,((VarNumber)a).data));
		}
		
	}

	@Override
	public String describe() {
		
		return "Pop two values from the reg stack, the value under the top is raised to the top power, and pushed to the reg stack.";
	}

}
