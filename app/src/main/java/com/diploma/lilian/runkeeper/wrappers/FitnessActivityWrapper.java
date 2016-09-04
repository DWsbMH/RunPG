package com.diploma.lilian.runkeeper.wrappers;

import com.diploma.lilian.runkeeper.GraphConstants;
import com.diploma.lilian.runkeeper.models.FitnessActivity;
import com.diploma.lilian.runkeeper.models.FitnessActivityFeed;
import com.diploma.lilian.runkeeper.models.User;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FitnessActivityWrapper {
	private String authToken;

	public FitnessActivityWrapper(String authToken) {
		this.authToken = authToken;
	}

	public FitnessActivityFeed getFitnessActivityFeed() throws Exception {
		//User object contains URLs
		User user = new UserWrapper(authToken).getUser();
		return getFitnessActivityFeed(user.getFitness_activities());
	}

	public FitnessActivityFeed getFitnessActivityFeed(String url) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpURLConnection conn  = (HttpURLConnection) new URL(GraphConstants.REST_URL + url).openConnection();
		conn.setRequestProperty("Accept", GraphConstants.MEDIA_FITNESS_ACTIVITY_FEED);
		conn.setRequestProperty("Authorization", "Bearer " + authToken);

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		FitnessActivityFeed fitnessActivityFeed = mapper.readValue(rd, FitnessActivityFeed.class);
		conn.disconnect();
		return fitnessActivityFeed;
	}

	public FitnessActivity getFitnessActivity(String url) throws Exception {
		ObjectMapper mapper = new ObjectMapper(); 
		url = GraphConstants.REST_URL + url;
		HttpURLConnection conn  = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestProperty("Accept", GraphConstants.MEDIA_FITNESS_ACTIVITY);
		conn.setRequestProperty("Authorization", "Bearer " + authToken);

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		BufferedReader rd = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		FitnessActivity fitnessActivity = mapper.readValue(rd, FitnessActivity.class);
		conn.disconnect();
		return fitnessActivity;
	}	

}
