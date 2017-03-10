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
		String target=args[0];
		
		interpreter = new Interpreter();
		for (int i=1; i<args.length; i++){
			String arg = args[i];
			if(arg.length()>2 && arg.substring(0, 2).equals("--")){
				Flags.SetFlag(arg.substring(2));
			}else{
				if (arg.matches("^'.*'$")){
					interpreter.push(arg.substring(1,arg.length()-1));
				}else if(arg.matches("(-?\\d+(\\.\\d*)?|(-?\\d+)?.\\d+)")){
					interpreter.push(arg);
				}else{
					interpreter.push(arg);
				}
			}
		}
		
		if(Flags.FlagToggled("b")){
			System.err.println(ByteTable.GetUsedBytes());
		}
		
		if(target==null){
			System.err.println("Could not run no code!");
		}
		
		try{
			double start_time = System.currentTimeMillis();
			if(Flags.FlagToggled("s")){
				interpreter.execute(target);
			}else{
				interpreter.execute(new File(target));
			}
			
			while(!interpreter.reg.isEmpty()){
				String s = interpreter.reg.pop().toString();
				if(interpreter.reg.isEmpty()){System.out.print(s);}else{System.out.println(s);};
			}
			if(Flags.FlagToggled("t")){
				System.err.println("Took "+(System.currentTimeMillis()-start_time)/1000+"s");
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
