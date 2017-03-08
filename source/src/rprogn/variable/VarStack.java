package rprogn.variable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Stack;

public class VarStack extends Stack<Var> implements Var {
	private static final long serialVersionUID = -6877596378024718431L;
	
	public VarStack(){
	}
	
	public VarStack(Stack<Var> data){
		for(int i=0; i<data.size(); i++){
			this.push(data.get(i));
		}
	}
	
	public VarStack(List<Var> data){
		Stack<Var> stack = new Stack<Var>();
		for(int i=0; i<data.size(); i++){
			stack.push(data.get(i));
		}
		for(int i=0; i<stack.size(); i++){
			this.push(stack.get(i));
		}
	}
	
	public boolean truthy(){
		return !isEmpty();
	}
	
	public String toString(){
		String s = "{";
		for (int i = 0; i < size(); i++){
			s = s + get(i).toString() + ((i+1)<size() ? "," : "");
		}
		return s + "}";
	}
	
	public String type(){
		return "stack";
	}
	
	public void push(String str){
		push(new VarString(str));
	}
	
	public void push(Double dbl){
		push(new VarNumber(dbl));
	}
	
	public void push(int i){
		push(new VarNumber(i));
	}
	
	public void push(BigInteger bi){
		push(new VarNumber(new BigDecimal(bi)));
	}
	
	public void push(BigDecimal bd){
		push(new VarNumber(bd));
	}
	
	public void push(Stack<Var> stack){
		push((Var)new VarStack(stack));
	}
}
