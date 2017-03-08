package rprogn.callables.misc;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.interpreter.Parser;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.compiler.Compiler;

public class CallableCall implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.pop();
		if(func instanceof VarCallable){
			func.getCallable().Call(interpreter, scope);
			return;
		}
		if(func!=null){
			Parser.parse(Compiler.compile(func.toString()),interpreter,scope.functions);
		}
	}

	@Override
	public String describe() {
		return "Call the function sitting on the top of the stack. If it's a string, try and run that.";
	}

}
