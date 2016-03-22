package com.example.cameraipcapitalpartners.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.cameraipcapitalpartners.model.Viewer;
import com.example.cameraipcapitalpartners.service.ViewerService;
import com.example.cameraipcapitalpartners.service.ViewerServiceImpl;

public class ViewerAction extends ActionAdapter {
	private ViewerService service = new ViewerServiceImpl(); 

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Viewer> list = service.list();
		request.setAttribute("list", list);
		return "/viewer.jsp";
	}
	
}
