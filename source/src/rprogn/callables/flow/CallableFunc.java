package rprogn.callables.flow;

import java.util.Arrays;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.compiler.concept.ConceptFunction;
import rprogn.functions.Scope;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarCallable;

public class CallableFunc implements Callable {

	@Override
	public void Call(Interpreter interpreter, Scope scope) {
		int d = 0;
		for (int i = scope.iPointer+1; i < scope.concepts.length; i++){
			if(scope.concepts[i] instanceof ConceptFunction){
				ConceptFunction conFunc = (ConceptFunction)scope.concepts[i];
				Callable func = scope.functions.get(conFunc.func);
				if(func instanceof CallableFunc){
					d++;
				}else if(func instanceof CallableEnd){
					if (d--==0){
						Concept[] newConcepts = Arrays.copyOfRange(scope.concepts, scope.iPointer+1, i);
						VarCallable callable = new VarCallable(newConcepts);
						callable.left_brace = "{";
						callable.right_brace = "}";
						interpreter.push(callable);
						scope.iPointer = i;
						return;
					}
				}
			}
		}
		scope.iPointer = 0;
	}

	@Override
	public String describe() {
		return "Define a function between this and the matching end.";
	}
	
}
