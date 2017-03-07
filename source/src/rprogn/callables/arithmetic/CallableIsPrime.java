package rprogn.callables.arithmetic;

import java.math.BigInteger;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableIsPrime implements Callable {

	private static BigInteger TWO = BigInteger.valueOf(2);
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if (var instanceof VarNumber){
			BigInteger n = ((VarNumber) var).data.toBigInteger();
			if(n.compareTo(BigInteger.ONE)<=0){
				interpreter.reg.push(new VarNumber(0));
				return;
			}
			
			if(n.equals(TWO)){
				interpreter.reg.push(new VarNumber(1));
				return;
			}
			
			for(BigInteger i=TWO; i.multiply(i).compareTo(n)<=0; i=i.add(BigInteger.ONE)){
				if(BigInteger.ZERO.equals(n.mod(i))){
					interpreter.reg.push(new VarNumber(0));
					return;
				}
			}
			
			interpreter.reg.push(new VarNumber(1));
		}
	}

	@Override
	public String describe() {
		return "Determine if a number is prime";
	}

}
