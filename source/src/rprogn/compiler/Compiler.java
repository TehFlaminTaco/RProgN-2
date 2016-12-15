package rprogn.compiler;

import java.util.Stack;

import rprogn.compiler.concept.Concept;
import rprogn.compiler.concept.ConceptFunction;
import rprogn.compiler.concept.ConceptString;
import rprogn.compiler.concept.ConceptZSS;

public class Compiler {
	
	public static final int MODE_GENERAL = 0;
	public static final int MODE_STRING = 1;
	public static final int MODE_ZSS = 2;
	
	public static Concept[] compile(String input){
		Stack<Concept> c = new Stack<Concept>();
		int mode = MODE_GENERAL; // Mode identifies if we're in a string or whatever.
		String builtWord = new String();
		String[] strings = input.split("");
		int i=0;
		while(i<strings.length){
			String subString = strings[i];
			
			// End a word.
			if(subString.matches("\\s")){ // If we finish the word, we push it to the concept stack.
				pushConceptFromWord(builtWord,mode,c);
				mode=MODE_GENERAL;
				builtWord = new String();
				
			// Pair Quotes, and make it assume string if General.
			}else if(subString.matches("['\"]")){ // Strings are fairly easy to pair in this version.
				if(mode==MODE_GENERAL){
					mode=MODE_STRING;
				}
				
				if(mode==MODE_ZSS){
					System.out.println(input.substring(i+1,input.indexOf(subString, i+1)));
				}else{
					builtWord+=input.substring(i+1,input.indexOf(subString, i+1));
				}
				int match = input.indexOf(subString, i+1);
				i=match;
			
			// Enter a ZSS.
			}else if(subString.equals("~") && mode==MODE_GENERAL && builtWord.length()<=0){
				mode=MODE_ZSS;
				
			// If we're in a ZSS, Handle pushing of individual strings like pushing of full words.
			}else if(mode==MODE_ZSS){
				if(subString.equals("`")){
					pushConceptFromWord(input.substring(i+1,i+2),MODE_STRING,c);
					i++;
				}else{
					pushConceptFromWord(subString,MODE_GENERAL,c);
				}
			
			// Keep building the word.
			}else{
				builtWord = builtWord+subString;
			}
			i++;
		}
		pushConceptFromWord(builtWord,mode,c);
		
		return c.toArray(new Concept[c.size()]);
	}
	
	public static void pushConceptFromWord(String builtWord, int mode, Stack<Concept> c){
		if(builtWord.length()>0){ // Only valid words! :D
			c.push(ConceptFromWord(builtWord,mode));
		}
	}
	
	public static Concept ConceptFromWord(String builtWord, int mode){
		switch(mode){
		case MODE_GENERAL:
			return new ConceptFunction(builtWord);
		case MODE_STRING:
			return new ConceptString(builtWord);
		case MODE_ZSS:
			System.err.println("This shouldn't be possible, and it's basically horrifying.\nA ZSS was pushed in full.\n"+builtWord);
			return new ConceptZSS(builtWord);
		}
		return null;
	}
}
