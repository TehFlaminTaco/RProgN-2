package rprogn.callables.arithmetic;


import rprogn.callables.Callable;
import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class CallablePlus implements Callable {

	@Override
	public int Call(Interpreter interpreter, Functions scope) {
		Var a = interpreter.reg.pop();
		Var b = interpreter.reg.pop();
		
		if(a instanceof VarString || b instanceof VarString){
			interpreter.reg.push(new VarString(b.toString()+a.toString()));
			return -1;
		}
		
		if(a instanceof VarNumber){
			if(b instanceof VarNumber){
				interpreter.reg.push(new VarNumber(((VarNumber) a).data + ((VarNumber) b).data));
				return -1;
			}
		}
		return -1;
	}

	@Override
	public String describe(){
		return "Pop the top two values from the active stack, Push the values added together to the active stack.";
	}

}
