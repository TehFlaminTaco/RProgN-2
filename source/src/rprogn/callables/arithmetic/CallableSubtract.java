package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.callables.logic.CallableEquals;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableSubtract implements Callable {

	public static CallableEquals eq = new CallableEquals();
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(b instanceof VarString){
			if(a instanceof VarStack){
				String o = a.toString();
				for(int i=0; i<((VarStack)a).size(); i++){
					o=o.replaceAll(((VarStack)a).get(i).toString(), "");
				}
				interpreter.push(o);
			}else{
				interpreter.push(((VarString)b).data.replaceAll(a.toString(), ""));
			}
		}
		
		if(b instanceof VarStack){
			VarStack stack = (VarStack) b;
			VarStack newStack = new VarStack();
			for(int i=0; i<stack.size();i++){
				if(a instanceof VarCallable){
					interpreter.push(stack.get(i));
					a.getCallable().Call(interpreter, scope);
					Var out = interpreter.pop();
					if(out != null && !out.truthy()){
						newStack.push(stack.get(i));
					}
				}else{
					interpreter.push(stack.get(i));
					interpreter.push(a);
					eq.Call(interpreter, scope);
					Var out = interpreter.pop();
					if(out != null && !out.truthy()){
						newStack.push(stack.get(i));
					}
				}
			}
			interpreter.push(newStack);
		}
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.push(((VarNumber)b).data.subtract(((VarNumber)a).data));
		}
		
	}

	@Override
	public String describe() {
		
		return "Pop the top two values from the active stack, The top value is subtracted from the value underneith, and the result is pushed to the active stack.";
	}

}
