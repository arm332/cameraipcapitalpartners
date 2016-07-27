package com.invprof.cameras.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Document;
import com.invprof.cameras.service.DocumentService;
import com.invprof.cameras.service.DocumentServiceImpl;
import com.invprof.cameras.util.Util;

public class DocumentAction extends ActionAdapter {
	private DocumentService service = new DocumentServiceImpl();
	
	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Document> list = service.list();
		request.setAttribute("list", list);
		return "/document.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		Document item = service.load(id);
		request.setAttribute("item", item);
		return "/document.jsp";
	}
}
