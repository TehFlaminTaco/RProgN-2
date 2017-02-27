package rprogn.callables.stack;

import java.math.BigDecimal;

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
		Var vart = interpreter.pop();
		Var varb = interpreter.pop();
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
					BigDecimal a = ((VarNumber) varb).data.max(((VarNumber) vart).data);
					BigDecimal b = ((VarNumber) varb).data.min(((VarNumber) vart).data);
					
					for (BigDecimal i = b; i.compareTo(a)<=0; i=i.add(new BigDecimal(1))){
						newStack.data.push(new VarNumber(i));
					}
					if ((int) ((VarNumber) varb).data.compareTo(b)==0){
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