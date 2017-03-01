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
	public int Call(Interpreter interpreter, Scope scope) {
		Var func = interpreter.pop();
		if(func instanceof VarCallable){
			return func.getCallable().Call(interpreter, scope);
		}
		if(func!=null){
			Parser.parse(Compiler.compile(func.toString()),interpreter,scope.functions);
			
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Call the function sitting on the top of the stack. If it's a string, try and run that.";
	}

}
