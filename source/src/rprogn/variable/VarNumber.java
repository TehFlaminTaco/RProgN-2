package rprogn.variable;

import java.math.BigDecimal;

public class VarNumber implements  Var {
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
		try{
			this.data = new BigDecimal(data);
		}catch (Exception e){
			
		}
	}
	
	public VarNumber(Var var) {
		if (var instanceof VarString){
			try{
				this.data = new BigDecimal(((VarString) var).data);
			}catch(Exception e){
				// Literally just try it. Do nothing if we fail.
				
				// Is there a correct way to do this?
			}
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
	
	public String type(){
		return "number";
	}
}
