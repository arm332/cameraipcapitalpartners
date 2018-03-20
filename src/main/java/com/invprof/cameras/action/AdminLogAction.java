package com.invprof.cameras.action;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Acme;
import com.invprof.cameras.model.Log;
import com.invprof.cameras.service.AcmeService;
import com.invprof.cameras.service.AcmeServiceImpl;
import com.invprof.cameras.service.LogService;
import com.invprof.cameras.service.LogServiceImpl;
import com.invprof.cameras.util.Util;

public class AdminLogAction extends ActionAdapter {
	private LogService service = new LogServiceImpl();

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Log> list = service.list();
		request.setAttribute("list", list);
		return "/admin/log.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		Log item = service.load(id);
		request.setAttribute("item", item);
		return "/admin/log.jsp";
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		aux = request.getParameter("date");
		Date date = Util.tryParseDate(aux);
		String email = request.getParameter("email");
		String notes = request.getParameter("notes");
		Log item = new Log(id, date, email, notes);
		service.save(item);
		return "redirect:/admin/log";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		service.delete(id);
		return "redirect:/admin/log";
	}
	
}