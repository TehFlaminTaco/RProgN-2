package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableUpper implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		
		if(var instanceof VarString){
			interpreter.push(((VarString)var).data.toUpperCase());
		}
		if(var instanceof VarStack){
			VarStack newStack = new VarStack();
			VarStack stack = (VarStack) var;
			for(int i=0; i<stack.size(); i++){
				Var tmp = stack.get(i);
				if(tmp instanceof VarString){
					VarString str = (VarString) tmp;
					newStack.push(str.data.toUpperCase());
				}
			}
			interpreter.push(newStack);
		}
	}

	@Override
	public String describe() {
		return "Convert a string to Uppercase.";
	}

}
