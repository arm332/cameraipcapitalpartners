package com.example.cameraipcapitalpartners.action;

public class ActionFactory {
	private ActionFactory() {} // prevents instantiation
	
	public static Action getAction(String path) {
		String className = getClassName(path);
		
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
	
	private static String getClassName(String path) {
		String name = path.equals("/") ? "Home" : toCamelCase(path);
		String packageName = ActionFactory.class.getPackage().getName();
		return packageName + "." + name + "Action";
	}
	
	/*private static String toUpperFirst(String path) {
		String str = path.replaceAll("/", "");
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1).toLowerCase();
	}*/
	
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
