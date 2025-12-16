package com.own.domain.user.dto.response;

import com.own.domain.user.dto.model.User;

public class UserTierResponse {
	
	private int tierLevel;
	
	public UserTierResponse() {
		
	}
	
	public UserTierResponse(User user) {
		this.tierLevel = user.getTierLevel();
	}

	public int getTierLevel() {
		return tierLevel;
	}

	public void setTierLevel(int tierLevel) {
		this.tierLevel = tierLevel;
	}
	
	
}
