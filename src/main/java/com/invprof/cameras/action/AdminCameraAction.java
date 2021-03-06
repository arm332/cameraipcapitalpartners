package com.invprof.cameras.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Camera;
import com.invprof.cameras.service.CameraService;
import com.invprof.cameras.service.CameraServiceImpl;
import com.invprof.cameras.util.Util;

public class AdminCameraAction extends ActionAdapter {
	private CameraService service = new CameraServiceImpl();

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Camera> list = service.list();
		request.setAttribute("list", list);
		return "/admin/camera.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		Camera item = service.load(id);
		request.setAttribute("item", item);
		return "/admin/camera.jsp";
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		
		aux = request.getParameter("interval");
		Integer interval = Util.tryParseInt(aux);
		if (interval == null) interval = 0;
		
		aux = request.getParameter("position");
		Integer position = Util.tryParseInt(aux);
		Camera item = new Camera(id, title, description, url, interval, position);
		service.save(item);
		return "redirect:/admin/camera";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException  {
		String aux = request.getParameter("id");
		Long id = Util.tryParseLong(aux);
		service.delete(id);
		return "redirect:/admin/camera";
	}
	
}