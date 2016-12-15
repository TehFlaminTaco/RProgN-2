package rprogn.functions;

import java.util.HashMap;

import rprogn.callables.*;
import rprogn.variable.VarNumber;

public class Functions {

	public static HashMap<String,Callable> default_functions = new HashMap<String,Callable>();
	public HashMap<String,Callable> custom_asoc = new HashMap<String,Callable>();
	public Callable get(String func) {
		Callable out=null;
		
		out = custom_asoc.get(func);
		if(out!=null){
			return out;
		}
		
		out = default_functions.get(func);
		if (out!=null){
			return out;
		}
		
		if(func.matches("\\d") && func.matches("^-?[\\d.]+$")){
			return new VarNumber(func).getCallable();
		}
		
		return null;
	}
	
	public static void setDefaults(){
		newDefault(new CallablePrint(), "print", "p");
	}
	
	public static void newDefault(Callable call, String... name){
		for(int i=0; i<name.length; i++){
			default_functions.put(name[i], call);
		}
	}
	
}
