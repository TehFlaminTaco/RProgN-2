package rprogn.callables.logic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableEquals implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			VarNumber aN = (VarNumber) a;
			VarNumber bN = (VarNumber) b;
			if(aN.data.compareTo(bN.data)==0){
				interpreter.push(new VarNumber(1));
			}else{
				interpreter.push(new VarNumber(0));
			}
			return;
		}
		
		if(a instanceof VarString && b instanceof VarString){
			VarString aS = (VarString) a;
			VarString bS = (VarString) b;
			if(aS.data.equals(bS.data)){
				interpreter.push(new VarNumber(1));
			}else{
				interpreter.push(new VarNumber(0));
			}
			return;
		}
		
		if(a instanceof VarStack && b instanceof VarStack){
			VarStack aS = (VarStack) a;
			VarStack bS = (VarStack) b;
			if(aS==bS){ // Reference equality for stacks. I'm so sorry.
				interpreter.push(new VarNumber(1));
			}else{
				interpreter.push(new VarNumber(0));
			}
			return;
		}
		
		if(a instanceof VarCallable && b instanceof VarCallable){
			VarCallable aC = (VarCallable) a;
			VarCallable bC = (VarCallable) b;
			
			if (
					(aC.data == null && bC.data == null && aC.otherDat==bC.otherDat) ||
					(aC.otherDat == null && bC.data == null && aC.data==bC.data)
				)
			{
				interpreter.push(new VarNumber(1));
			}else{
				interpreter.push(new VarNumber(0));
			}
			return;
		}
		
		interpreter.push(new VarNumber(0));
	}

	@Override
	public String describe() {
		return "Compare two values, push 1 if they are equal, 0 otherwise.";
	}
	
}
