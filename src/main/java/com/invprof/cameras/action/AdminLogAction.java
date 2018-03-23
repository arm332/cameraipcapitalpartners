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
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if (request.getParameter("download") != null) {
			return download(request, response);
		}
		
		return super.execute(request, response);
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String page = request.getParameter("page");
		Integer p = Util.tryParseInt(page);
		if (p == null || p < 0) p = 0;
		System.out.println("p: " + p);
		Integer limit = 20;
		Integer offset = limit * p;
		String order = "-date";
		
		List<Log> list = service.list(Util.tryParseDate(start), 
				Util.tryParseDate(end), limit, offset, order);
		
		request.setAttribute("list", list);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("page", p);
		request.setAttribute("prev", p - 1);
		request.setAttribute("next", p + 1);
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
		Date date = Util.tryParseDateTime(aux);
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
		List<Log> list = service.list(null, null, null, null, null);
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