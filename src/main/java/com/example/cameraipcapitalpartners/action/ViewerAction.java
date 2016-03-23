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

public class ViewerAction extends ActionAdapter {
	//private ViewerService service = new ViewerServiceImpl(); 

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//List<Viewer> list = service.list();
		List<HashMap<String,String>> list = new ArrayList<>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("_ah_SESSION"); 
		PreparedQuery preparedQuery = datastore.prepare(query); 
		List<Entity> entities = preparedQuery.asList(FetchOptions.Builder.withOffset(0));

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
						
						final long expires = (long) entity.getProperty("_expires");
						map.put("expires", new Date(expires).toString());

						map.put("email", email.toString());
						list.add(map);
					}
				}
			}
		}
		
		request.setAttribute("list", list);
		return "/viewer.jsp";
	}
	
}
