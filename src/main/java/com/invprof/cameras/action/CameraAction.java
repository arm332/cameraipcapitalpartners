package com.invprof.cameras.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Camera;
import com.invprof.cameras.service.CameraService;
import com.invprof.cameras.service.CameraServiceImpl;
import com.invprof.cameras.util.Util;

public class CameraAction extends ActionAdapter {
	private CameraService service = new CameraServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		List<Long> order = new ArrayList<>();
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("order")) {
					final String value = cookie.getValue();
					
					if (value != null) {
						String[] idents = value.split("\\|");
						
						for (String ident : idents) {
							Long id = Util.tryParseLong(ident);
							
							if (id != null) {
								order.add(id);
							}
						}
					}
					
					break;
				}
			}
		}

		List<Camera> list = service.list();
		List<Camera> found = new ArrayList<>(list.size());
		
		if (order.size() != 0) {
			for (Long id : order) {
				Camera aux = new Camera(id);
				int index = list.indexOf(aux);
				
				if (index > -1) {
					found.add(list.get(index));
					list.remove(index);
				}
			}
		}

		found.addAll(list);
		request.setAttribute("list", found);
		request.setAttribute("refresh", true);
		//return "/camera.jsp?refresh";
		return "/camera.jsp";
	}
}
