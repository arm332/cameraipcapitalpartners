package com.invprof.cameras.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LogoutOpenIdAction extends ActionAdapter {
//	private static final Logger log = Logger.getLogger(LogoutOpenIdAction.class.getName());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		UserService userService = UserServiceFactory.getUserService();
		String logoutUrl = userService.createLogoutURL("/login?logout");
		HttpSession session = request.getSession(false);
		
		if (session != null) {
//			log.info("Session logout: " + session.getId() 
//				+ "; e-mail: " + session.getAttribute("email"));
			session.invalidate();
		}
		
		return "redirect:" + logoutUrl;
	}
}
