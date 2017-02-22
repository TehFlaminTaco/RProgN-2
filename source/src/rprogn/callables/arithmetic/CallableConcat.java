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
	public int Call(Interpreter interpreter, Scope scope) {
		Var b = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var a = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		
		if(b instanceof VarStack){
			if(a!=null){
				interpreter.reg.push(a);
			}
			VarStack stack = (VarStack) b;
			if (stack.data.size()>0){
				Var o = stack.data.get(0);
				for(int i = 1; i < stack.data.size(); i++){
					o = concat(o, stack.data.get(i));
				}
				interpreter.reg.push(o);
			}
		}else{
			interpreter.reg.push(concat(a,b));
		}
		return -1;
	}
	
	public Var concat(Var a, Var b){
		if(a instanceof VarCallable || b instanceof VarCallable){
			return new VarCallable(new CallableTacted(b.getCallable(),a.getCallable()));
		}
		
		String str = a.toString() + b.toString();
		return new VarString(str);
	}

	@Override
	public String describe() {
		return "Combine two variables";
	}

}
