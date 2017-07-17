package rprogn.callables.string;

import java.util.Base64;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarString;

public class CallableBase64Decode implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var v = interpreter.pop();
		if(v == null){
			v = new VarString();
		}
		interpreter.push(new String(Base64.getDecoder().decode(v.toString().getBytes())));
	}

	@Override
	public String describe() {
		return "Decode a string from Base64.";
	}

}
