package com.invprof.cameras.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Log;
import com.invprof.cameras.service.LogService;
import com.invprof.cameras.service.LogServiceImpl;
import com.invprof.cameras.util.Util;

public class AdminLogAction extends ActionAdapter {
	private LogService service = new LogServiceImpl();
	private final static Integer LIMIT = 25;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if (request.getParameter("download") != null) {
			return download(request, response);
		}
		
		return super.execute(request, response);
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String aux = request.getParameter("pg");
		Integer pg = Util.tryParseInt(aux);
		if (pg == null || pg < 0) pg = 0;
		Integer offset = pg * LIMIT; 
		List<Log> list = service.list(LIMIT, offset);
		request.setAttribute("list", list);
		request.setAttribute("page", pg);
		request.setAttribute("prev", pg - 1);
		request.setAttribute("next", pg + 1);
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
	
	private String download(HttpServletRequest request, HttpServletResponse response) {
		//String aux = request.getParameter("id");
		//Long id = Util.tryParseLong(aux);
		List<Log> list = service.list(0, 0);
		String data = "";
		
		if (list != null) {
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < list.size() - 1 ; i++) {
				Log log = list.get(i);
				sb.append("\"");
				sb.append(log.id.toString());
				sb.append("\",\"");
				sb.append(log.date.toString());
				sb.append("\",\"");
				sb.append(log.email.toString());
				sb.append("\",\"");
				sb.append(Objects.toString(log.notes, "").replaceAll("\"","\\\""));
		        sb.append("\"\n");
		    }
			
			data = sb.toString();
		}
		
        response.setHeader("Content-disposition","attachment; filename=\"log.csv\"");
		//response.setHeader("Content-Transfer-Encoding", "application/octet-stream");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentType("application/octet-stream");
		//response.setContentType("application/x-download");  
		response.setContentLength((int) data.length());
		//response.setContentLength(-1);
        
		try {
			
			OutputStream out = response.getOutputStream();
			out.write(data.getBytes());
			out.flush();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

		return null;
	}
}