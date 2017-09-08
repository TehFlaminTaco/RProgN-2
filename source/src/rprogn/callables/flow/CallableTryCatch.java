package rprogn.callables.flow;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;

public class CallableTryCatch implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var vCatch = interpreter.pop();
		Var vTry = interpreter.pop();
		
		if(vCatch == null)
			return;
		
		if(vTry == null){
			interpreter.push(vCatch);
			return;
		}
		
		Callable cCatch = vCatch.getCallable();
		Callable cTry = vTry.getCallable();
		
		try{
			cTry.Call(interpreter, scope);
		}catch(Exception e){
			cCatch.Call(interpreter, scope);
		}

	}

	@Override
	public String describe() {
		// TODO Auto-generated method stub
		return null;
	}

}
