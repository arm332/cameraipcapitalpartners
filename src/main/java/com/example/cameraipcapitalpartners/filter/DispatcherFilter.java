package com.example.cameraipcapitalpartners.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class DispatcherFilter implements Filter {
	private static final String FRONT_CONTROLLER = "/FrontController";
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		//System.out.println("uri: " + uri);
		
		if (!uri.startsWith("/_ah") && !uri.startsWith("/setup")) {
			// forward the request to the front controller
			req.getRequestDispatcher(FRONT_CONTROLLER + uri).forward(request, response);
		}
		else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}
