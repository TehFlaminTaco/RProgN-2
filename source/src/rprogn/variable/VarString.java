package rprogn.variable;

import java.math.BigDecimal;

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
	
	public boolean truthy(){
		return data.length()>0;
	}
	
	public String type(){
		return "string";
	}
	
	public VarString repeat(BigDecimal n){
		String s = "";
		while((n=n.subtract(new BigDecimal(1))).compareTo(new BigDecimal(0))>-1){
			s += this.data;
		}
		return new VarString(s);
	}
}
