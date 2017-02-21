package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallableMultiply implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.reg.pop();
		Var b = interpreter.reg.pop();
		
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
		
		return -1;
	}

	@Override
	public String describe() {
		
		return "Pop two values from the stack, multiply them together and push to the stack.";
	}

}
