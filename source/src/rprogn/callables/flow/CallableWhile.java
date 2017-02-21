package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableWhile implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		if(func!=null){
			while(!interpreter.reg.isEmpty() && interpreter.reg.pop().truthy()){
				func.getCallable().Call(interpreter, scope);
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Repeat the function upon the top of the stack whilst the top of the stack is truthy. Does initial pop as well.";
	}
	
}
