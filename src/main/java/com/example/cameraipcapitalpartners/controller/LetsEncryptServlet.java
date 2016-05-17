package com.example.cameraipcapitalpartners.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LetsEncryptServlet extends HttpServlet {
	private static final long serialVersionUID = -7339246115145842372L;
	
	private static final String CHALLENGE_NAME = "QLMQxGJAy31mfhU4C4jEjcCPq-xIok4RR8uuG0SBYbM";
	private static final String CHALLENGE_VALUE = "QLMQxGJAy31mfhU4C4jEjcCPq-xIok4RR8uuG0SBYbM.CQvaglKJmJOI544HySzODNnhSQFaaM8c9ceDfIhz7ho";
	
	public LetsEncryptServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getRequestURI().substring("/.well-known/acme-challenge/".length());
		
        if (CHALLENGE_NAME.equals(id)) {
            response.setContentType("text/plain");
            response.getOutputStream().print(CHALLENGE_VALUE);
        }
        else {
            response.sendError(404);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
