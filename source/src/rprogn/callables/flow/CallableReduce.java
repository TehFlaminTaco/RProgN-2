package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;

public class CallableReduce implements Callable {

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
		if(v_S.isEmpty())
			return;
		Var top = v_S.pop();
		while(!v_S.isEmpty()){
			interpreter.push(top);
			interpreter.push(v_S.pop());
			v_C.Call(interpreter, scope);
			top = interpreter.pop();
			if(top == null)
				return;
		}
		interpreter.push(top);
	}

	@Override
	public String describe() {
		return "Reduce a stack to a single value through a function";
	}

}
