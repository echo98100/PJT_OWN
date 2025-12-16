package com.own.domain.user.dto.request;

public class UserSignupRequest {
	
	private String name;
	private String nickname;
	private String email;
	private String password;
	private String profileImg;
	
	public UserSignupRequest() {
		
	}
	
	public UserSignupRequest(String name, String nickname, String email, String password, String profileImg) {
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.profileImg = profileImg;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	
	
	

}
