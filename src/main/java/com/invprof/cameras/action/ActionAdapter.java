package com.invprof.cameras.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionAdapter implements Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if (request.getParameter("delete") != null) {
			return delete(request, response);
		}
		
		if (request.getParameter("save") != null) {
			return save(request, response);
		}
		
		if (request.getParameter("load") != null) {
			return load(request, response);
		}
		
		//if (request.getParameter("list") != null) {}
		return list(request, response);
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		return null;
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		return null;
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		return null;
	}
}
