package rprogn.callables.arithmetic;


import rprogn.callable.tacted.CallableTacted;
import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallablePlus implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var b = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		
		if(b instanceof VarStack){
			if(a!=null){
				interpreter.reg.push(a);
				VarStack stack = (VarStack) b;
				if (stack.data.size()>0){
					Var o = stack.data.get(0);
					for(int i = 1; i < stack.data.size(); i++){
						o = add(stack.data.get(i), o);
					}
					interpreter.reg.push(o);
				}
			}
		}else{
			Var o = add(a,b);
			if (o!=null){
				interpreter.reg.push(o);
			}
		}
		
		return -1;
	}
	
	public Var add(Var a, Var b){
		if(a instanceof VarCallable || b instanceof VarCallable){
			return new VarCallable(new CallableTacted(b.getCallable(),a.getCallable()));
		}
		
		if(a instanceof VarString || b instanceof VarString){
			return new VarString(b.toString()+a.toString());
		}
		
		if(a instanceof VarNumber){
			if(b instanceof VarNumber){
				return new VarNumber(((VarNumber) a).data + ((VarNumber) b).data);
			}
		}
		return null;
	}

	@Override
	public String describe(){
		return "Pop the top two values from the active stack, Push the values added together to the active stack.";
	}

}
