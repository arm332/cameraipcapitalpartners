package com.invprof.cameras.action;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.people.v1.People;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.ContactGroupMembership;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAction extends ActionAdapter {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
	    HttpTransport httpTransport = new NetHttpTransport();
	    JacksonFactory jsonFactory = new JacksonFactory();

	    // Go to the Google Developers Console, open your application's
	    // credentials page, and copy the client ID and client secret.
	    // Then paste them into the following code.
	    String clientId = "YOUR_CLIENT_ID";
	    String clientSecret = "YOUR_CLIENT_SECRET";

	    // Or your redirect URL for web based applications.
	    String redirectUrl =  "urn:ietf:wg:oauth:2.0:oob";
	    String scope = "https://www.googleapis.com/auth/contacts.readonly";

	    // Step 1: Authorize -->
	    String authorizationUrl = new GoogleBrowserClientRequestUrl(clientId, redirectUrl, Arrays.asList(scope)).build();

	    // Point or redirect your user to the authorizationUrl.
	    System.out.println("Go to the following link in your browser:");
	    System.out.println(authorizationUrl);
	    return "redirect:" + authorizationUrl;
/*
	    // Read the authorization code from the standard input stream.
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("What is the authorization code?");
	    String code = null;
		try {
			code = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // End of Step 1 <--

	    // Step 2: Exchange -->
	    GoogleTokenResponse tokenResponse = null;
		try {
			tokenResponse = new GoogleAuthorizationCodeTokenRequest(
			    httpTransport, jsonFactory, clientId, clientSecret, code, redirectUrl).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // End of Step 2 <--

	    GoogleCredential credential = new GoogleCredential.Builder()
	        .setTransport(httpTransport)
	        .setJsonFactory(jsonFactory)
	        .setClientSecrets(clientId, clientSecret)
	        .build()
	        .setFromTokenResponse(tokenResponse);

	    //People peopleService = new People.Builder(httpTransport, jsonFactory, credential).build();

	    return null;
*/
	}
}
