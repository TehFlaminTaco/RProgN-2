package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableModulos implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			
			interpreter.push(new VarNumber((((VarNumber)b).data.remainder(((VarNumber)a).data))));
		}
		
	}

	@Override
	public String describe() {
		return "Get the value of a % b";
	}

}
