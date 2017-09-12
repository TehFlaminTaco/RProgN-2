package rprogn.callables.string;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallablePadLeft implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var chr = interpreter.pop();
		Var count = interpreter.pop();
		Var string = interpreter.pop();
		if(chr == null)
			return;
		if(count == null){
			string = chr;
			count = new VarNumber(8);
			chr = new VarString("0");
		}
		if(string == null){
			string = count;
			count = new VarNumber(8);
		}
		if(!(chr instanceof VarString && count instanceof VarNumber && string instanceof VarString)){
			return;
		}
		
		VarString v_S = (VarString)string;
		VarNumber v_N = (VarNumber)count;
		VarString v_C = (VarString)chr;
		interpreter.push(v_C.repeat(v_N.data.subtract(new BigDecimal(v_S.data.length()))).data+v_S.data);
	}

	@Override
	public String describe() {
		return "Pads a string to N length with a particular character.";
	}
	
}
