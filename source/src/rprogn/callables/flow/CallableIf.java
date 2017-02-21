package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;

public class CallableIf implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var func_b = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var func_a = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		Var val;
		
		if(func_a instanceof VarCallable){
			val = interpreter.reg.isEmpty() ? null : interpreter.reg.pop();
		}else{
			val = func_a;
			func_a = func_b;
			func_b = null;
		}
		
		if(val!=null){
			if(val.truthy()){
				if(func_a!=null){
					func_a.getCallable().Call(interpreter, scope);
				}
			}else{
				if(func_b!=null){
					func_b.getCallable().Call(interpreter, scope);
				}
			}
		}
		
		return -1;
	}

	@Override
	public String describe() {
		return "Given the stack 'val, func_a, func_b', do func_a if val is truthy, or func_b otherwise. Given the stack 'val, func' however, func_a is ran if val is truthy, and nothing happens otherwise.";
	}

}
