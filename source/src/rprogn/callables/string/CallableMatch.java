package rprogn.callables.string;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableMatch implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var needle = interpreter.pop();
		if(needle == null){
			return;
		}
		Var haystack = interpreter.pop();
		if(haystack == null){
			interpreter.push(needle);
			return;
		}
		if(haystack instanceof VarString || haystack instanceof VarNumber){
			String hayString = haystack.toString();
			String needleString = needle.toString();
			VarStack results = new VarStack();
			Matcher m = Pattern.compile(needleString).matcher(hayString);
			while(m.find()){
				if (m.groupCount()<1)
					results.push(m.group());
				else{
					Stack<Var> subs = new Stack<Var>();
					for(int i=0; i<=m.groupCount(); i++){
						subs.push(new VarString(m.group(i)));
					}
					results.push(subs);
				}
			}
			interpreter.push(results);
			return;
		}
		if(haystack instanceof VarStack){
			VarStack stack = (VarStack) haystack;
			for(int i=0;i<stack.size();i++){
				if(stack.get(i).equals(needle)){
					interpreter.push(1);
					return;
				}
			}
			interpreter.push(0);
		}
	}

	@Override
	public String describe() {
		return "Check if a string contains a regex, returns it's matches if it finds any.";
	}
}
