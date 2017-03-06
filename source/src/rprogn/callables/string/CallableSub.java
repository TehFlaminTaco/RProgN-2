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
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		Var b = interpreter.pop();
		Var c;
		if (b instanceof VarNumber){
			c = interpreter.pop();
		}else{
			c = b;
			b = a;
			a = new VarNumber(((VarNumber) a).data.add(new BigDecimal(1)));
		}
		
		if(c==null || !(b instanceof VarNumber) || !(a instanceof VarNumber)){return -1;}
		
		VarNumber start = (VarNumber) b;
		VarNumber end = (VarNumber) a;
		
		if (c instanceof VarString){
			VarString str = (VarString) c;
			
			interpreter.reg.push(new VarString(str.data.substring(start.data.intValue(), end.data.intValue())));
		}
		if (c instanceof VarStack){
			VarStack stack = (VarStack) c;
			
			interpreter.reg.push(
					new VarStack(
							stack.data.subList(start.data.intValue(), end.data.intValue())
							));
		}
		
		return -1;
	}

	@Override
	public String describe() {
		return "Get the elements or characters between two numbers, or at one number.";
	}

}
