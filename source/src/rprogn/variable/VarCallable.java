package rprogn.variable;

import java.util.HashMap;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.functions.Functions;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.interpreter.Parser;

public class VarCallable implements Var, Callable {
	public Concept[] data;
	public Callable otherDat;
	public HashMap<String, Callable> cur_asoc;
	
	public String left_brace = "";
	public String right_brace = "";
	
	public VarCallable(Concept[] init){
		data = init;
	}
	
	public VarCallable(Callable init){
		otherDat = init;
	}
	
	
	public Callable getCallable(){
		return this;
	}
	
	public String toString(){
		if(otherDat!=null){
			return otherDat.describe();
		}
		if(data!=null){
			String s = left_brace;
			for (Concept c : data){
				s += c.toString();
			}
			return s + right_brace;
		}
		return "[FUNCTION]";
	}
	
	@SuppressWarnings("unchecked")
	public void Call(Interpreter interpreter, Scope scope){
		if (otherDat==null){
			Functions funcs = scope.functions;
			if(cur_asoc!=null){
				funcs = new Functions();
				funcs.custom_asoc = (HashMap<String, Callable>) cur_asoc.clone();
			}
			Parser.parse(data, interpreter, funcs);
		}else{
			otherDat.Call(interpreter, scope);
		}
	}
	
	public String describe(){
		return "A Variable that holds an array of concepts, which when called, parses those concepts.";
	}
	
	public boolean truthy(){
		return true;
	}
	
	public String type(){
		return "function";
	}
	
}
