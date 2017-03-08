package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableIntDivide implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			
			interpreter.push((((VarNumber)b).data.divideToIntegralValue(((VarNumber)a).data)));
		}
		
	}

	@Override
	public String describe() {
		
		return "Pop two values from the top of the stack, Integer Divide the value under the top by the top and push it to the reg stack.";
	}

}
