package com.own.domain.bookmark.dto.model;

import java.time.LocalDateTime;

public class Bookmark {
	// DB 매핑용
	private int userId;
	private int postId;
	private LocalDateTime createdAt;
	
	public Bookmark(int userId, int postId, LocalDateTime createdAt) {
		super();
		this.userId = userId;
		this.postId = postId;
		this.createdAt = createdAt;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Bookmark [userId=" + userId + ", postId=" + postId + ", createdAt=" + createdAt + "]";
	}
	
	

}
