package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableTranspose implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(a==null){
			return;
		}
		if(a instanceof VarString){
			a = ((VarString)a).splitLines();
		}
		if(!(a instanceof VarStack)){
			interpreter.push(a);
			return;
		}
		
		VarStack aS = (VarStack) a;
		int width = aS.size();
		int height = 0;
		for(int i=0; i < width; i++){
			Var b = aS.get(i);
			if(b instanceof VarString){
				b = new VarStack((VarString)b);
			}
			if(!(b instanceof VarStack)){
				interpreter.push(a);
				return;
			}
			VarStack bS = (VarStack) b;
			if(height < bS.size()){
				height = bS.size();
			}
		}
		VarStack nS = new VarStack();
		for(int i=0; i < height; i++){
			nS.push((Var)new VarStack());
		}
		for(int i=0; i < width; i++){
			Var b = aS.get(i);
			if(b instanceof VarString){
				b = new VarStack((VarString)b);
			}
			VarStack bS = (VarStack)b;
			for(int c=0; c < bS.size(); c++){
				((VarStack)nS.get(c)).push(bS.get(c));
			}
			for(int c=bS.size(); c < height; c++){
				((VarStack)nS.get(c)).push(" ");
			}
		}
		interpreter.push(nS);
	}

	@Override
	public String describe() {
		return "Transpose a stack of stacks / strings.";
	}

}
