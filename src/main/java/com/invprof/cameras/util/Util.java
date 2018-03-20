package com.invprof.cameras.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public final class Util {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private Util() {} // prevents instantiation

    public static Properties getProperties() {
    		return getProperties("config.properties");
    }
    
    public static Properties getProperties(String filename) {
        Properties properties = new Properties();
        InputStream in = Util.class.getClassLoader().getResourceAsStream(filename);

        try {
            properties.load(in);
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch(IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }

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
	
	public static Date tryParseDate(String s) {
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
}
