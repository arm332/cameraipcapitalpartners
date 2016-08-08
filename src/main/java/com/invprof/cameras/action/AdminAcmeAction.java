package com.invprof.cameras.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Acme;
import com.invprof.cameras.service.AcmeService;
import com.invprof.cameras.service.AcmeServiceImpl;
import com.invprof.cameras.util.Util;

public class AdminAcmeAction extends ActionAdapter {
	private AcmeService service = new AcmeServiceImpl();

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Acme> list = service.list();
		request.setAttribute("list", list);
		return "/admin/acme.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		Acme item = service.load(id);
		request.setAttribute("item", item);
		return "/admin/acme.jsp";
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		String domain = request.getParameter("domain");
		String token = request.getParameter("token");
		String auth = request.getParameter("auth");
		Acme item = new Acme(id, domain, token, auth);
		service.save(item);
		return "redirect:/admin/acme";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		service.delete(id);
		return "redirect:/admin/acme";
	}
	
}