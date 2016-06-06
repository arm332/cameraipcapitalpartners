package com.invprof.cameras.action;

public class ActionFactory {
	private ActionFactory() {} // prevents instantiation
	
	public static Action getAction(String path) {
		String className = ActionFactory.class.getPackage().getName() + ".";
		
		if (path.equals("/"))
			className += "Home";
		else if (path.startsWith("/.well-known"))
			className += "WellKnown";
		else 
			className += toCamelCase(path);
		
		className += "Action";
		
		try {
			return (Action) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}
		
		return null;
	}
		
	private static String toCamelCase(String path) {
		StringBuilder out = new StringBuilder();
		String[] arr = path.split("/");
		
		for (String str : arr) {
			if (str.length() > 0) {
				out.append(Character.toUpperCase(str.charAt(0)));
				out.append(str.substring(1).toLowerCase());
			}
		}
		
		return out.toString();
	}
}
