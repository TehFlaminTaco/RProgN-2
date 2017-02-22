package rprogn.callables.arithmetic;

import rprogn.callable.tacted.CallableRepeated;
import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallableMultiply implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var b = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		
		if(a instanceof VarString && b instanceof VarNumber){
			interpreter.reg.push(new VarString(new String(new char[(int)((VarNumber)b).data]).replace("\0", ((VarString)a).data)));
			return -1;
		}
		
		if(b instanceof VarString && a instanceof VarNumber){
			interpreter.reg.push(new VarString(new String(new char[(int)((VarNumber)a).data]).replace("\0", ((VarString)b).data)));
			return -1;
		}
		
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.reg.push(new VarNumber(((VarNumber)b).data * ((VarNumber)a).data));
			return -1;
		}
		
		if(b instanceof VarNumber && a instanceof VarCallable){
			CallableRepeated repCall = new CallableRepeated();
			repCall.count = (int) ((VarNumber) b).data;
			repCall.parent = a.getCallable();
			interpreter.reg.push(new VarCallable(repCall));
		}
		
		if(a instanceof VarNumber && b instanceof VarCallable){
			CallableRepeated repCall = new CallableRepeated();
			repCall.count = (int) ((VarNumber) a).data;
			repCall.parent = b.getCallable();
			interpreter.reg.push(new VarCallable(repCall));
		}
		
		return -1;
	}

	@Override
	public String describe() {
		
		return "Pop two values from the stack, multiply them together and push to the stack.";
	}

}
