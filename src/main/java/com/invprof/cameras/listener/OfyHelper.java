package com.invprof.cameras.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.invprof.cameras.model.Camera;
import com.invprof.cameras.model.Profile;
import com.invprof.cameras.model.Project;

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
	}

	public void contextDestroyed(ServletContextEvent event) {
		// App Engine does not currently invoke this method.
	}
	
}
