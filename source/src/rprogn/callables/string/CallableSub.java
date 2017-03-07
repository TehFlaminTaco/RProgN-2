package rprogn.callables.string;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableSub implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		Var c;
		if (b instanceof VarNumber){
			c = interpreter.pop();
		}else{
			c = b;
			b = a;
			a = new VarNumber(((VarNumber) a).data.add(BigDecimal.ONE));
		}
		
		if(c==null || !(b instanceof VarNumber) || !(a instanceof VarNumber)){return;}
		
		VarNumber start = (VarNumber) b;
		VarNumber end = (VarNumber) a;
		
		if (c instanceof VarString){
			VarString str = (VarString) c;
			
			interpreter.push(new VarString(str.data.substring(start.data.intValue(), end.data.intValue())));
		}
		if (c instanceof VarStack){
			VarStack stack = (VarStack) c;
			
			interpreter.push(
					new VarStack(
							stack.subList(start.data.intValue(), end.data.intValue())
							));
		}
		
	}

	@Override
	public String describe() {
		return "Get the elements or characters between two numbers, or at one number.";
	}

}
