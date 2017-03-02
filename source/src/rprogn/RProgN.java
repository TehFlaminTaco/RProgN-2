package rprogn;

import java.io.File;
import java.io.FileNotFoundException;

import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;
import rprogn.variable.VarNumber;
import rprogn.variable.VarString;

public class RProgN {
	static Interpreter interpreter;
	
	public static void main(String[] args) {
		Functions.setDefaults();
		
		Flags.SetFlag("z");
		String target=args[0];
		
		interpreter = new Interpreter();
		for (int i=1; i<args.length; i++){
			String arg = args[i];
			if(arg.length()>2 && arg.substring(0, 2).equals("--")){
				Flags.SetFlag(arg.substring(2));
			}else{
				if (arg.matches("^'.*'$")){
					interpreter.reg.push(new VarString(arg.substring(1,arg.length()-1)));
				}else if(arg.matches("(-?\\d+(\\.\\d*)?|(-?\\d+)?.\\d+)")){
					interpreter.reg.push(new VarNumber(arg));
				}else{
					interpreter.reg.push(new VarString(arg));
				}
			}
		}
		
		if(target==null){
			System.err.println("Could not run no code!");
		}
		
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
