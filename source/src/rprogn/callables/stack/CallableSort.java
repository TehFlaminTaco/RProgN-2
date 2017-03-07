package rprogn.callables.stack;

import java.util.Stack;

import rprogn.callables.Callable;
import rprogn.callables.logic.CallableLessThan;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;

public class CallableSort implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if (a instanceof VarCallable){
			Var b = interpreter.pop();
			if (b instanceof VarStack){
				VarStack stack = (VarStack)b;
				interpreter.push(sort(interpreter,scope,stack,a.getCallable()));
			}
		}else if(a instanceof VarStack){
			VarStack stack = (VarStack)a;
			interpreter.push(sort(interpreter,scope,stack));
		}
	}

	@Override
	public String describe() {
		return "Sort a stack by an optional function.";
	}
	
	// Quicksort Down here...
	public static VarStack sort(Interpreter interpreter, Scope scope, Stack<Var> stack){
		return sort(interpreter, scope, stack, new CallableLessThan());
	}
	
	public static VarStack sort(Interpreter interpreter, Scope scope, Stack<Var> stack, Callable func){
		if(stack.size()<=1){
			return new VarStack(stack);
		}
		Stack<Var> l = new Stack<Var>();
		Stack<Var> r = new Stack<Var>();
		int pivot = (int) stack.size()/2;
		Var p = stack.get(pivot);
		
		for(int i=0;i<stack.size();i++){
			if(i!=pivot){
				interpreter.push(stack.get(i));
				interpreter.push(p);
				func.Call(interpreter, scope);
				Var result = interpreter.pop();
				if(result!=null){
					if(result.truthy()){
						l.push(stack.get(i));
					}else{
						r.push(stack.get(i));
					}
				}
			}
		}
		
		l = sort(interpreter,scope,l,func);
		r = sort(interpreter,scope,r,func);

		l.push(p);
		l.addAll(r);
		return new VarStack(l);
	}
	
}
