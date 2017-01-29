package main;

import graphics.ConstantIDs;

public class Utils {
	public static String joinIDName(ConstantIDs id, String name){
		return String.join("_", id.toString(), name);
	}
}
