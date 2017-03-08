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
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		if(a instanceof VarString && b instanceof VarNumber){
			interpreter.push(((VarString)a).repeat(((VarNumber)b).data));
			return;
		}
		
		if(b instanceof VarString && a instanceof VarNumber){
			interpreter.push(((VarString)b).repeat(((VarNumber)a).data));
			return;
		}
		
		
		if(a instanceof VarNumber && b instanceof VarNumber){
			interpreter.push(((VarNumber)b).data.multiply(((VarNumber)a).data));
			return;
		}
		
		if(b instanceof VarNumber && a instanceof VarCallable){
			for (BigDecimal i=BigDecimal.ONE; i.compareTo(((VarNumber)b).data)<=0; i=i.add(BigDecimal.ONE)){
				((Callable)a).Call(interpreter, scope);
			}
		}
		
		if(a instanceof VarNumber && b instanceof VarCallable){
			for (BigDecimal i=BigDecimal.ONE; i.compareTo(((VarNumber)b).data)<=0; i=i.add(BigDecimal.ONE)){
				((Callable)b).Call(interpreter, scope);
			}
		}
		
	}

	@Override
	public String describe() {
		
		return "Pop two values from the stack, multiply them together and push to the stack.";
	}

}
