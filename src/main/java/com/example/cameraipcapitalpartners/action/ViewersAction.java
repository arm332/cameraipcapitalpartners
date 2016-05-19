package com.example.cameraipcapitalpartners.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.memcache.ErrorHandlers;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.gson.Gson;

public class ViewersAction extends ActionAdapter {

	@SuppressWarnings("unchecked")
	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		Date now = new Date();
		Map<String, Date> viewers = new HashMap<>();
		MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
		syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		Object value = syncCache.get("viewers");
		
		if (value != null) {
			viewers = (Map<String, Date>) value;
		}
		
		Iterator<Entry<String, Date>> entries = viewers.entrySet().iterator();
		
		while (entries.hasNext()) {
			final Entry<String, Date> entry = entries.next();
			//final String email = (String) entry.getKey();
			final Date expires = (Date) entry.getValue();
			if (expires.before(now)) entries.remove();
		}
		
		String email = (String) request.getSession().getAttribute("email");
		Date expires = new Date(now.getTime() + 1000 * 60 * 2); // 2 min; I know, I know, I know...
		viewers.put(email, expires);
		syncCache.put("viewers", viewers); 
		
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
		return "/viewers.jsp";
	}
	
}
