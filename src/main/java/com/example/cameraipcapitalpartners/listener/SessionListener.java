package com.example.cameraipcapitalpartners.listener;

import java.util.logging.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.example.cameraipcapitalpartners.model.Viewer;
import com.example.cameraipcapitalpartners.service.ViewerService;
import com.example.cameraipcapitalpartners.service.ViewerServiceImpl;

public class SessionListener implements HttpSessionListener {
	private static final Logger log = Logger.getLogger(SessionListener.class.getName());
	private static int sessionCount = 0;
	
	public static int getSessionCount(){
		return sessionCount;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount++;
		}
		
		log.info("Session created: " + event.getSession().getId()
				+ "; e-mail: " + event.getSession().getAttribute("email")
				+ "; count: " + getSessionCount()
				+ "; timeout: " + event.getSession().getMaxInactiveInterval());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount--;
		}
		
		log.info("Session destroyed: " + event.getSession().getId()
				+ "; e-mail: " + event.getSession().getAttribute("email")
				+ "; count: " + getSessionCount());
		
		// NOT WORKING!
		//ViewerService viewerService = new ViewerServiceImpl();
		//viewerService.delete(event.getSession().getId());
	}

}
