package com.example.cameraipcapitalpartners.action;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.cameraipcapitalpartners.model.Profile;
import com.example.cameraipcapitalpartners.service.ProfileService;
import com.example.cameraipcapitalpartners.service.ProfileServiceImpl;

public class ProfileAction extends ActionAdapter {
	private ProfileService service = new ProfileServiceImpl();

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Profile> list = service.list();
		request.setAttribute("list", list);
		return "/profile.jsp";
	}

	@Override
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		Profile item = service.load(email);
		request.setAttribute("item", item);
		return "/profile.jsp";
	}

	@Override
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		Profile item = new Profile(email, name);
		service.save(item);
		// Because we are editing identities (to avoid NOT NULL and reduce costs)
		String old = request.getParameter("old");
		if (!old.isEmpty() && !old.equals(email)) 
			service.delete(old);
		return "redirect:/profile";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		service.delete(email);
		return "redirect:/profile";
	}
}
