package rprogn.callables.arithmetic;

import rprogn.callables.Callable;
import rprogn.callables.logic.CallableGreaterThan;
import rprogn.callables.stack.CallableSort;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarStack;

public class CallableMax implements Callable {

	CallableGreaterThan gt = new CallableGreaterThan();
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var b = interpreter.pop();
		if(b==null){return;}
		if(b instanceof VarStack){
			VarStack out = CallableSort.sort(interpreter, scope, (VarStack)b, gt);
			if(out.get(0)!=null){
				interpreter.push(out.get(0));
			}
		}else{
			Var a = interpreter.pop();
			if(a==null){interpreter.push(b);return;}
			interpreter.push(a);
			interpreter.push(b);
			gt.Call(interpreter, scope);
			Var out = interpreter.pop();
			if(out==null || !out.truthy()){
				interpreter.push(b);
			}else{
				interpreter.push(a);
			}
		}
	}

	@Override
	public String describe() {
		return "Of two values, return the largest. If the top value is a stack, return its largest value.";
	}

}
