package rprogn.callables.bitwiseoperations;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableBitwiseOr implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		
		BigDecimal iA;
		BigDecimal iB;
		
		if(a instanceof VarNumber){
			iA = ((VarNumber) a).data;
		}else{
			iA = new BigDecimal(a!=null&&a.truthy() ? 1 : 0);
		}
		
		if(b instanceof VarNumber){
			iB = ((VarNumber) b).data;
		}else{
			iB = new BigDecimal(b!=null&&b.truthy() ? 1 : 0);
		}
		interpreter.push(new BigDecimal(iA.toBigInteger().or(iB.toBigInteger())));
	}

	@Override
	public String describe() {
		return "Get the Bitwise Or of two numbers. If they're not numbers, get their Truthyness first.";
	}
	
}
