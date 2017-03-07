package rprogn.callables.flow;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableSatisfy implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		Callable func = var.getCallable();
		if(func==null){return;}
		
		BigDecimal n=BigDecimal.ZERO;
		while(true){
			interpreter.reg.push(new VarNumber(n));
			func.Call(interpreter, scope);
			Var popped = interpreter.pop();
			if(popped!=null && popped.truthy()){
				interpreter.reg.push(new VarNumber(n));
				break;
			}
			n=n.add(BigDecimal.ONE);
		}
	}

	@Override
	public String describe() {
		return "Find the first Positive Integer which, when passed to the supplied function, returns a truthy value.";
	}

}
