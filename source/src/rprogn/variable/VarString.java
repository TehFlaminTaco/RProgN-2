package rprogn.variable;

public class VarString extends Var {
	public String data;
	
	public VarString(){
		
	}
	
	public VarString(String data){
		this.data = data;
	}
	
	@Override
	public String toString(){
		return data;
	}
}
