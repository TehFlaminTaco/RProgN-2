package rprogn.callables.string;

import java.math.BigDecimal;
import java.math.BigInteger;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallableBase implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var base = interpreter.pop();
		Var target = interpreter.pop();
		if (base instanceof VarNumber){
			VarNumber nBase = (VarNumber) base;
			if(nBase.data.compareTo(new BigDecimal(0)) > 0){
				if (target instanceof VarNumber){
					VarNumber nTarget = (VarNumber) target;
					
					interpreter.push(nTarget.data.toBigInteger().toString(nBase.data.intValue()));
				}
			}else if(nBase.data.compareTo(new BigDecimal(0)) < 0){
				if(target instanceof VarString){
					VarString sTarget = (VarString) target;
					
					interpreter.push(new BigDecimal(new BigInteger(sTarget.data, nBase.data.intValue())));
				}
			}
		}
	}

	@Override
	public String describe() {
		return "Convert a number to a string of base n.";
	}

}
