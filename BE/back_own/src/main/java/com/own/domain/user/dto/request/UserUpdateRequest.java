package com.own.domain.user.dto.request;

public class UserUpdateRequest {
	
	private String profileImage;
	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;
	
	
	public UserUpdateRequest() {
		
	}
	
	public UserUpdateRequest(String profileImage, String oldPassword, String newPassword, String newPasswordConfirm) {
		this.profileImage = profileImage;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordConfirm = newPasswordConfirm;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}

	
	
}
