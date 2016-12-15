package rprogn.compiler.concept;


public class ConceptString extends Concept {
	public String string;
	
	public ConceptString(String string){
		this.string = string;
	}
	
	@Override
	public String toString(){
		return string;
	}
}
