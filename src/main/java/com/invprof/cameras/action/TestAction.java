package com.invprof.cameras.action;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.People;
import com.google.api.services.people.v1.model.ContactGroupMembership;
import com.google.api.services.people.v1.model.EmailAddress;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Membership;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestAction extends ActionAdapter {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	    try {
		    // Go to the Google Developers Console, open your application's
		    // credentials page, and copy the client ID and client secret.
		    // Then paste them into the following code.
		    String clientId = "YOUR_CLIENT_ID";
		    String clientSecret = "YOUR_CLIENT_SECRET";
	
		    // Or your redirect URL for web based applications.
		    String redirectUrl = "https://cameraipcapitalpartners.appspot.com/test"; // "urn:ietf:wg:oauth:2.0:oob";
		    String scope = "https://www.googleapis.com/auth/contacts.readonly";
		    
		    //
		    HttpTransport httpTransport = new NetHttpTransport();
		    JacksonFactory jsonFactory = new JacksonFactory();
			GoogleCredential credential = new GoogleCredential.Builder()
		            .setTransport(httpTransport)
		            .setJsonFactory(jsonFactory)
		            .setClientSecrets(clientId, clientSecret)
		            .build();
			
			HttpSession session = request.getSession();
			String accessToken = (String) session.getAttribute("accessToken");
			String refreshToken = (String) session.getAttribute("refreshToken");
	
			if (accessToken == null || refreshToken == null) {
			    
			    String code = request.getParameter("code");
		
			    if (code == null) {
				    // Step 1: Authorize -->
				    String authorizationUrl = new GoogleBrowserClientRequestUrl(clientId, redirectUrl, Arrays.asList(scope))
				    		.setResponseTypes(Arrays.asList("code")).build();
	
				    // Point or redirect your user to the authorizationUrl.
				    System.out.println("Go to the following link in your browser:");
				    System.out.println(authorizationUrl);
				    // End of Step 1 <--
				    
				    response.sendRedirect(authorizationUrl);
				    return null;
			    }
			    
			    String error = request.getParameter("error"); 
	
			    if (error != null) {
			    	
			    	// TODO - inform the user or redirect to "/"
			    	System.out.println("User authotization faild");
			    	return null;
			    	
			    }
		    
		        // Step 2: Exchange -->
			    GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
		    		    httpTransport, jsonFactory, clientId, clientSecret, code, redirectUrl).execute();
		        // End of Step 2 <--
			    
			    // Get the credential from response token
		        credential.setFromTokenResponse(tokenResponse);
		        
		        // Store the credential
				session.setAttribute("accessToken", credential.getAccessToken());
				session.setAttribute("refreshToken", credential.getRefreshToken());
		    	
			}
			else {
				
				credential.setAccessToken(accessToken);
				credential.setRefreshToken(refreshToken);
				
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");

			People peopleService = new People.Builder(httpTransport, jsonFactory, credential).build();
			
			ListConnectionsResponse peopleResponse = peopleService.people().connections()
	                .list("people/me")
	                .setPageSize(10)
	                //.setSortOrder("FIRST_NAME_ASCENDING")
	                //.setRequestMaskIncludeField("person.names,person.emailAddresses, contactGroupMembership")
	                .execute();
			
			List<Person> connections = peopleResponse.getConnections();
			
			if (connections != null && connections.size() > 0) {
				sb.append("<table border=\"1\">");
				
				for (Person person : connections) {
					sb.append("<tr>");
					sb.append("<td>");
					
					List<Name> names = person.getNames();
					
					if (names != null && names.size() > 0) {
						for (Name name : names) {
							sb.append(name.getDisplayName());
							sb.append("; ");
						}
					}
					else {
						sb.append("No names available for connection.");
					}
					
					sb.append("</td>");
					sb.append("<td>");
					
					List<EmailAddress> emailAdresses = person.getEmailAddresses();
					
					if (emailAdresses != null && emailAdresses.size() > 0) {
						for (EmailAddress emailAdress : emailAdresses) {
							sb.append(emailAdress.getValue());
							sb.append("; ");
						}
					}
					else {
						sb.append("No e-mail adresses available for connection.");
					}
					
					sb.append("</td>");
					sb.append("<td>");
					
					List<Membership> memberships = person.getMemberships();
					
					if (memberships != null && memberships.size() > 0) {
						for (Membership membership : memberships) {
							ContactGroupMembership contactGroupMembership = membership.getContactGroupMembership();
							
							if (contactGroupMembership != null) {
								sb.append(contactGroupMembership.getContactGroupId());
								sb.append("; ");
							}
						}
					}
					else {
						sb.append("No memberships available for connection.");
					}
					
					sb.append("</td>");
					sb.append("</tr>");
				}
				
				sb.append("</table>");
			}
			else {
				sb.append("No connections found.");
			}
	        
			sb.append("</html>");
	        response.setContentType("text/html");
	        response.getWriter().write(sb.toString());
	        
	    }
	    catch (IOException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }

	    return null;
	}
	
    /*
	        out.println("<html>");
	        out.println("<a href=\""+authorizationUrl+"\">TEST</a>");
	        out.println("<p>Code: " + code);
	        out.println("<p>Error: " + error);
	        out.println("<p>Token: " + token);
	        out.println("<p>RokenResponse: " + tokenResponse);
	        out.println("<p>Credential: " + credential);
	        
	        out.println("<p>END");
	        out.println("</html>");
	//return "redirect:" + authorizationUrl;
    response.setContentType("text/html");
	PrintWriter out = response.getWriter();
    //out.println("<html>");
    out.println("<a href=\""+authorizationUrl+"\">TEST</a>");
    //out.println("</html>");

		    // Read the authorization code from the standard input stream.
		    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    System.out.println("What is the authorization code?");
		    String code = in.readLine();
		    
		    System.out.println("code: " + code);

    if (code == null) {
    	return null;
    }
    
    // Step 2: Exchange -->
    GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
		    httpTransport, jsonFactory, clientId, clientSecret, code, redirectUrl).execute();
    // End of Step 2 <--

    GoogleCredential credential = new GoogleCredential.Builder()
        .setTransport(httpTransport)
        .setJsonFactory(jsonFactory)
        .setClientSecrets(clientId, clientSecret)
        .build()
        .setFromTokenResponse(tokenResponse);

    if (credential == null) {
    	//out.println("<p>NULL</p>");
    }
    
    //out.println("<p>END</p>");
    
    //People peopleService = new People.Builder(httpTransport, jsonFactory, credential).build();
	try {
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
// https://developers.google.com/api-client-library/java/google-api-java-client/oauth2
// http://stackoverflow.com/questions/13450460/how-to-get-authorization-code-for-googleauthorizationcodetokenrequest-in-java
// 
// https://gitlab.com/medvedutka/ficheeps/blob/ae695dc8853535ecf257254ca85b01eca3f05b23/src/main/java/ficheeps/server/auth/OAuth2Servlet.java
// http://stackoverflow.com/questions/12573635/how-to-get-access-token-from-googlecredential

*/

}
