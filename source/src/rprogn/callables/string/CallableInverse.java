package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableInverse implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if (var instanceof VarStack){
			VarStack newStack = new VarStack();
			VarStack stack = (VarStack) var;
			for(int i = stack.data.size()-1; i>=0; i--){
				newStack.data.push(stack.data.get(i));
			}
			interpreter.reg.push(newStack);
		}else if(var != null){
			interpreter.reg.push(new VarString(new StringBuilder(var.toString()).reverse().toString()));
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Get the inverse of a variable";
	}

}
