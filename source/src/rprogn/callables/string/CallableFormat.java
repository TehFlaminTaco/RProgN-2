package rprogn.callables.string;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarNumber;

public class CallableFormat implements Callable {

	public static Pattern formatMatches = Pattern.compile("%([sSqQnNiIbBhH])");
	
	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		
		Var format = interpreter.pop();
		if(format==null){
			return;
		}
		
		String form = format.toString();
		Stack<Var> args = new Stack<Var>();
		
		Matcher m = formatMatches.matcher(form);
		while(m.find()){
			Var i = interpreter.pop();
			if(i!=null){
				args.insertElementAt(i, 0);
			}
		}
		Var[] argV = new Var[args.size()];
		argV = args.toArray(argV);
		interpreter.push(format(form, argV));
		
	}

	@Override
	public String describe() {
		return "Format a string with given arguments such that 'a, b, c, format'";
	}
	
	public static String format(String tar, Var[] args){
		Matcher m = formatMatches.matcher(tar);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while(m.find()){
			String type = m.group(1);
			switch(type){
			case "q": case "Q":
				String arg = args[i].toString();
				String out = "";
				if(arg.length()==1){
					out = "`"+arg;
				}else if(arg.contains("\"")){
					out = "'"+arg+"'";
				}else{
					out = "\""+arg+"\"";
				}
				m.appendReplacement(sb, out);
				i++;
				break;
			case "s": case "S": case "n": case "N": // The nN format is a LIE
				m.appendReplacement(sb, args[i].toString());
				i++;
				break;
			case "i": case "I":
				Var var = args[i];
				if(var instanceof VarNumber){
					VarNumber n = (VarNumber) var;
					m.appendReplacement(sb, n.data.toBigInteger().toString());
				}
				i++;
				break;
			case "b": case "B":
				var = args[i];
				if(var instanceof VarNumber){
					VarNumber n = (VarNumber) var;
					m.appendReplacement(sb, n.data.toBigInteger().toString(2));
				}
				i++;
				break;
			case "h": case "H":
				var = args[i];
				if(var instanceof VarNumber){
					VarNumber n = (VarNumber) var;
					m.appendReplacement(sb, n.data.toBigInteger().toString(16).toUpperCase());
				}
				i++;
				break;
			}
		}
		m.appendTail(sb);
		return new String(sb);
	}

}
