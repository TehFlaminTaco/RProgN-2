package rprogn.variable;

import java.util.List;
import java.util.Stack;

public class VarStack extends Var {
	public Stack<Var> data;
	
	public VarStack(){
		data = new Stack<Var>();
	}
	
	public VarStack(Stack<Var> data){
		this.data = data;
	}
	
	public VarStack(List<Var> data){
		Stack<Var> stack = new Stack<Var>();
		for(int i=0; i<data.size(); i++){
			stack.push(data.get(i));
		}
		this.data = stack;
	}
	
	public boolean truthy(){
		return !data.isEmpty();
	}
	
	public String toString(){
		String s = "{";
		for (int i = 0; i < data.size(); i++){
			s = s + data.get(i).toString() + ((i+1)<data.size() ? "," : "");
		}
		return s + "}";
	}
	
	public String type(){
		return "stack";
	}
}
