package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;

public class CallableFold implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.pop();
		if(func == null)
			return;
		if(!(func instanceof VarCallable)){
			interpreter.push(func);
			return;
		}
		Var stack = interpreter.pop();
		if(stack == null){
			interpreter.push(func);
			return;
		}
		if(!(stack instanceof VarStack)){
			interpreter.push(stack);
			return;
		}
		VarStack v_S = new VarStack((VarStack)stack);
		VarCallable v_C = (VarCallable)func;
		Var top = v_S.pop();
		VarStack out = new VarStack();
		if(v_S.isEmpty())
			return;
		while(!v_S.isEmpty()){
			Var b = v_S.pop();
			interpreter.push(top);
			interpreter.push(b);
			v_C.Call(interpreter, scope);
			if(interpreter.reg.isEmpty())
				return;
			out.push(interpreter.pop());
			top = b;
		}
		interpreter.push(out);
	}

	@Override
	public String describe() {
		return "Fold a function over a stack";
	}

}
