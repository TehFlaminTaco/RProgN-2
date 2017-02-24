package rprogn.variable;

import java.math.BigDecimal;

public class VarNumber extends Var {
	public BigDecimal data;
	
	public VarNumber(){
		
	}
	
	public VarNumber(double data){
		this.data = new BigDecimal(data);
	}
	
	public VarNumber(BigDecimal data){
		this.data = data;
	}
	
	public VarNumber(String data){
		this.data = new BigDecimal(data);
	}
	
	public VarNumber(Var var) {
		if (var instanceof VarString){
			this.data = new BigDecimal(((VarString) var).data);
		}
		if (var instanceof VarNumber){
			this.data = ((VarNumber) var).data;
		}
	}

	public String toString(){
		return String.valueOf(data);
	}

	public boolean truthy(){
		return data.compareTo(new BigDecimal(0))!=0;
	}
}
