package rprogn;

import java.io.File;
import java.io.FileNotFoundException;

import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;

public class RProgN {
	static Interpreter interpreter;
	
	public static void main(String[] args) {
		Functions.setDefaults();
		
		Flags.SetFlag("z");
		
		String target=null;
		for(String arg : args){
			if(arg.length()>1 && arg.substring(0, 1).equals("-")){
				for(String s : arg.substring(2).split("")){
					Flags.SetFlag(s);
				}
			}else{
				target = arg;
			}
		}
		
		if(target==null){
			System.err.println("Could not run no code!");
		}
		
		interpreter = new Interpreter();
		try{
			if(Flags.FlagToggled("s")){
				interpreter.execute(target);
			}else{
				interpreter.execute(new File(target));
			}
			
			while(!interpreter.reg.isEmpty()){
				String s = interpreter.reg.pop().toString();
				if(interpreter.reg.isEmpty()){System.out.print(s);}else{System.out.println(s);};
			}
			
		}catch(Exception e){
			if(e instanceof FileNotFoundException){
				System.err.println("Could not find file to run!");
			}else{
				e.printStackTrace();
			}
		}
	}

}
