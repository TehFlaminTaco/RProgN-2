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
	public int Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if (a instanceof VarCallable){
			Var b = interpreter.pop();
			if (b instanceof VarStack){
				VarStack stack = (VarStack)b;
				interpreter.reg.push(sort(interpreter,scope,stack.data,a.getCallable()));
			}
		}else if(a instanceof VarStack){
			VarStack stack = (VarStack)a;
			interpreter.reg.push(sort(interpreter,scope,stack.data));
		}
		return -1;
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
		Stack<Var> l = new Stack<Var>();
		Stack<Var> r = new Stack<Var>();
		int pivot = (int) stack.size()/2;
		Var p = stack.get(pivot);
		
		for(int i=0;i<stack.size();i++){
			if(i!=pivot){
				interpreter.reg.push(stack.get(i));
				interpreter.reg.push(p);
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
		
		if(l.size()>1){
			l = sort(interpreter,scope,l,func).data;
		}
		if(r.size()>1){
			r = sort(interpreter,scope,r,func).data;
		}
		
		l.push(p);
		l.addAll(r);
		return new VarStack(l);
	}
	
}
