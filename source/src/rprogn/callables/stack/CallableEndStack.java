package rprogn.callables.stack;

import java.util.Stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallableEndStack implements Callable {
	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		VarStack newStack = new VarStack(interpreter.reg);
		if(!interpreter.stored_reg.isEmpty()){
			interpreter.reg = interpreter.stored_reg.pop();
		}else{
			interpreter.reg = new Stack<Var>();
		}
		interpreter.reg.push(newStack);
		return -1;
	}

	@Override
	public String describe() {
		return "Make a new stack with the contents of reg.";
	}

}
