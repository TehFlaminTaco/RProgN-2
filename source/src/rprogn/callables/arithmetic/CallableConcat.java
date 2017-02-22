package rprogn.callables.arithmetic;

import rprogn.callable.tacted.CallableTacted;
import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallableConcat implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var b = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var a = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		
		if(a instanceof VarCallable || b instanceof VarCallable){
			interpreter.reg.push(new VarCallable(new CallableTacted(b.getCallable(),a.getCallable())));
			return -1;
		}
		
		String str = a.toString() + b.toString();
		
		Var out;
		
		if(str.matches("^(-?\\d+(.\\d+)?|.\\d+)$")){
			out = new VarNumber(Double.parseDouble(str));
		}else{
			out = new VarString(str);
		}
		
		interpreter.reg.push(out);
		
		return -1;
	}

	@Override
	public String describe() {
		return "Combine two variables";
	}

}
