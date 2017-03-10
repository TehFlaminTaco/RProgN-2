package rprogn.callables.arithmetic;

import java.math.BigInteger;
import java.util.HashMap;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableIsPrime implements Callable {

	private static BigInteger TWO = BigInteger.valueOf(2);
	
	private static HashMap<BigInteger, Boolean> isprimes = new HashMap<BigInteger, Boolean>();
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if (var instanceof VarNumber){
			BigInteger n = ((VarNumber) var).data.toBigInteger();
			
			Boolean isprime_stored = isprimes.get(n);
			if(isprime_stored!=null){
				interpreter.push(isprime_stored ? 1 : 0);
				return;
			}
			
			if(n.compareTo(BigInteger.ONE)<=0){
				interpreter.push(0);
				return;
			}
			
			if(n.equals(TWO)){
				interpreter.push(1);
				return;
			}
			
			for(BigInteger i=TWO; i.multiply(i).compareTo(n)<=0; i=i.add(BigInteger.ONE)){
				if(BigInteger.ZERO.equals(n.mod(i))){
					isprimes.put(n, false);
					interpreter.push(0);
					return;
				}
			}
			isprimes.put(n, true);
			interpreter.push(1);
		}
	}

	@Override
	public String describe() {
		return "Determine if a number is prime";
	}

}
