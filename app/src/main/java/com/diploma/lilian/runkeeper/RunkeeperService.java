package com.diploma.lilian.runkeeper;

import com.diploma.lilian.runkeeper.wrappers.FitnessActivityWrapper;
import com.diploma.lilian.runkeeper.wrappers.ProfileWrapper;
import com.diploma.lilian.runkeeper.wrappers.UserWrapper;

public class RunkeeperService {
	public FitnessActivityWrapper fitnessActivityWrapper;
	public ProfileWrapper profileWrapper;
	public UserWrapper userWrapper;

	private String authToken;
	
	public RunkeeperService(String authToken) {
		this.authToken = authToken;
		fitnessActivityWrapper = new FitnessActivityWrapper(authToken);
		//profileWrapper = new ProfileWrapper(authToken);
		//userWrapper = new UserWrapper(authToken);
	}
}
