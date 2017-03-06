package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableSplit implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(!(b instanceof VarString)){
			if(b!=null){
				interpreter.reg.push(b);
			}
			b = a;
			a = new VarString("\\s");
		}
		if (!(a instanceof VarString) || !(b instanceof VarString)){
			return -1;
		}
		
		VarString sA = (VarString)a;
		VarString sB = (VarString)b;
		
		VarStack stack = new VarStack();
		
		String[] split = sB.data.split(sA.data);
		for(int i=0; i<split.length;i++){
			stack.data.push(new VarString(split[i]));
		}
		
		interpreter.reg.push(stack);
		
		return -1;
	}

	@Override
	public String describe() {
		return "Split a string by another.";
	}
	
}
