package rprogn.functions;

import java.util.HashMap;

import rprogn.Flags;
import rprogn.callables.Callable;
import rprogn.callables.CallablePrint;
import rprogn.callables.CallablePushVar;
import rprogn.callables.CallableWrite;
import rprogn.callables.arithmetic.CallableConcat;
import rprogn.callables.arithmetic.CallableDivide;
import rprogn.callables.arithmetic.CallableIntDivide;
import rprogn.callables.arithmetic.CallableModulos;
import rprogn.callables.arithmetic.CallableMultiply;
import rprogn.callables.arithmetic.CallablePlus;
import rprogn.callables.arithmetic.CallablePower;
import rprogn.callables.arithmetic.CallableSubtract;
import rprogn.callables.arithmetic.CallableToNumber;
import rprogn.callables.flow.CallableEnd;
import rprogn.callables.flow.CallableFor;
import rprogn.callables.flow.CallableFunc;
import rprogn.callables.flow.CallableIf;
import rprogn.callables.flow.CallableWhile;
import rprogn.callables.misc.CallableAsoc;
import rprogn.callables.misc.CallableCall;
import rprogn.callables.misc.CallableLen;
import rprogn.callables.misc.CallableLocalAsoc;
import rprogn.callables.reg.CallableDuplicate;
import rprogn.callables.reg.CallableRegPop;
import rprogn.callables.reg.CallableRegSwap;
import rprogn.callables.stack.CallableEndStack;
import rprogn.callables.stack.CallableNewStack;
import rprogn.callables.stack.CallableRange;
import rprogn.callables.stack.CallableStartStack;
import rprogn.callables.stack.CallableToStack;
import rprogn.variable.VarCallable;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class Functions {

	// Below is where you put new default functions! :D
	
	public static void setDefaults(){
		
		// Generic Functions
		newDefault(new CallablePrint(), "print", "p");
		newDefault(new CallableWrite(), "write", "w");
		
		// Arithmetic
		newDefault(new CallablePlus(), "+");
		newDefault(new CallableSubtract(), "-");
		newDefault(new CallableMultiply(), "*");
		newDefault(new CallableDivide(), "/");
		newDefault(new CallableIntDivide(), "//", "÷");
		newDefault(new CallablePower(), "^");
		newDefault(new CallableModulos(), "%");
		newDefault(new CallableConcat(), ".");
		newDefault(new CallableToNumber(), "n");
		
		// Reg Manipulation
		newDefault(new CallableDuplicate(), "]");
		newDefault(new CallableRegPop(), "[");
		newDefault(new CallableRegSwap(), "\\");
		
		// Flow of Control
		newDefault(new CallableFunc(), "function", "{");
		newDefault(new CallableEnd(), "end", "}");
		newDefault(new CallableIf(), "if", "?");
		newDefault(new CallableWhile(), "while", ":");
		newDefault(new CallableFor(), "for", ";");
		
		// Stacks
		newDefault(new CallableNewStack(), "stack", "s");
		newDefault(new CallableToStack(), "tostack", "S");
		newDefault(new CallableRange(), "range", "R");
		newDefault(new CallableStartStack(), "(");
		newDefault(new CallableEndStack(), ")");
		
		// Misc
		newDefault(new CallableCall(), "call","C");
		newDefault(new CallableAsoc(), "asoc","=");
		newDefault(new CallableLocalAsoc(), "@");
		newDefault(new CallableLen(), "len", "L");
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
		
		if (func.substring(0,1).equals("#")){
			
			return new CallablePushVar(new VarCallable(get(func.substring(1)))); // This horrific thing gets everything after a #, and pushes the function it represents.
		}
		
		if (func.substring(0,1).equals("$")){
			return new VarNumber(func.substring(1)).getCallable();
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
