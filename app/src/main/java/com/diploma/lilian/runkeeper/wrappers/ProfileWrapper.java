package com.diploma.lilian.runkeeper.wrappers;

import com.diploma.lilian.runkeeper.GraphConstants;
import com.diploma.lilian.runkeeper.models.Profile;
import com.diploma.lilian.runkeeper.models.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class ProfileWrapper {
	private String authToken;
	
	public ProfileWrapper(String authToken) {
		this.authToken = authToken;
	}

	public Profile getProfile() throws Exception {
		//User object contains URLs
		User user = new UserWrapper(authToken).getUser();
		ObjectMapper mapper = new ObjectMapper();
		String url = GraphConstants.REST_URL + user.getProfile();
		HttpURLConnection conn  = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestProperty("Accept", GraphConstants.MEDIA_PROFILE);
		conn.setRequestProperty("Authorization", "Bearer " + authToken);

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		Profile profile = mapper.readValue(rd, Profile.class);
		conn.disconnect();
		return profile;
	}
	
}
