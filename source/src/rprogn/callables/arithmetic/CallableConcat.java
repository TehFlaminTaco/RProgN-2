package rprogn.callables.arithmetic;

import rprogn.callable.tacted.CallableTacted;
import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableConcat implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var b = interpreter.pop();
		Var a = interpreter.pop();
		
		if(a==null && b==null){return;}
		
		if(b instanceof VarStack || a instanceof VarStack){
			Var stepper=null;
			if (a instanceof VarStack){
				stepper = b;
				b = a;
			}
			VarStack stack = (VarStack) b;
			if (stack.data.size()>0){
				Var o = stack.data.get(0);
				for(int i = 1; i < stack.data.size(); i++){
					if(stepper!=null){
						o = concat(o, stepper);
					}
					o = concat(o, stack.data.get(i));
				}
				interpreter.reg.push(o);
			}
		}else{
			interpreter.reg.push(concat(a,b));
		}
	}
	
	public Var concat(Var a, Var b){
		
		if(a instanceof VarString || b instanceof VarString){
			return new VarString(a.toString() + b.toString());
		}else if(a instanceof VarCallable || b instanceof VarCallable){
			return new VarCallable(new CallableTacted(b.getCallable(),a.getCallable()));
		}
		if(a==null){return b;}
		if(b==null){return a;}
		String str = a.toString() + b.toString();
		return new VarString(str);
	}

	@Override
	public String describe() {
		return "Combine two variables";
	}

}
