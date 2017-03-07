package rprogn.callables.stack;

import java.util.Stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallableEndStack implements Callable {
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		VarStack newStack = new VarStack(interpreter.reg);
		if(!interpreter.stored_reg.isEmpty()){
			interpreter.reg = interpreter.stored_reg.pop();
		}else{
			interpreter.reg = new Stack<Var>();
		}
		interpreter.reg.push(newStack);
	}

	@Override
	public String describe() {
		return "Make a new stack with the contents of reg.";
	}

}
