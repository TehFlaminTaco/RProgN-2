package rprogn.callables.arithmetic;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

import rprogn.callables.Callable;
import rprogn.callables.stack.CallableSort;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableFactorise implements Callable {
	
	private static BigInteger TWO = BigInteger.valueOf(2);
	private static BigInteger THREE = BigInteger.valueOf(3);
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var var = interpreter.pop();
		if (var instanceof VarNumber){
			BigInteger n = ((VarNumber)var).data.toBigInteger();
			Stack<Var> factors = new Stack<Var>();
			
			if(n.compareTo(BigInteger.ZERO)<0){
				factors.push(new VarNumber(-1));
				n = n.negate();
			}
			
			if(n.equals(BigInteger.ZERO)){
				factors.push(new VarNumber(0));
			}else if(n.equals(BigInteger.ONE)){
				factors.push(new VarNumber(1));
			}else if(n.equals(TWO)){
				factors.push(new VarNumber(1));
				factors.push(new VarNumber(2));
			}else{
				factors.push(new VarNumber(1));
				factors.push(new VarNumber(new BigDecimal(n)));
				if(n.mod(TWO).equals(BigInteger.ZERO)){
					factors.push(new VarNumber(2));
					factors.push(new VarNumber(new BigDecimal(n.divide(TWO))));
				}
				for(BigInteger i = THREE; i.multiply(i).compareTo(n)<=0; i=i.add(BigInteger.ONE)){
					if(BigInteger.ZERO.equals(n.mod(i))){
						factors.push(new VarNumber(new BigDecimal(i)));
						if(i.multiply(i).compareTo(n)<0)
							factors.push(new VarNumber(new BigDecimal(n.divide(i))));
					}
				}
			}
			interpreter.push((Var)CallableSort.sort(interpreter, scope, factors));
		}
	}

	@Override
	public String describe() {
		return "Get all the factors of a number as a stack.";
	}

}
