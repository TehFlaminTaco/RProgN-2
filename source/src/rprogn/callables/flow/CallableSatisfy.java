package rprogn.callables.flow;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableSatisfy implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		Callable func = var.getCallable();
		if(func==null){return -1;}
		
		BigDecimal n=new BigDecimal(0);
		while(true){
			interpreter.reg.push(new VarNumber(n));
			func.Call(interpreter, scope);
			Var popped = interpreter.pop();
			if(popped!=null && popped.truthy()){
				interpreter.reg.push(new VarNumber(n));
				break;
			}
			n=n.add(new BigDecimal(1));
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Find the first Positive Integer which, when passed to the supplied function, returns a truthy value.";
	}

}
