package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallableFlatten implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(a == null){
			return;
		}
		if(!(a instanceof VarStack)){
			interpreter.push(a);
			return;
		}
		interpreter.push(flatten((VarStack)a));
	}
	
	private VarStack flatten(VarStack in){
		return flatten(in, new VarStack());
	}
	
	private VarStack flatten(VarStack in, VarStack out){
		for(int i=0; i < in.size(); i++){
			Var a = in.get(i);
			if(a instanceof VarStack){
				flatten((VarStack)a, out);
			}else{
				out.push(a);
			}
		}
		
		return out;
	}

	@Override
	public String describe() {
		return "Flatten a stack of stacks into a single stack.";
	}

}
