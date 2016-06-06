package com.invprof.cameras.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Profile;
import com.invprof.cameras.service.ProfileService;
import com.invprof.cameras.service.ProfileServiceImpl;
import com.invprof.cameras.util.Util;

public class AdminProfileAction extends ActionAdapter {
	private ProfileService service = new ProfileServiceImpl();

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Profile> list = service.list();
		request.setAttribute("list", list);
		return "/admin/profile.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		Profile item = service.load(email);
		request.setAttribute("item", item);
		return "/admin/profile.jsp";
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String sStatus = request.getParameter("status");
		Integer status = Util.tryParseInt(sStatus);
		if (status == null || status < 0) status = 0;
		Profile item = new Profile(email, name, status);
		service.save(item);
		// Because we are editing identities (to avoid NOT NULL and reduce costs)
		String old = request.getParameter("old");
		if (!old.isEmpty() && !old.equals(email)) 
			service.delete(old);
		return "redirect:/admin/profile";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		service.delete(email);
		return "redirect:/admin/profile";
	}
}
