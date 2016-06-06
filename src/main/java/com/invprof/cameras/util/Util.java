package com.invprof.cameras.util;

public final class Util {
	private Util() {} // prevents instantiation
	
	public static Integer tryParseInt(String s) {
		try {
		    return Integer.parseInt(s);
		} catch (Exception e) {
		    return null;
		}
	}
	
	public static Long tryParseLong(String s) {
		try {
		    return Long.parseLong(s);
		} catch (Exception e) {
		    return null;
		}
	}
}
