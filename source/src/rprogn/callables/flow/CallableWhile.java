package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableWhile implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.pop();
		if(func!=null){
			while(!interpreter.reg.isEmpty() && interpreter.reg.pop().truthy()){
				func.getCallable().Call(interpreter, scope);
			}
		}
	}

	@Override
	public String describe() {
		return "Repeat the function upon the top of the stack whilst the top of the stack is truthy. Does initial pop as well.";
	}
	
}
