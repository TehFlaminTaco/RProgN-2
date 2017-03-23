package rprogn.functions;

import java.util.HashMap;
import java.util.Map;

import rprogn.Flags;
import rprogn.callables.Callable;
import rprogn.callables.CallablePrint;
import rprogn.callables.CallablePushVar;
import rprogn.callables.CallableWrite;
import rprogn.callables.arithmetic.*;
import rprogn.callables.bitwiseoperations.*;
import rprogn.callables.constants.*;
import rprogn.callables.flow.*;
import rprogn.callables.logic.*;
import rprogn.callables.misc.*;
import rprogn.callables.reg.*;
import rprogn.callables.stack.*;
import rprogn.callables.string.*;
import rprogn.variable.Var;
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
		newDefault(new CallableSqrt(), "š");
		newDefault(new CallableFactorise(), "ƒ");
		newDefault(new CallableIsPrime(), "P");
		newDefault(new CallableMin(), "m");
		newDefault(new CallableMax(), "M");
		newDefault(new CallableRandom(), "ñ");
		newDefault(new CallableAbs(), "a");
		newDefault(new CallableLog(), "Š");
		
		
		// Logical Functions
		newDefault(new CallableEquals(), "equals", "e");
		newDefault(new CallableNot(), "¬");
		newDefault(new CallableLessThan(), "<");
		newDefault(new CallableGreaterThan(), ">");
		
		// Bitwise Functions
		newDefault(new CallableBitwiseOr(), "or", "|");
		newDefault(new CallableBitwiseAnd(), "and", "&");
		
		// Reg Manipulation
		newDefault(new CallableDuplicate(), "]");
		newDefault(new CallableRegPop(), "[");
		newDefault(new CallableRegSwap(), "\\");
		
		// Flow of Control
		newDefault(new CallableFunc(), "function", "{");
		newDefault(new CallableEnd(), "end", "}");
		newDefault(new CallableContainedFunc(), "«");
		newDefault(new CallableContainedEnd(), "»");
		newDefault(new CallableIf(), "if", "?");
		newDefault(new CallableWhile(), "while", ":");
		newDefault(new CallableFor(), "for", ";");
		newDefault(new CallableSatisfy(), "é");
		newDefault(new CallableHalt(), "exit", "¶");
		
		// Stacks
		newDefault(new CallableNewStack(), "stack", "s");
		newDefault(new CallableToStack(), "tostack", "S");
		newDefault(new CallableRange(), "range", "R");
		newDefault(new CallableStartStack(), "(");
		newDefault(new CallableEndStack(), ")");
		newDefault(new CallablePush(), "push", "’");
		newDefault(new CallablePop(), "pop", "‘");
		newDefault(new CallableSort(), "sort","§");
		newDefault(new CallableRotateRight(), "›");
		newDefault(new CallableRotateLeft(), "‹");
		
		// Strings
		newDefault(new CallableReplace(), "replace", "r");
		newDefault(new CallableInverse(), "inverse", "i");
		newDefault(new CallableFormat(), "format", "F");
		newDefault(new CallableChar(), "char", "c");
		newDefault(new CallableOrd(), "ord", "o");
		newDefault(new CallableBase(), "base", "B");
		newDefault(new CallableSub(), "sub", "ü");
		newDefault(new CallableHead(), "head", "ù");
		newDefault(new CallableTail(), "tail", "ú");
		newDefault(new CallableSplit(), "split", "û");
		newDefault(new CallableUpper(), "upper", "U");
		newDefault(new CallableLower(), "lower", "l");
		
		// Misc
		newDefault(new CallableCall(), "call","C");
		newDefault(new CallableAsoc(), "asoc","=");
		newDefault(new CallableLocalAsoc(), "@");
		newDefault(new CallableLen(), "len", "L");
		
		// Constants
		newDefault(new CallablePushSource(), "q");
		newDefault(new CallablePushReg(), "ý");
		newDefault(new CallablePushTime(), "t");
		newDefault(new CallablePushTimeMS(), "™");
		newDefault("x", "x");
		newDefault("y", "y");
		newDefault("z", "z");
		newDefault("X", "X");
		newDefault("Y", "Y");
		newDefault("Z", "Z");
		newDefault(10, "°");
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
		
		if(func.length()==0){
			return null;
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
		
		if(func.matches("^([\"'])[\\x00-\\xFF]*\\1$")){
			return new VarString(func.substring(1,func.length()-1)).getCallable();
		}
		
		if(Flags.FlagToggled("z")){
			
		}
		
		return null;
	}
	
	public static void newDefault(Var var, String... name){
		newDefault(new CallablePushVar(var),name);
	}
	
	public static void newDefault(String var, String... name){
		newDefault(new CallablePushVar(var),name);
	}
	
	public static void newDefault(double var, String... name){
		newDefault(new CallablePushVar(var),name);
	}
	
	public HashMap<String,Callable> duplicateAsoc(){
		HashMap<String, Callable> newMap = new HashMap<String, Callable>();
		
		for(Map.Entry<String, Callable> entry : custom_asoc.entrySet()){
			newMap.put(entry.getKey(), entry.getValue());
		}
		
		if(parent!=null){
			HashMap<String, Callable> parentMap = parent.duplicateAsoc();
			for(Map.Entry<String, Callable> entry : newMap.entrySet()){
				parentMap.put(entry.getKey(), entry.getValue());
			}
			newMap = parentMap;
		}
		return newMap;
	}
	
	public static void newDefault(Callable call, String... name){
		for(int i=0; i<name.length; i++){
			if(default_functions.get(name[i])!=null){
				System.err.println("WARNING, "+name[i]+" USED MULTIPLE TIMES!");
			}
			default_functions.put(name[i], call);
		}
	}
	
}
