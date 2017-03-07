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
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		
		if(a instanceof VarStack){
			VarStack stack = (VarStack) a;
			if (stack.size()>0){
				Var o = stack.get(0);
				for(int i = 1; i < stack.size(); i++){
					o = add(stack.get(i), o);
				}
				interpreter.push(o);
			}
			return;
		}
		
		Var b = interpreter.pop();
		
		if(b instanceof VarStack){
			if(a!=null){
				interpreter.push(a);
				VarStack stack = (VarStack) b;
				if (stack.size()>0){
					Var o = stack.get(0);
					for(int i = 1; i < stack.size(); i++){
						o = add(stack.get(i), o);
					}
					interpreter.push(o);
				}
			}
		}else{
			Var o = add(a,b);
			if (o!=null){
				interpreter.push(o);
			}
		}
		
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
				return new VarNumber(((VarNumber) a).data.add(((VarNumber) b).data));
			}
		}
		return null;
	}

	@Override
	public String describe(){
		return "Pop the top two values from the active stack, Push the values added together to the active stack.";
	}

}
