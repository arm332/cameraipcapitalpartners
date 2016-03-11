package com.example.cameraipcapitalpartners.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.cameraipcapitalpartners.model.Profile;
import com.example.cameraipcapitalpartners.service.ProfileService;
import com.example.cameraipcapitalpartners.service.ProfileServiceImpl;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginAction extends ActionAdapter {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String view = "/login.jsp";
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		if (user != null) {
			String email = user.getEmail().toLowerCase();
			String domain = email.substring(email.indexOf('@'));
			ProfileService profileService = new ProfileServiceImpl();
			
			// First try to login by domain, them by e-mail
			Profile profile = profileService.load(domain);
			if (profile == null) profile = profileService.load(email);
			
			if (profile != null) {
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				return "redirect:/";
			}
			
			view += "?error";
		}

		String loginUrl = userService.createLoginURL("/login");
		request.setAttribute("loginUrl", loginUrl);
		return view;
	}
}
