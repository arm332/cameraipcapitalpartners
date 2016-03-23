package com.example.cameraipcapitalpartners.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;

public class ViewerAction extends ActionAdapter {

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//List<Viewer> list = service.list();
		List<HashMap<String,String>> list = new ArrayList<>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("_ah_SESSION"); 
		PreparedQuery preparedQuery = datastore.prepare(query); 
		List<Entity> entities = preparedQuery.asList(FetchOptions.Builder.withOffset(0));
		Date now = new Date();
		 
		for (Entity entity : entities) {
			final Blob blob = (Blob) entity.getProperty("_values");
			Object obj = null;

			try (
				ByteArrayInputStream b = new ByteArrayInputStream(blob.getBytes())){
	        	ObjectInputStream o = new ObjectInputStream(b);
				obj = o.readObject();
			} catch(ClassNotFoundException e){
				e.printStackTrace();
		    } catch(IOException e){
				e.printStackTrace();
		    }

			if (obj != null) {
				@SuppressWarnings("unchecked")
				final HashMap<String,Object> values = (HashMap<String, Object>) obj;
				
				if (values != null) {
					final String email = (String) values.get("email");
					
					if (email != null) {
						final HashMap<String,String> map = new HashMap<String,String>();
						
						final Key key = entity.getKey();
						map.put("key", key.getName());
						
						map.put("email", email.toString());
						
						final long time = (long) entity.getProperty("_expires");
						final Date expires = new Date(time);
						map.put("expires", expires.toString());

						final Integer status = now.compareTo(expires);
						map.put("status", status.toString());
						
						list.add(map);
					}
				}
			}
		}
		
		if (request.getHeader("x-requested-with") != null) {
			String json = new Gson().toJson(list);
			response.setContentType("application/json");
			
	        try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        return null;
		}
		else {
			request.setAttribute("list", list);
			return "/viewer.jsp";
		}
		
	}
	
}
