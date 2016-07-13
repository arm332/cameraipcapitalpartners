package com.invprof.cameras.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.io.BaseEncoding;

public class ForwardAction extends ActionAdapter {
	private static String sCam = null;
	
	private static synchronized String getSCam() {
		if (sCam == null) {
			try {
				InputStream input = ForwardAction.class.getClassLoader().getResourceAsStream("forward.properties");
				Properties properties = new Properties();
				properties.load(input);
				sCam = properties.getProperty("cam");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sCam;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		String param = request.getParameter("url"); // /forward?url=URL
		String cam = request.getParameter("cam"); // /forward?cam=CAM
		
		if (cam != null) {
			param = getSCam().replace("cam", cam);
		}
		
		response.setContentType("image/jpeg");
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		
		try {
			
			URL url = new URL(param);
			URLConnection conn = url.openConnection();
			
			/* HTTP Basic Authorization. Requires Java 8, Apache Commons Codec 
			 * or Google Guava. Usng Google Guava
			 */
			if (url.getUserInfo() != null) {
				byte[] bytes = url.getUserInfo().getBytes();
				String auth = BaseEncoding.base64().encode(bytes);
			    conn.setRequestProperty("Authorization", auth);
			}

			input = new BufferedInputStream(conn.getInputStream());
			output = new BufferedOutputStream(response.getOutputStream());
	        int ch = 0;
	        
	        while((ch = input.read()) != -1) {
	        	output.write(ch);
	        }
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
}
