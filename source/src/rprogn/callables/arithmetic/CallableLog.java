package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.helpers.RUtil;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableLog implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(!(a instanceof VarNumber)){if(a!=null){interpreter.push(a);}return;}
		Var b = interpreter.pop();
		if(!(b instanceof VarNumber)){if(b!=null){interpreter.push(b);}interpreter.push(a);return;}
		
		interpreter.push(RUtil.log(((VarNumber)b).data, ((VarNumber)a).data, 64));
	}

	@Override
	public String describe() {
		return "Push log{a}{b} to the stack.";
	}

}
