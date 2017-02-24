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
	public int Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		if(var instanceof VarString){
			VarStack newStack = new VarStack();
			VarString varStr = (VarString) var;
			for (int i=0; i<varStr.data.length(); i++){
				newStack.data.push(new VarString(String.valueOf(varStr.data.charAt(i))));
			}
			interpreter.reg.push(newStack);
		}
		if(var instanceof VarNumber){
			VarNumber varNum = (VarNumber) var;
			VarStack newStack = new VarStack();
			Var func = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
			if(func instanceof VarCallable){
				for (int i = 1; i <= varNum.data.intValue(); i++){
					interpreter.reg.push(new VarNumber(i));
					((VarCallable) func).Call(interpreter, scope);
					if(!interpreter.reg.isEmpty()){
						newStack.data.push(interpreter.reg.pop());
					}
				}
			}else if(func!=null){
				interpreter.reg.push(var);
				for (int i = 1; i <= varNum.data.intValue(); i++){
					newStack.data.push(new VarNumber(i));
				}
			}else{
				for (int i = 1; i <= varNum.data.intValue(); i++){
					newStack.data.push(new VarNumber(i));
				}
			}
			interpreter.reg.push(newStack);
		}
		if(var instanceof VarCallable){
			Var num = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
			VarCallable func = (VarCallable) var;
			if(num instanceof VarNumber){
				VarNumber varNum = (VarNumber) num;
				VarStack newStack = new VarStack();
				for (int i = 1; i <= varNum.data.intValue(); i++){
					interpreter.reg.push(new VarNumber(i));
					((VarCallable) func).Call(interpreter, scope);
					if(!interpreter.reg.isEmpty()){
						newStack.data.push(interpreter.reg.pop());
					}
				}
				interpreter.reg.push(newStack);
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Convert the top of the reg stack to a stack.";
	}

}
