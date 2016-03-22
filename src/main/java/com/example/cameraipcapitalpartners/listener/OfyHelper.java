package com.example.cameraipcapitalpartners.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.example.cameraipcapitalpartners.model.Camera;
import com.example.cameraipcapitalpartners.model.Profile;
import com.example.cameraipcapitalpartners.model.Project;
import com.example.cameraipcapitalpartners.model.Viewer;
import com.googlecode.objectify.ObjectifyService;

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		// This will be invoked as part of a warmup request, or the first user
		// request if no warmup request.
		ObjectifyService.register(Project.class);
		ObjectifyService.register(Profile.class);
		//ObjectifyService.register(Product.class);
		ObjectifyService.register(Camera.class);
		ObjectifyService.register(Viewer.class);
	}

	public void contextDestroyed(ServletContextEvent event) {
		// App Engine does not currently invoke this method.
	}
	
}
