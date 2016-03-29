package com.example.cameraipcapitalpartners.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class ViewerAction extends ActionAdapter {

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		Date now = new Date();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		
		@SuppressWarnings("unchecked")
		Map<String, Date> viewers = (Map<String, Date>) context.getAttribute("viewers");
		
		if (viewers == null) {
			viewers = new HashMap<String, Date>();
		}
		
		Iterator<Entry<String, Date>> entries = viewers.entrySet().iterator();
		
		while (entries.hasNext()) {
			final Entry<?, ?> entry = (Entry<?, ?>) entries.next();
			//final String email = (String) entry.getKey();
			final Date expires = (Date) entry.getValue();
			if (expires.before(now)) {
				entries.remove();
			}
		}
		
		String key = (String) session.getAttribute("email");
		Date expires = new Date(now.getTime() + 1000 * 60 * 2); // 2 min, I know, I know, I know...
		viewers.put(key, expires);
		
		context.setAttribute("viewers", viewers);
		
		if (request.getHeader("x-requested-with") != null) {
			response.setContentType("application/json");
			String json = new Gson().toJson(viewers);
			
	        try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
        
	        return null;
		}
		
		request.setAttribute("map", viewers);
		return "/viewer.jsp";
	}
	
}
