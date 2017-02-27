package rprogn.callables.arithmetic;

import java.math.BigDecimal;

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
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarString && b instanceof VarNumber){
			interpreter.reg.push(new VarString(new String(new char[((VarNumber)b).data.intValue()]).replace("\0", ((VarString)a).data)));
			return -1;
		}
		
		if(b instanceof VarString && a instanceof VarNumber){
			interpreter.reg.push(new VarString(new String(new char[((VarNumber)a).data.intValue()]).replace("\0", ((VarString)b).data)));
			return -1;
		}
		
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.reg.push(new VarNumber(((VarNumber)b).data.multiply(((VarNumber)a).data)));
			return -1;
		}
		
		if(b instanceof VarNumber && a instanceof VarCallable){
			for (BigDecimal i=new BigDecimal(1); i.compareTo(((VarNumber)b).data)<=0; i=i.add(new BigDecimal(1))){
				((Callable)a).Call(interpreter, scope);
			}
		}
		
		if(a instanceof VarNumber && b instanceof VarCallable){
			for (BigDecimal i=new BigDecimal(1); i.compareTo(((VarNumber)b).data)<=0; i=i.add(new BigDecimal(1))){
				((Callable)b).Call(interpreter, scope);
			}
		}
		
		return -1;
	}

	@Override
	public String describe() {
		
		return "Pop two values from the stack, multiply them together and push to the stack.";
	}

}
