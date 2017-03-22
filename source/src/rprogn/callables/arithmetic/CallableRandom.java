package rprogn.callables.arithmetic;

import java.math.BigDecimal;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;

public class CallableRandom implements Callable {

	private static BigDecimal BYTE_SIZE = BigDecimal.valueOf(256);
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var a = interpreter.pop();
		if(!(a instanceof VarNumber)){
			
			if(a instanceof VarStack){
				VarStack clone = (VarStack) ((VarStack) a).clone();
				VarStack newStack = new VarStack();
				while(clone.size()>0){
					newStack.push(clone.remove(Math.abs(interpreter.rng.nextInt())%clone.size()));
				}
				interpreter.push(newStack);
				return;
			}
			
			if(a!=null){interpreter.push(a);};
			a = new VarNumber(BigDecimal.TEN);
		}
		Var b = interpreter.pop();
		if(!(b instanceof VarNumber)){
			if(b!=null){interpreter.push(b);};
			b = new VarNumber(BigDecimal.ZERO);
		}
		VarNumber aN = (VarNumber)a;
		VarNumber bN = (VarNumber)b;
		
		BigDecimal dif = aN.data.subtract(bN.data);
		
		// Determine log(256, difference), which shows how many bytes we need.
		byte[] bytes = new byte[0];
		for (int i=0; i<=dif.intValue(); i++){
			if(BYTE_SIZE.pow(i).compareTo(dif)>=0){
				bytes = new byte[i+1];
				break;
			}
		}
		
		// Randomize the byte table
		interpreter.rng.nextBytes(bytes);
		
		// Convert from Base256 to BigDecimal
		BigDecimal out = BigDecimal.ZERO;
		for(int i=0; i<bytes.length; i++){
			out = out.multiply(BYTE_SIZE).add(new BigDecimal(bytes[i]+127));
		}
		
		// Mod%Difference, because the max value of the output can be much larger than the difference itself.
		out = out.remainder(dif.add(BigDecimal.ONE));
		
		// Add the lower value back to it.
		out = out.add(bN.data);
		
		// Output.
		interpreter.push(out);
		
	}

	@Override
	public String describe() {
		return "Produce a random number. Optionally up to one number, or between two. If the top of the stack is a stack, shuffle it.";
	}
	
}
