package rprogn.interpreter;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.compiler.concept.ConceptFunction;
import rprogn.compiler.concept.ConceptString;
import rprogn.functions.Functions;
import rprogn.variable.VarString;

public class Parser {
	
	public static void parse(Concept[] concepts, Interpreter interpreter){
		Functions scope = new Functions();
		
		for(int i=0; i<concepts.length; i++){
			Concept concept = concepts[i];
			if(concept instanceof ConceptFunction){
				Callable func = scope.get(((ConceptFunction) concept).func);
				
				if(func!=null){
					int I = func.Call(interpreter,scope);
					if(I>-1){
						i=I;
					}
				}
			}else if(concept instanceof ConceptString){
				interpreter.reg.push(new VarString(((ConceptString)concept).string));
			}
		}
	}
	
}
