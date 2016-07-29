package com.invprof.cameras.action;

import java.util.Arrays;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.invprof.cameras.model.Profile;
import com.invprof.cameras.service.ProfileService;
import com.invprof.cameras.service.ProfileServiceImpl;
import com.invprof.cameras.util.Util;

public class Login2Action extends ActionAdapter {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Properties properties = Util.getProperties();
		String appName = properties.getProperty("appName");
		String clientId = properties.getProperty("clientId");
		String clientSecret = properties.getProperty("clientSecret");
		String[] scopes = properties.getProperty("scopes").split(",");

		String scheme = request.getScheme() + "://";
	    String serverName = request.getServerName();
	    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	    String redirectUri = scheme + serverName + serverPort + "/login2";
	    
		String email = null;
		String name = null;
		
		try {
			
			HttpTransport httpTransport = new NetHttpTransport();
			JacksonFactory jsonFactory = new JacksonFactory();
			String code = request.getParameter("code");
			
			GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(httpTransport,
					jsonFactory, clientId, clientSecret, code, redirectUri).execute();
			String accessToken = tokenResponse.getAccessToken();
			
			GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
			
			Oauth2 oauth2 = new Oauth2.Builder(httpTransport, jsonFactory, credential)
					.setApplicationName(appName).build();
			
			Userinfoplus userInfo = oauth2.userinfo().get().execute();
			//String tmp = userInfo.toPrettyString();
			email = userInfo.getEmail();
			name = userInfo.getName();
			
		} catch (Exception e) {
			//e.printStackTrace();
			String authorizationUrl = new GoogleAuthorizationCodeRequestUrl(clientId,
					redirectUri, Arrays.asList(scopes))
					//.setApprovalPrompt("force")
					//.setState("mystate")
					.build();
			request.setAttribute("loginUrl", authorizationUrl);
			return "/login.jsp";
		}
		
		// First try to login by e-mail (to get user status)
		ProfileService profileService = new ProfileServiceImpl();
		Profile profile = profileService.load(email);
		
		// Then try by domain (non privileged user)
		if (profile == null) {
			String domain = email.substring(email.indexOf('@'));
			profile = profileService.load(domain);
			
			// User not found on database
			if (profile == null) {
				return "/login.jsp?error";
			}
		}
		
		int status = profile.getStatus();
		
		HttpSession session = request.getSession();
		session.setAttribute("status", status);
		session.setAttribute("email", email);
		session.setAttribute("name", name);
		
		return "redirect:/camera";
	}
}
