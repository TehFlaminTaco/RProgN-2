package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableToStack implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if(var instanceof VarString){
			VarStack newStack = new VarStack();
			VarString varStr = (VarString) var;
			for (int i=0; i<varStr.data.length(); i++){
				newStack.push(String.valueOf(varStr.data.charAt(i)));
			}
			interpreter.push(newStack);
		}
		if(var instanceof VarNumber){
			VarNumber varNum = (VarNumber) var;
			VarStack newStack = new VarStack();
			Var func = interpreter.pop();
			if(func instanceof VarCallable){
				for (int i = 1; i <= varNum.data.intValue(); i++){
					interpreter.push(i);
					((VarCallable) func).Call(interpreter, scope);
					if(!interpreter.reg.isEmpty()){
						newStack.push(interpreter.reg.pop());
					}
				}
			}else if(func!=null){
				interpreter.push(var);
				for (int i = 1; i <= varNum.data.intValue(); i++){
					newStack.push((double)i);
				}
			}else{
				for (int i = 1; i <= varNum.data.intValue(); i++){
					newStack.push((double)i);
				}
			}
			interpreter.push(newStack);
		}
		if(var instanceof VarCallable){
			Var num = interpreter.pop();
			VarCallable func = (VarCallable) var;
			if(num instanceof VarNumber){
				VarNumber varNum = (VarNumber) num;
				VarStack newStack = new VarStack();
				for (int i = 1; i <= varNum.data.intValue(); i++){
					interpreter.push(i);
					((VarCallable) func).Call(interpreter, scope);
					if(!interpreter.reg.isEmpty()){
						newStack.push(interpreter.reg.pop());
					}
				}
				interpreter.push(newStack);
			}
		}
	}

	@Override
	public String describe() {
		return "Convert the top of the reg stack to a stack.";
	}

}
