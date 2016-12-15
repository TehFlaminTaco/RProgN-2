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
}
