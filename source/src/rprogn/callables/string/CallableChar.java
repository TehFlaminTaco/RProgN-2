package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableChar implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var s = interpreter.reg.pop();
		if (s instanceof VarString){
			String str = ((VarString)s).data;
			interpreter.push((int)str.charAt(0));
		}else if(s instanceof VarStack){
			VarStack stack = (VarStack) s;
			VarStack newStack = new VarStack();
			for(int i=0; i < stack.size(); i++){
				Var sub = stack.get(i);
				if(sub instanceof VarString){
					newStack.push((double)((VarString)sub).data.charAt(0));
				}
			}
			interpreter.push((Var)newStack);
		}
	}

	@Override
	public String describe() {
		return "Get the char value of the first character of a string.";
	}

}
