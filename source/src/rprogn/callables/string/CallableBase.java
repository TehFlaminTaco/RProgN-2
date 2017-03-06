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
	public int Call(Interpreter interpreter, Scope scope) {
		Var base = interpreter.pop();
		Var target = interpreter.pop();
		if (base instanceof VarNumber){
			VarNumber nBase = (VarNumber) base;
			if(nBase.data.compareTo(new BigDecimal(0)) > 0){
				if (target instanceof VarNumber){
					VarNumber nTarget = (VarNumber) target;
					
					interpreter.reg.push(new VarString(nTarget.data.toBigInteger().toString(nBase.data.intValue())));
				}
			}else if(nBase.data.compareTo(new BigDecimal(0)) < 0){
				if(target instanceof VarString){
					VarString sTarget = (VarString) target;
					
					interpreter.reg.push(new VarNumber(new BigDecimal(new BigInteger(sTarget.data, nBase.data.intValue()))));
				}
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Convert a number to a string of base n.";
	}

}