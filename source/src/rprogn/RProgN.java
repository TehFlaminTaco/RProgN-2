package rprogn;

import java.io.File;
import java.io.FileNotFoundException;

import rprogn.functions.Functions;
import rprogn.interpreter.Interpreter;

public class RProgN {
	static Interpreter interpreter;
	
	public static void main(String[] args) {
		Functions.setDefaults();
		
		interpreter = new Interpreter();
		try{
			interpreter.execute(new File("C:\\Users\\William\\Desktop\\code.rpn"));
			
			while(!interpreter.reg.isEmpty()){
				System.out.println(interpreter.reg.pop().toString());
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
