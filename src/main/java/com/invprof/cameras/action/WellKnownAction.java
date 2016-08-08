package com.invprof.cameras.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.invprof.cameras.model.Acme;
import com.invprof.cameras.service.AcmeService;
import com.invprof.cameras.service.AcmeServiceImpl;

public class WellKnownAction extends ActionAdapter {
	private static final String ACME_CHALLENGE = "/.well-known/acme-challenge/";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
        try {
    		String path = request.getPathInfo();
    		
    		if (path.startsWith(ACME_CHALLENGE)) {
    			String token = path.substring(ACME_CHALLENGE.length());
    			
    			if (token != null) {
        			AcmeService service = new AcmeServiceImpl();
        			List<Acme> list = service.list();
        			
        			for (Acme acme : list) {
        				if (token.equals(acme.getToken())) {
            	            response.setContentType("text/plain");
            	            response.getOutputStream().print(acme.getAuth());
            				return null;
        				}
        			}
    			}
    		}
    		
			response.sendError(404);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return null;
	}
}
