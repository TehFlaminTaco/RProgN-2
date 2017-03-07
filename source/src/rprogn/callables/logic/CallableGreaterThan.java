package rprogn.callables.logic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableGreaterThan implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var b = interpreter.pop();
		Var a = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			VarNumber aN = (VarNumber) a;
			VarNumber bN = (VarNumber) b;
			if(aN.data.compareTo(bN.data)==1){
				interpreter.reg.push(new VarNumber(1));
			}else{
				interpreter.reg.push(new VarNumber(0));
			}
			return;
		}
		
		if(a instanceof VarString && b instanceof VarString){
			VarString aS = (VarString) a;
			VarString bS = (VarString) b;
			if(aS.data.compareTo(bS.data)>=1){
				interpreter.reg.push(new VarNumber(1));
			}else{
				interpreter.reg.push(new VarNumber(0));
			}
			return;
		}
		
		if(a instanceof VarStack && b instanceof VarStack){
			VarStack aS = (VarStack) a;
			VarStack bS = (VarStack) b;
			if(aS.data.size()>bS.data.size()){
				interpreter.reg.push(new VarNumber(1));
			}else{
				interpreter.reg.push(new VarNumber(0));
			}
			return;
		}
		
		if(a instanceof VarCallable && b instanceof VarCallable){
			interpreter.reg.push(new VarNumber(0));
			return;
		}
		
		interpreter.reg.push(new VarNumber(0));
	}

	@Override
	public String describe() {
		return "Compare two values, push 1 if the first value is less than the second, 0 otherwise.";
	}

}
