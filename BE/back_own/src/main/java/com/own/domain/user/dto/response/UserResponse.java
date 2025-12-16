package com.own.domain.user.dto.response;

public class UserResponse {

	private String name;
	private String nickname;
	private String email;
	private String profileImg;
	private int tierLevel;
	
	public UserResponse() {
		
	}

	public UserResponse(String name, String nickname, String email, String profileImg, int tierLevel) {
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.profileImg = profileImg;
		this.tierLevel = tierLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public int getTierLevel() {
		return tierLevel;
	}

	public void setTierLevel(int tierLevel) {
		this.tierLevel = tierLevel;
	}

	
}
