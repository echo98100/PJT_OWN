package com.own.domain.post.dto.request;

import java.util.List;

public class PostSearchRequest {
	private Integer userId; // 특정 유저의 게시글/좋아요/북마크 조회할 때 사용
	private Integer lastPostId; // 무한스크롤 커서 (null이면 첫페이지)
	private Integer size; // 한번에 몇개 가져올지 개수

	private Boolean liked; // 좋아요 목록 요청인지
	private Boolean bookmarked; // 북마크 목록 요청인지
	
	public PostSearchRequest(Integer userId, Integer lastPostId, Integer size, Boolean liked, Boolean bookmarked) {
		super();
		this.userId = userId;
		this.lastPostId = lastPostId;
		this.size = size;
		this.liked = liked;
		this.bookmarked = bookmarked;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLastPostId() {
		return lastPostId;
	}
	public void setLastPostId(Integer lastPostId) {
		this.lastPostId = lastPostId;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Boolean getLiked() {
		return liked;
	}
	public void setLiked(Boolean liked) {
		this.liked = liked;
	}
	public Boolean getBookmarked() {
		return bookmarked;
	}
	public void setBookmarked(Boolean bookmarked) {
		this.bookmarked = bookmarked;
	}
	@Override
	public String toString() {
		return "PostSearchRequest [userId=" + userId + ", lastPostId=" + lastPostId + ", size=" + size + ", liked="
				+ liked + ", bookmarked=" + bookmarked + "]";
	}

	
}
