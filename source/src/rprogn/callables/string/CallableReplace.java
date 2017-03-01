package rprogn.callables.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;
import rprogn.variable.VarString;

public class CallableReplace implements Callable {

	@Override
	public int Call(Interpreter interpreter, Scope scope) {
		Var rplmt = interpreter.pop();
		if(rplmt==null){return -1;}
		if (rplmt instanceof VarCallable){
			Var key = interpreter.pop();
			if(key==null){return -1;}
			VarCallable func = (VarCallable) rplmt;
			if (key instanceof VarStack){
				VarStack stack = (VarStack) key;
				VarStack newStack = new VarStack();
				for(int i=stack.data.size()-1; i>=0; i--){
					interpreter.reg.push(stack.data.get(i));
					func.Call(interpreter, scope);
					Var popped = interpreter.pop();
					if (popped!=null){
						newStack.data.push(popped);
					}
				}
				interpreter.reg.push(newStack);
			}else{
				String stKey = key.toString();
				Var target = interpreter.pop();
				if(target==null){return -1;}
				if (target instanceof VarStack){
					VarStack stack = (VarStack) target;
					VarStack newStack = new VarStack();
					for(int i=stack.data.size()-1; i>=0; i--){
						String s = stack.data.get(i).toString();
						Pattern p = Pattern.compile(stKey);
						Matcher m = p.matcher(s);
						StringBuffer sb = new StringBuffer();
						while(m.find()){
							interpreter.reg.push(new VarString(m.group()));
							func.Call(interpreter, scope);
							Var popped = interpreter.pop();
							if (popped!=null){
								m.appendReplacement(sb, popped.toString());
							}
						}
						m.appendTail(sb);
						newStack.data.push(new VarString(sb.toString()));
					}
					interpreter.reg.push(newStack);
				}else{
					String s = target.toString();
					Pattern p = Pattern.compile(stKey);
					Matcher m = p.matcher(s);
					StringBuffer sb = new StringBuffer();
					while(m.find()){
						interpreter.reg.push(new VarString(m.group()));
						func.Call(interpreter, scope);
						Var popped = interpreter.pop();
						if (popped!=null){
							m.appendReplacement(sb, popped.toString());
						}
					}
					m.appendTail(sb);
					interpreter.reg.push(new VarString(sb.toString()));
				}
			}
		}else{
			Var key = interpreter.pop();
			if(key==null){return -1;}
			if(key instanceof VarStack){
				VarStack newStack = new VarStack();
				for (int i=0; i<((VarStack)key).data.size(); i++){
					newStack.data.push(key);
				}
				interpreter.reg.push(newStack);
			}else{
				Var target = interpreter.pop();
				if(target==null){return -1;}
				if (target instanceof VarStack){
					VarStack stack = (VarStack) target;
					VarStack newStack = new VarStack();
					for (int i=0; i<stack.data.size(); i++){
						newStack.data.push(new VarString(stack.data.get(i).toString().replaceAll(key.toString(), rplmt.toString())));
					}
					interpreter.reg.push(newStack);
				}else{
					interpreter.reg.push(new VarString(target.toString().replaceAll(key.toString(), rplmt.toString())));
				}
			}
		}
		return -1;
	}

	@Override
	public String describe() {
		return "Replace all occurrences of one value with another in a last. Optionally use a function, and optionally use a stack.";
	}

}
