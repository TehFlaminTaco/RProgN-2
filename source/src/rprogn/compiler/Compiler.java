package rprogn.compiler;

import java.util.Stack;

import rprogn.Flags;
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
		int mode = Flags.FlagToggled("z") ? MODE_ZSS : MODE_GENERAL; // Mode identifies if we're in a string or whatever.
		String builtWord = new String();
		String[] strings = input.split("");
		int i=0;
		while(i<strings.length){
			String subString = strings[i];
			
			// End a word.
			if(subString.matches("\\s")){ // If we finish the word, we push it to the concept stack.
				pushConceptFromWord(builtWord,mode,c);
				mode=Flags.FlagToggled("z") ? MODE_ZSS : MODE_GENERAL;
				builtWord = new String();
				
			// Pair Quotes, and make it assume string if General.
			}else if(subString.matches("['\"]")){ // Strings are fairly easy to pair in this version.
				if(mode==MODE_GENERAL){
					mode=MODE_STRING;
				}
				int skip = input.indexOf(subString, i+1);
				if(mode==MODE_ZSS){
					pushConceptFromWord(input.substring(i+1,skip),MODE_STRING,c);
				}else{
					builtWord+=input.substring(i+1,skip);
				}
				int match = skip;
				i=match;
			
			// Enter a ZSS.
			}else if(subString.equals("~") && mode==MODE_GENERAL && builtWord.length()<=0){
				mode=MODE_ZSS;
				
			// If we're in a ZSS, Handle pushing of individual strings like pushing of full words.
			}else if(mode==MODE_ZSS){
				if(subString.equals("`")){
					if(input.length() >= i+2){
						pushConceptFromWord(input.substring(i+1,i+2),MODE_STRING,c);
					}
					i++;
				}else if(subString.equals("#")){
					if(input.length() >= i+2){
						pushConceptFromWord(input.substring(i,i+2),MODE_GENERAL,c);
					}
					i++;
				}else if(subString.equals("$")){
					String s = "";
					String part;
					while((part=input.substring(++i,i+1)).matches("[\\d.-]")){
						s = s + part;
					}
					pushConceptFromWord("$"+s,MODE_GENERAL,c);
					i--;
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
		if(builtWord.length()>0 || mode == MODE_STRING){ // Only valid words! :D
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
			return new ConceptZSS(builtWord);
		}
		return null;
	}
}
