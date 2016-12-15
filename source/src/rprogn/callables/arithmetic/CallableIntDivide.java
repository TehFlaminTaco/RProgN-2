package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableIntDivide implements Callable {

	@Override
	public int Call(Interpreter interpreter, Functions scope) {
		Var a = interpreter.reg.pop();
		Var b = interpreter.reg.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.reg.push(new VarNumber((int)(((VarNumber)b).data / ((VarNumber)a).data)));
		}
		
		return -1;
	}

	@Override
	public String describe() {
		
		return "Pop two values from the top of the stack, Integer Divide the value under the top by the top and push it to the reg stack.";
	}

}
