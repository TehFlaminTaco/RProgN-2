package rprogn.callables.string;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;

public class CallableInverse implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if (var instanceof VarStack){
			VarStack newStack = new VarStack();
			VarStack stack = (VarStack) var;
			for(int i = stack.size()-1; i>=0; i--){
				newStack.push(stack.get(i));
			}
			interpreter.push(newStack);
		}else if(var instanceof VarNumber){
			interpreter.push(BigDecimal.ZERO.subtract(((VarNumber) var).data));
		}else if(var != null){
			interpreter.push(new StringBuilder(var.toString()).reverse().toString());
		}
	}

	@Override
	public String describe() {
		return "Get the inverse of a variable";
	}

}
