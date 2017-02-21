package rprogn.variable;

public class VarNumber extends Var {
	public double data;
	
	public VarNumber(){
		
	}
	
	public VarNumber(double data){
		this.data = data;
	}
	
	public VarNumber(String data){
		this.data = Double.parseDouble(data);
	}
	
	public String toString(){
		if((int)data == data){
			return String.valueOf((int)data);
		}
		
		return String.valueOf(data);
	}

	public boolean truthy(){
		return data!=0;
	}
}
