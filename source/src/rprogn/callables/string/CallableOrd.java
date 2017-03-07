package rprogn.callables.string;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableOrd implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var s = interpreter.reg.pop();
		if (s instanceof VarNumber){
			BigDecimal chr = ((VarNumber)s).data;
			interpreter.push(new VarString(String.valueOf((char)chr.intValue())));
		}else if(s instanceof VarStack){
			VarStack stack = (VarStack) s;
			VarStack newStack = new VarStack();
			for(int i=0; i < stack.size(); i++){
				Var sub = stack.get(i);
				if(sub instanceof VarNumber){
					BigDecimal chr = ((VarNumber)sub).data;
					newStack.push(new VarString(String.valueOf((char)chr.intValue())));
				}
			}
			interpreter.push(newStack);
		}
	}

	@Override
	public String describe() {
		return "Get the character represented by an inputted number.";
	}

}
