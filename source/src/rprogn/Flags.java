package rprogn;

import java.util.HashMap;

public class Flags {
	public static HashMap<String,String> flags = new HashMap<String,String>();
	
	
	public static void SetFlag(String flag){
		SetFlag(flag,"true");
	}
	
	public static void SetFlag(String flag, String value){
		flags.put(flag, value);
	}
	
	public static boolean FlagToggled(String flag){
		String str = flags.get(flag);
		if(str!=null){
			return true;
		}
		return false;
	}
}
