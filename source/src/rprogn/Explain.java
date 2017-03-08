package rprogn;

import rprogn.callables.Callable;
import rprogn.callables.flow.CallableContainedEnd;
import rprogn.callables.flow.CallableContainedFunc;
import rprogn.callables.flow.CallableEnd;
import rprogn.callables.flow.CallableFunc;
import rprogn.compiler.concept.Concept;
import rprogn.compiler.concept.ConceptFunction;
import rprogn.compiler.concept.ConceptString;
import rprogn.functions.Functions;

public class Explain {
	public static String explain(Concept[] c){
		String s = "";
		int ind = 0;
		Functions funcs = new Functions();
		for (Concept ca : c){
			if(ca instanceof ConceptString){
				for (int i = 0; i < ind; i++){s = s + "\t";}
				s += ca.toString();
				s += "\t";
				
				s += "The literal string \""+ca.toString()+"\"\n";
			}else if(ca instanceof ConceptFunction){
				Callable callable = funcs.get(ca.toString());
				if (callable instanceof CallableFunc){
					for (int i = 0; i < ind; i++){s = s + "\t";}
					ind++;
					s += "{\n";
				}else if(callable instanceof CallableContainedFunc){
					for (int i = 0; i < ind; i++){s = s + "\t";}
					ind++;
					s += "«\n";
				}else if(callable instanceof CallableEnd){
					ind--;
					for (int i = 0; i < ind; i++){s = s + "\t";}
					s += "}\n";
				}else if(callable instanceof CallableContainedEnd){
					ind--;
					for (int i = 0; i < ind; i++){s = s + "\t";}
					s += "»\n";
				}else if(callable == null){
					for (int i = 0; i < ind; i++){s = s + "\t";}
					s += ca.toString()+"\tAn unknown function\n";
				}else{
					for (int i = 0; i < ind; i++){s = s + "\t";}
					s += ca.toString()+"\t"+callable.describe()+"\n";
				}
			}
		}
		return s;
	}
}
