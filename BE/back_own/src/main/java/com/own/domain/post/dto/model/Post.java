package com.own.domain.post.dto.model;

import java.time.LocalDateTime;

public class Post {
	private int postId;
	private int userId;
	private String caption;
	private int musicId;
	private LocalDateTime createdAt;
	
	public Post(int postId, int userId, String caption, int musicId, LocalDateTime createdAt) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.caption = caption;
		this.musicId = musicId;
		this.createdAt = createdAt;
	}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userId=" + userId + ", caption=" + caption + ", musicId=" + musicId
				+ ", createdAt=" + createdAt + "]";
	}
	
}
