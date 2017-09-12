package rprogn.callables.bitwiseoperations;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableBitwiseNot implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(a == null){
			interpreter.push(new VarNumber(1));
			return;
		}
		if(a instanceof VarNumber){
			interpreter.push(255-(((VarNumber) a).data.intValue())&255);
		}else{
			interpreter.push(a.truthy()?new VarNumber(0):new VarNumber(1));
		}
		
	}

	@Override
	public String describe() {
		return "Return the bitwise not of the input";
	}

}
