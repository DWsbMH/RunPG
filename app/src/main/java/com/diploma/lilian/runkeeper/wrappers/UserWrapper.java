package com.diploma.lilian.runkeeper.wrappers;

import com.diploma.lilian.runkeeper.GraphConstants;
import com.diploma.lilian.runkeeper.models.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserWrapper {
	private String authToken;
	
	public UserWrapper(String authToken) {
		this.authToken = authToken;
	}
	
	public User getUser() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String url = GraphConstants.REST_URL + "/user";
		HttpURLConnection conn  = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestProperty("Accept", GraphConstants.MEDIA_USER);
		conn.setRequestProperty("Authorization", "Bearer " + authToken);

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		User user = null;
		user = mapper.readValue(rd, User.class);
		conn.disconnect();
		return user;
	}
}