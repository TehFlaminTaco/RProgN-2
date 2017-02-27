package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableModulos implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			
			interpreter.reg.push(new VarNumber((((VarNumber)b).data.remainder(((VarNumber)a).data))));
		}
		
		return -1;
	}

	@Override
	public String describe() {
		return "Get the value of a % b";
	}

}
