package com.invprof.cameras.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Document;
import com.invprof.cameras.service.DocumentService;
import com.invprof.cameras.service.DocumentServiceImpl;

public class HomeAction extends ActionAdapter {
	private DocumentService service = new DocumentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Document> list = service.list();
		request.setAttribute("list", list);
		return "/home.jsp";
	}
}
