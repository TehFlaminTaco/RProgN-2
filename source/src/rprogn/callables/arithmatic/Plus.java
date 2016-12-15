package rprogn.callables.arithmatic;

import java.util.Iterator;


import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;

public class Plus implements Callable {

	@Override
	public int Call(Interpreter interpreter, Functions scope) {
		Var a = interpreter.reg.pop();
		Var b = interpreter.reg.pop();
		
		if(a instanceof VarCallable){
			if(b instanceof VarCallable){
				
			}
			
		}
		if(a instanceof VarStack){
			VarStack newStack = new VarStack();
			Iterator<Var> iter = ((VarStack) a).data.iterator();
			if(b instanceof VarStack){
				while(iter.hasNext()){
					newStack.data.push(iter.next());
				}
				Iterator<Var> bIter = ((VarStack) b).data.iterator();
				while(bIter.hasNext()){
					newStack.data.push(bIter.next());
				}
				interpreter.reg.push(newStack);
				return -1;
			}
		}
		
		return -1;
	}
	
	@Override
	public String describe(){
		return "Pop the top two values from the active stack, Push the values added together to the active stack.";
	}

}
