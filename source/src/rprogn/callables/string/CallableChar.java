package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableChar implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var s = interpreter.reg.pop();
		if (s instanceof VarString){
			String str = ((VarString)s).data;
			interpreter.reg.push(new VarNumber((int)str.charAt(0)));
		}else if(s instanceof VarStack){
			VarStack stack = (VarStack) s;
			VarStack newStack = new VarStack();
			for(int i=0; i < stack.data.size(); i++){
				Var sub = stack.data.get(i);
				if(sub instanceof VarString){
					newStack.data.push(new VarNumber(((VarString)sub).data.charAt(0)));
				}
			}
			interpreter.reg.push(newStack);
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Get the char value of the first character of a string.";
	}

}
