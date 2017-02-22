package rprogn.callables.stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableRange implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var vart = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var varb = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		if(vart!=null){
			if(varb == null){
				if(vart instanceof VarNumber){
					varb = new VarNumber(1);
				}else if(vart instanceof VarString){
					varb = new VarString(String.valueOf((char) 0));
				}
			}
			if(varb != null){
				if(vart instanceof VarNumber && varb instanceof VarNumber){
					VarStack newStack = new VarStack();
					int a = Math.min((int) ((VarNumber) varb).data, (int) ((VarNumber) vart).data);
					int b = Math.max((int) ((VarNumber) varb).data, (int) ((VarNumber) vart).data);
					for (int i = a; i <= b; i++){
						newStack.data.push(new VarNumber(i));
					}
					if (b==((int) ((VarNumber) varb).data)){
						VarStack reverseStack = new VarStack();
						while (!newStack.data.isEmpty()){
							reverseStack.data.push(newStack.data.pop());
						}
						newStack = reverseStack;
					}
					interpreter.reg.push(newStack);
				}
				if(varb instanceof VarString && varb.truthy() && vart instanceof VarString && vart.truthy()){ // Strings are truthy if they have more than 1 char, very useful here.
					VarStack newStack = new VarStack();
					int a = Math.min((int) ((VarString) varb).data.charAt(0), (int) ((VarString) vart).data.charAt(0));
					int b = Math.max((int) ((VarString) varb).data.charAt(0), (int) ((VarString) vart).data.charAt(0));
					for (int i = a; i <= b; i++){
						newStack.data.push(new VarString(String.valueOf((char) i)));
					}
					interpreter.reg.push(newStack);
				}
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Takes two things, makes a stack between them.";
	}

}
