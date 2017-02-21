package rprogn;

import java.util.HashMap;

public class Flags {
	public static HashMap<String,Boolean> flags = new HashMap<String,Boolean>();
	
	
	public static void SetFlag(String flag){
		SetFlag(flag.toLowerCase(),flag == flag.toLowerCase());
	}
	
	public static void SetFlag(String flag, boolean value){
		flags.put(flag, value);
	}
	
	public static boolean FlagToggled(String flag){
		Boolean b = flags.get(flag.toLowerCase());
		if(b==null||!b){
			return flag!=flag.toLowerCase();
		}
		return flag==flag.toLowerCase();
	}
}
