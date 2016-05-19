package com.example.cameraipcapitalpartners.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WellKnownAction extends ActionAdapter {
	private static final Map<String, String> challenges = new HashMap<>();
	
	static {
        challenges.put("QLMQxGJAy31mfhU4C4jEjcCPq-xIok4RR8uuG0SBYbM", 
        		"TEST");
        challenges.put("yegbiwTIdv2mDa_3uuzpFmHMUL0t06K0WIQg28kYkWk", 
        		"yegbiwTIdv2mDa_3uuzpFmHMUL0t06K0WIQg28kYkWk.sMQb7Dhy4bTr_3A6gqYk10ZsAF42hM7uLHm9_bi-jXQ");
    }
		
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
        try {
        	
    		String path = request.getPathInfo();
    		
    		if (path.startsWith("/.well-known/acme-challenge/")) {
    			
    			String id = path.substring("/.well-known/acme-challenge/".length());
    			
    			if (challenges.containsKey(id)) {
    	            response.setContentType("text/plain");
    	            response.getOutputStream().print(challenges.get(id));
    				return null;
    			}
    		}
    		
			response.sendError(404);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return null;
	}
}
