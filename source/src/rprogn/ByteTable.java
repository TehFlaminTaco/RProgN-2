package rprogn;

import rprogn.callables.Callable;
import rprogn.functions.Functions;

public class ByteTable {
	public static String GetUsedBytes(){
		Functions funcs = new Functions();
		
		String table_string = "";
		
		int cell_width = 12;
		
		table_string += rep("-", cell_width);
		table_string = "+"+table_string;
		String bar = table_string;
		table_string = rep(bar, 17);
		table_string += "+\n|"+rep(" ", cell_width);
		
		for (int i=0; i<16; i++){
			table_string += "|"+rep(" ",(int)Math.ceil((cell_width-2)/2))+String.format("%h", i) + "_"+ rep(" ",(int)Math.floor((cell_width-2)/2));
		}
		
		table_string += "|\n";
		
		table_string += rep(bar, 17)+"+\n";
		
		for(int i=0; i<16; i++){
			table_string += "|"+rep(" ",(int)Math.ceil((cell_width-2)/2))+String.format("%h", i)+"_"+rep(" ",(int)Math.floor((cell_width-2)/2));
			for(int c=0; c<16; c++){
				table_string += "|"+rep(" ",(int)Math.ceil((cell_width-1)/2))
								   +fromChar((char)(i*16+c))
								   +rep(" ",(int)Math.floor((cell_width-1)/2)+1);
			}
			table_string += "|\n|"+rep(" ",cell_width)+"|";
			for(int c=0; c<16; c++){
				Callable call = funcs.get(fromChar((char)(i*16+c)));
				String name = "null";
				if(call!=null){
					name = call.getClass().toString();
				}
				name = name.replaceAll(".*\\.", "");
				name = name.replaceAll("Callable","");
				if(name.length()>cell_width){
					name = name.substring(0, cell_width-3)+"...";
				}
				table_string += name + rep(" ",cell_width-name.length())+"|";
			}
			table_string += "\n" + rep("+"+rep("-",cell_width),17);
			
			table_string +="+\n";
		}
		
		return table_string;
	}
	
	public static String rep(String s, int i){
		String o = "";
		for (int c=0; c<i; c++){
			o+=s;
		}
		return o;
	}
	
	public static String fromChar(char n){
		if (n < (16*2)){
			return "?";
		}
		if (n >= (16*8) && n < (16*10)){
			return String.valueOf(("€ ‚ƒ„…†‡ˆ‰Š‹Œ Ž  ‘’“”•–—˜™š›œ žŸ").charAt((int)n-(16*8)));
		}
		return String.valueOf(n);
	}
}
