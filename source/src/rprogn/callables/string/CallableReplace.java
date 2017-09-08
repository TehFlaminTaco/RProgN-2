package rprogn.callables.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rprogn.callables.Callable;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.Var;
import rprogn.variable.VarCallable;
import rprogn.variable.VarStack;

public class CallableReplace implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		Var rplmt = interpreter.pop();
		if(rplmt==null){return;}
		if (rplmt instanceof VarCallable){
			Var key = interpreter.pop();
			if(key==null){return;}
			VarCallable func = (VarCallable) rplmt;
			if (key instanceof VarStack){
				VarStack stack = (VarStack) key;
				VarStack newStack = new VarStack();
				for(int i=0; i<stack.size(); i++){
					interpreter.push(stack.get(i));
					func.Call(interpreter, scope);
					Var popped = interpreter.pop();
					if (popped!=null){
						newStack.push(popped);
					}
				}
				interpreter.push(newStack);
			}else{
				String stKey = key.toString();
				Var target = interpreter.pop();
				if(target==null){return;}
				if (target instanceof VarStack){
					VarStack stack = (VarStack) target;
					VarStack newStack = new VarStack();
					for(int i=stack.size()-1; i>=0; i--){
						String s = stack.get(i).toString();
						Pattern p = Pattern.compile(stKey);
						Matcher m = p.matcher(s);
						StringBuffer sb = new StringBuffer();
						while(m.find()){
							interpreter.push(m.group());
							func.Call(interpreter, scope);
							Var popped = interpreter.pop();
							if (popped!=null){
								m.appendReplacement(sb, popped.toString());
							}
						}
						m.appendTail(sb);
						newStack.push(sb.toString());
					}
					interpreter.push(newStack);
				}else{
					String s = target.toString();
					Pattern p = Pattern.compile(stKey);
					Matcher m = p.matcher(s);
					StringBuffer sb = new StringBuffer();
					while(m.find()){
						interpreter.push(m.group());
						func.Call(interpreter, scope);
						Var popped = interpreter.pop();
						if (popped!=null){
							m.appendReplacement(sb, popped.toString());
						}
					}
					m.appendTail(sb);
					interpreter.push(sb.toString());
				}
			}
		}else{
			Var key = interpreter.pop();
			if(key==null){return;}
			if(key instanceof VarStack){
				VarStack newStack = new VarStack();
				for (int i=0; i<((VarStack)key).size(); i++){
					newStack.push(rplmt);
				}
				interpreter.push(newStack);
			}else{
				Var target = interpreter.pop();
				if(target==null){return;}
				if (target instanceof VarStack){
					VarStack stack = (VarStack) target;
					VarStack newStack = new VarStack();
					for (int i=0; i<stack.size(); i++){
						newStack.push(stack.get(i).toString().replaceAll(key.toString(), rplmt.toString()));
					}
					interpreter.push(newStack);
				}else{
					interpreter.push(target.toString().replaceAll(key.toString(), rplmt.toString()));
				}
			}
		}
	}

	@Override
	public String describe() {
		return "Replace all occurrences of one value with another in a last. Optionally use a function, and optionally use a stack.";
	}

}
