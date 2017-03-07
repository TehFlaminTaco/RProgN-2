package rprogn.callables.flow;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;

public class CallableFor implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.pop();
		if (func instanceof VarCallable){
			VarCallable funcC = (VarCallable) func;
			Var key = interpreter.pop();
			if (key instanceof VarStack){
				VarStack stack = (VarStack) key;
				for (int i=0; i < stack.data.size(); i++){
					interpreter.reg.push(stack.data.get(i));
					funcC.Call(interpreter, scope);
				}
				return;
			}
			if (key instanceof VarNumber){
				Var max = interpreter.pop();
				Var min = interpreter.pop();
				if (!(max instanceof VarNumber)){
					if (min!=null){interpreter.reg.push(min);}
					if (max!=null){interpreter.reg.push(max);}
					min = new VarNumber(1);
					max = key;
					key = new VarNumber(1);
				}
				if (!(min instanceof VarNumber)){
					if (min!=null){interpreter.reg.push(min);}
					min = max;
					max = key;
					key = new VarNumber(1);
				}
				VarNumber Nmin = (VarNumber) min;
				VarNumber Nmax = (VarNumber) max;
				VarNumber Nkey = (VarNumber) key;
				for (BigDecimal i=Nmin.data; i.compareTo(Nmax.data)<=0; i=i.add(Nkey.data)){
					interpreter.reg.push(new VarNumber(i));
					funcC.Call(interpreter, scope);
				}
				
			}
		}
	}

	@Override
	public String describe() {
		return "Iterate through something, calling a function on each element.";
	}

}
