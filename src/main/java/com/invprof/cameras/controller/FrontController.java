package com.invprof.cameras.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.invprof.cameras.action.Action;
import com.invprof.cameras.action.ActionFactory;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		//System.out.println("path: " + path);
		
		if (!path.startsWith("/login") && !path.startsWith("/.well-known")) {
			HttpSession session = request.getSession(false);
			
			if (session == null || session.getAttribute("email") == null) {
				response.sendRedirect("/login");
				//response.sendError(403);
				return;
			}
			
			int status = (int) session.getAttribute("status");
			
			if (path.startsWith("/admin") && status != 1) {
				response.sendRedirect("/");
				//response.sendError(403);
				return;
			}
		}

		Action action = ActionFactory.getAction(path);
		
        if (action == null) {
            response.sendRedirect("/");
            //response.sendError(404);
            return;
        }
        
        String view = action.execute(request, response);
        
        if (view != null) {
            if (view.startsWith("redirect:")) {
                response.sendRedirect(view.substring(9));
            }
            else {
                request.getRequestDispatcher("/WEB-INF/view" + view).forward(request, response);
            }
        }
	}
}
