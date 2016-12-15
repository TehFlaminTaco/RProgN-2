package rprogn.compiler.concept;


public class ConceptFunction extends Concept {
	public String func;
	
	public ConceptFunction(String func){
		this.func = func;
	}
	
	@Override
	public String toString(){
		return func;
	}
	
}
