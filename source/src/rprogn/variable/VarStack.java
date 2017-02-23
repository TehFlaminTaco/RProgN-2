package rprogn.variable;

import java.util.Stack;

public class VarStack extends Var {
	public Stack<Var> data;
	
	public VarStack(){
		data = new Stack<Var>();
	}
	
	public VarStack(Stack<Var> data){
		this.data = data;
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
}
