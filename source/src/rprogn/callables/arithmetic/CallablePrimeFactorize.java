package rprogn.callables.arithmetic;

import java.math.BigDecimal;
import java.util.Stack;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;

public class CallablePrimeFactorize implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(a==null){
			return;
		}
		VarCallable action = null;
		if(a instanceof VarCallable){
			Var b = interpreter.pop();
			if(b==null || !(b instanceof VarNumber)){
				if(b!=null){
					interpreter.push(b);
				}
				interpreter.push(a);
				return;
			}
			action = (VarCallable)a;
			a = b;
		}
		if(!(a instanceof VarNumber)){
			interpreter.push(a);
			return;
		}
		BigDecimal n = ((VarNumber)a).data;
		interpreter.push(primeFactors(interpreter, scope, n, action));
	}

	@Override
	public String describe() {
		return "Return a stack of the prime factors of N";
	}
	
	private Stack<Var> primeFactors(Interpreter i, Scope scope, BigDecimal n, VarCallable a){
		return primeFactors(i, scope, n, a, new BigDecimal(2), new Stack<Var>());
	}
	
	private Stack<Var> primeFactors(Interpreter interpreter, Scope scope, BigDecimal n, VarCallable a, BigDecimal i, Stack<Var> s){
		if(i.compareTo(n)>0){
			return s;
		}
		BigDecimal[] divrem = n.divideAndRemainder(i);
		if(divrem[1].compareTo(BigDecimal.ZERO) == 0){
			if(a!=null){
				interpreter.push(new VarNumber(i));
				a.Call(interpreter, scope);
				s.push(interpreter.pop());
			}else{
				s.push(new VarNumber(i));
			}
			return primeFactors(interpreter, scope, divrem[0], a, i, s);
		}else{
			return primeFactors(interpreter, scope, n, a, i.add(BigDecimal.ONE), s);
		}
	}

}
