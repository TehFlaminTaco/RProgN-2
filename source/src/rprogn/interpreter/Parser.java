package rprogn.interpreter;

import rprogn.callables.Callable;
import rprogn.compiler.concept.Concept;
import rprogn.compiler.concept.ConceptFunction;
import rprogn.compiler.concept.ConceptString;
import rprogn.functions.Functions;
import rprogn.functions.Scope;
import rprogn.variable.VarString;

public class Parser {
	
	public static void parse(Concept[] concepts, Interpreter interpreter){
		parse(concepts, interpreter, null);
	}
	
	public static void parse(Concept[] concepts, Interpreter interpreter, Functions parentFuncs){
		Scope scope = new Scope();
		scope.concepts = concepts;
		scope.functions = new Functions();
		scope.functions.parent = parentFuncs;
		for(int i=0; i<concepts.length; i++){
			scope.iPointer = i;
			Concept concept = concepts[i];
			if(concept instanceof ConceptFunction){
				Callable func = scope.functions.get(((ConceptFunction) concept).func);
				
				if(func!=null){
					try{
						int I = func.Call(interpreter,scope);
						if(I>-1){
							i=I;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else if(concept instanceof ConceptString){
				interpreter.reg.push(new VarString(((ConceptString)concept).string));
			}
		}
	}
	
}
