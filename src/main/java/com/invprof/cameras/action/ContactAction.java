package com.invprof.cameras.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ServiceException;

public class ContactAction extends ActionAdapter {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {

			// Go to the Google Developers Console, open your application's
			// credentials page, and copy the client ID and client secret.
			// Then paste them into the following code.
			String clientId = "YOUR_CLIENT_ID";
			String clientSecret = "YOUR_CLIENT_SECRET";
			
			// Or your redirect URL for web based applications.
			//String redirectUrl = "urn:ietf:wg:oauth:2.0:oob";
			//String scope = "https://www.googleapis.com/auth/contacts.readonly";
			String redirectUrl = "https://cameraipcapitalpartners.appspot.com/contact";
			String scope = "https://www.google.com/m8/feeds/";

			NetHttpTransport httpTransport = new NetHttpTransport();
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
					String authorizationUrl = new GoogleBrowserClientRequestUrl(
							clientId, redirectUrl, Arrays.asList(scope))
							.setResponseTypes(Arrays.asList("code"))
							.build();
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
		        		httpTransport, jsonFactory, clientId, clientSecret, code, redirectUrl)
		        		.execute();
		        // End of Step 2 <--
		        
		        // Set the credential from response token
		        credential.setFromTokenResponse(tokenResponse);
		        
		        // Store the credential
		        session.setAttribute("accessToken", credential.getAccessToken());
		        session.setAttribute("refreshToken", credential.getRefreshToken());
			}
			else {
				credential.setAccessToken(accessToken);
		        credential.setRefreshToken(refreshToken);
			}
        
			response.setContentType("text/html");
			StringBuilder sb = new StringBuilder();
			PrintWriter out = response.getWriter();
			out.write("<p>START");
			
			ContactsService contactsService = new ContactsService("Camera-IP-2016");
			contactsService.setOAuth2Credentials(credential);

			URL contactsUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
			ContactFeed contactsFeed = contactsService.getFeed(contactsUrl, ContactFeed.class);
			int contactsCount = contactsFeed.getEntries().size();

			out.write("<p>contactsCount: " + contactsCount);
			
			for (int i = 0; i < contactsCount; i++) {
				ContactEntry entry = contactsFeed.getEntries().get(i);
				//String contectId = entry.getId();
				sb.append("<p>" + i);
				
				Name name = entry.getName();
				
				if (name != null) {
					if (name.hasFullName()) {
						String fullName = name.getFullName().getValue();
						sb.append("; fullName: " + fullName);
					}
					
					if (name.hasGivenName()) {
						String givenName = name.getGivenName().getValue();
						sb.append("; givenName: " + givenName);
					}

					if (name.hasAdditionalName()) {
						String additionalName = name.getAdditionalName().getValue();
						sb.append("; additionalName: " + additionalName);
					}
					
					if (name.hasFamilyName()) {
						String familyName = name.getFamilyName().getValue();
						sb.append("; familyName: " + familyName);
					}
				}
				else {
					sb.append("; No name found");
				}
				
				if (entry.hasEmailAddresses()) {
					List<Email> emails = entry.getEmailAddresses();
					
					for (Email email : emails) {
						String address = email.getAddress();
						sb.append("; address: " + address);
					}
				}
				else {
					sb.append("; no e-mail found");
				}
				
				for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
					String groupHref = group.getHref();
					sb.append("; groupHref: " + groupHref);
				}
			}

			URL groupsUrl = new URL("https://www.google.com/m8/feeds/groups/default/full");
			ContactGroupFeed groupsFeed = contactsService.getFeed(groupsUrl, ContactGroupFeed.class);
			int groupsCount = groupsFeed.getEntries().size();
			
			out.write("<p>groupsCount: " + contactsCount);

			for (int i = 0; i < groupsCount; i++) {
				ContactGroupEntry entry = groupsFeed.getEntries().get(i);
				String groupId = entry.getId();
				sb.append("<p>" + i + "; " + groupId);
				
				String groupName = entry.getTitle().getPlainText();
				sb.append("; " + groupName);
			}

			out.write(sb.toString());
			out.write("<p>END</p>");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
