package com.hssa.ezybiz.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleAuthenticationService {

	final static Logger LOG = LoggerFactory.getLogger(GoogleAuthenticationService.class);

	private final String clientId = "926043173083-o45626pjm3ud740p45dqv19m00vnbr3a.apps.googleusercontent.com";

	private HttpTransport transport;

	private JsonFactory jsonFactory;
	private static final String ISSUER = "accounts.google.com";

	public boolean authenticateUserFromGoogle(String email, String googleIdToken, String googleAccessToken) {

		jsonFactory = JacksonFactory.getDefaultInstance();// new JacksonFactory();
		transport = new NetHttpTransport();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singleton(clientId))
				// .setClientIds(clientId)

				.setIssuer(ISSUER)
				// Specify the CLIENT_ID of the app that accesses the backend:
				// .setAudience(Collections.singletonList("944046804732-8l6lnmsb55oj5nsnlukra5g95sdvoe76.apps.googleusercontent.com"))
				// Or, if multiple clients access the backend:
				// .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		// (Receive idTokenString by HTTPS POST)

		GoogleIdToken idToken;
		try {
			idToken = verifier.verify(googleIdToken);
			if (idToken != null) {
				Payload payload = idToken.getPayload();

				// Print user identifier

				// String userId = payload.getUserId();
				// System.out.println("User ID: " + userId);

				// Get profile information from payload
				if (!payload.getEmail().equalsIgnoreCase(email) && !Boolean.valueOf(payload.getEmailVerified())) {
					return false;
				}
				// boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				/*
				 * String name = (String) payload.get("name"); String pictureUrl = (String)
				 * payload.get("picture"); String locale = (String) payload.get("locale");
				 * String familyName = (String) payload.get("family_name"); String givenName =
				 * (String) payload.get("given_name");
				 */
				// Use or store profile information
				// ...
				return true;
			} else {
				// System.out.println("Invalid ID token.");
				// return null if token is invalid.
				return false;
			}

		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
