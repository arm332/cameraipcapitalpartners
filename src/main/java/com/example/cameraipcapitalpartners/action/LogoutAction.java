package com.example.cameraipcapitalpartners.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LogoutAction extends ActionAdapter {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		UserService userService = UserServiceFactory.getUserService();
		String logoutUrl = userService.createLogoutURL("/login?logout");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:" + logoutUrl;
	}
}
