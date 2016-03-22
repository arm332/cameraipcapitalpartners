package com.example.cameraipcapitalpartners.action;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.cameraipcapitalpartners.model.Viewer;
import com.example.cameraipcapitalpartners.service.ViewerService;
import com.example.cameraipcapitalpartners.service.ViewerServiceImpl;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LogoutAction extends ActionAdapter {
	private static final Logger log = Logger.getLogger(LogoutAction.class.getName());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		UserService userService = UserServiceFactory.getUserService();
		String logoutUrl = userService.createLogoutURL("/login?logout");
		HttpSession session = request.getSession();
		log.info("Session logout: " + session.getId() 
				+ "; e-mail: " + session.getAttribute("email"));
		ViewerService viewerService = new ViewerServiceImpl();
		viewerService.delete(session.getId());
		session.invalidate();
		return "redirect:" + logoutUrl;
	}
}
