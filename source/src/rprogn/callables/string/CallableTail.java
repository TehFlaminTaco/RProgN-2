package rprogn.callables.string;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableTail implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
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
			interpreter.reg.push(new VarString(str.data.substring(str.data.length()-((VarNumber)n).data.intValue())));
		}
		if(dat instanceof VarStack){
			VarStack stack = (VarStack) dat;
			interpreter.reg.push(
					new VarStack(
							stack.data.subList(stack.data.size()-((VarNumber)n).data.intValue(), stack.data.size())
							));
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Get the last n characters or elements";
	}

}
