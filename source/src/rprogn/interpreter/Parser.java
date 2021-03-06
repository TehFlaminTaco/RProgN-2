package rprogn.interpreter;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.compiler.concept.ConceptFunction;
import rprogn.compiler.concept.ConceptString;
import rprogn.functions.Functions;
import rprogn.functions.Scope;

public class Parser {
	
	public static void parse(Concept[] concepts, Interpreter interpreter){
		parse(concepts, interpreter, null);
	}
	
	public static void parse(Concept[] concepts, Interpreter interpreter, Functions parentFuncs){
		Scope scope = new Scope();
		scope.concepts = concepts;
		scope.functions = new Functions();
		scope.functions.parent = parentFuncs;
		for(scope.iPointer=0; scope.iPointer<concepts.length; scope.iPointer++){
			Concept concept = concepts[scope.iPointer];
			if(concept instanceof ConceptFunction){
				Callable func = scope.functions.get(((ConceptFunction) concept).func);
				
				if(func!=null){
					func.Call(interpreter,scope);
				}
			}else if(concept instanceof ConceptString){
				interpreter.push(((ConceptString)concept).string);
			}
		}
	}
	
}
