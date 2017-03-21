package rprogn.callables.stack;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableRotateLeft implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.reg.pop();
		
		if(var instanceof VarStack){
			VarStack newStack = new VarStack();
			VarStack stack = (VarStack) var;
			for(int i=1; i<stack.size(); i++){
				newStack.push(stack.get(i));
			}
			newStack.push(stack.get(0));
			interpreter.push(newStack);
		}else if(var instanceof VarString){
			String s = "";
			VarString old = (VarString)var;
			s += old.data.substring(1, old.data.length());
			s += old.data.substring(0, 1);
			interpreter.push(s);
		}else if(var instanceof VarNumber){
			VarNumber n = (VarNumber)var;
			interpreter.push(n.data.multiply(new BigDecimal(2)));
		}
		
	}

	@Override
	public String describe() {
		return "Rotates the top of the stack 1 to the left, may also be a bshift left for numbers.";
	}
	
}
