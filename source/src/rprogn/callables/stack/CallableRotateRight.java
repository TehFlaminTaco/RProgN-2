package rprogn.callables.stack;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableRotateRight implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.reg.pop();
		
		if(var instanceof VarStack){
			VarStack newStack = new VarStack();
			VarStack stack = (VarStack) var;
			newStack.push(stack.peek());
			for(int i=0; i<(stack.size()-1); i++){
				newStack.push(stack.get(i));
			}
			interpreter.push(newStack);
		}else if(var instanceof VarString){
			String s = "";
			VarString old = (VarString)var;
			s += old.data.substring(old.data.length()-1);
			s += old.data.substring(0, old.data.length()-1);
			interpreter.push(s);
		}else if(var instanceof VarNumber){
			VarNumber n = (VarNumber)var;
			interpreter.push(n.data.divideToIntegralValue(new BigDecimal(2)));
		}
		
	}

	@Override
	public String describe() {
		return "Rotates the top of the stack 1 to the right, may also be a bshift right for numbers.";
	}
	
}
