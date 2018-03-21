package com.invprof.cameras.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction extends ActionAdapter {
//	private static final Logger log = Logger.getLogger(LogoutAction.class.getName());
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
//			log.info("Session logout: " + session.getId() 
//				+ "; e-mail: " + session.getAttribute("email"));
			session.invalidate();
		}
		
		return "redirect:/login?logout";
	}
}
