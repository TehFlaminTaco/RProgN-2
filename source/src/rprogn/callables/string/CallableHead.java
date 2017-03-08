package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableHead implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var n = interpreter.pop();
		Var dat;
		if (n instanceof VarNumber){
			dat = interpreter.pop();
		}else{
			dat = n;
			n = new VarNumber(1);
		}
		
		if(dat instanceof VarString){
			VarString str = (VarString) dat;
			interpreter.push(str.data.substring(0, ((VarNumber)n).data.intValue()));
		}
		if(dat instanceof VarStack){
			VarStack stack = (VarStack) dat;
			interpreter.push(
					new VarStack(
							stack.subList(0, ((VarNumber)n).data.intValue())
							));
		}
	}

	@Override
	public String describe() {
		return "Get the first n characters or elements";
	}

}
