package rprogn.functions;

import java.util.HashMap;

import rprogn.Flags;
import rprogn.callables.*;
import rprogn.callables.arithmetic.CallableDivide;
import rprogn.callables.arithmetic.CallableIntDivide;
import rprogn.callables.arithmetic.CallableMultiply;
import rprogn.callables.arithmetic.CallablePlus;
import rprogn.callables.arithmetic.CallablePower;
import rprogn.callables.arithmetic.CallableSubtract;
import rprogn.callables.flow.CallableEnd;
import rprogn.callables.flow.CallableFunc;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class Functions {

	// Below is where you put new default functions! :D
	
	public static void setDefaults(){
		
		// Generic Functions
		newDefault(new CallablePrint(), "print", "p");
		
		// Arithmetic
		newDefault(new CallablePlus(), "+");
		newDefault(new CallableSubtract(), "-");
		newDefault(new CallableMultiply(), "*");
		newDefault(new CallableDivide(), "/");
		newDefault(new CallableIntDivide(), "//");
		newDefault(new CallablePower(), "^");
		
		// Flow of Control
		newDefault(new CallableFunc(), "function", "{");
		newDefault(new CallableEnd(), "end", "}");
		
	}
	
	
	
	
	// Below is boring under the hood stuff.
	
	
	public static HashMap<String,Callable> default_functions = new HashMap<String,Callable>();
	public HashMap<String,Callable> custom_asoc = new HashMap<String,Callable>();
	public Functions parent = null;
	public Callable get(String func) {
		Callable out=null;
		
		out = custom_asoc.get(func);
		if(out!=null){
			return out;
		}
		
		if(parent!=null){
			out = parent.get(func);
			if (out!=null){
				return out;
			}
		}
		
		out = default_functions.get(func);
		if (out!=null){
			return out;
		}
		
		if(func.matches(".*\\d.*") && func.matches("^-?[\\d.]+$")){
			return new VarNumber(func).getCallable();
		}
		
		if(func.substring(0,1).equals("`")){
			return new VarString(func.substring(1)).getCallable();
		}
		
		if(Flags.FlagToggled("z")){
			
		}
		
		return null;
	}
	
	public static void newDefault(Callable call, String... name){
		for(int i=0; i<name.length; i++){
			default_functions.put(name[i], call);
		}
	}
	
}
