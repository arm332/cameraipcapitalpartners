package com.invprof.cameras.action;

import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.invprof.cameras.model.Profile;
import com.invprof.cameras.service.ProfileService;
import com.invprof.cameras.service.ProfileServiceImpl;

public class LoginOpenIdAction extends ActionAdapter {
	private static final Logger log = Logger.getLogger(LoginOpenIdAction.class.getName());

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String view = "/login.jsp";
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user != null) {
			String email = user.getEmail().toLowerCase();
			String domain = email.substring(email.indexOf('@'));
			ProfileService profileService = new ProfileServiceImpl();
			
			// First try to login by e-mail (to get user status),
			// then by domain
			Profile profile = profileService.load(email);
			
			if (profile == null) 
				profile = profileService.load(domain);
			
			if (profile != null) {
				int status = profile.getStatus();
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("status", status);
				log.info("Session login: " + session.getId() 
					+ "; e-mail: " + session.getAttribute("email")
					+ "; status: " + session.getAttribute("status"));
				return "redirect:/camera";
			}
			
			view += "?error";
		}

		String loginUrl = userService.createLoginURL("/login");
		request.setAttribute("loginUrl", loginUrl);
		return view;
	}
	
}
