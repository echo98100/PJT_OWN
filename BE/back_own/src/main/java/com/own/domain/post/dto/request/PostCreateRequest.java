package com.own.domain.post.dto.request;

import java.util.*;

public class PostCreateRequest {
	private Integer postId; 
	private Integer userId;
	private Integer workoutTag;  // 선택 전 = null
	private List <Integer> emotionTags; 
	private Integer musicId; 
	private String caption;
	private String spotifyTrackUrl;
	
    public PostCreateRequest() {
    }
	
	public PostCreateRequest(Integer postId, Integer workoutTag, List<Integer> emotionTags, Integer musicId, String caption,
			String spotifyTrackUrl, Integer userId) { 
		super();
		this.postId = postId;
		this.workoutTag = workoutTag;
		this.emotionTags = emotionTags;
		this.musicId = musicId;
		this.caption = caption;
		this.spotifyTrackUrl = spotifyTrackUrl;
		this.userId = userId; 
	}
	
	public Integer getPostId() {
	    return postId;
	}

	public void setPostId(Integer postId) {
	    this.postId = postId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getWorkoutTag() {
		return workoutTag;
	}
	public void setWorkoutTag(Integer workoutTag) {
		this.workoutTag = workoutTag;
	}
	public List<Integer> getEmotionTags() {
		return emotionTags;
	}
	public void setEmotionTags(List<Integer> emotionTags) {
		this.emotionTags = emotionTags;
	}
	public Integer getMusicId() {
		return musicId;
	}
	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSpotifyTrackUrl() {
		return spotifyTrackUrl;
	}
	public void setSpotifyTrackUrl(String spotifyTrackUrl) {
		this.spotifyTrackUrl = spotifyTrackUrl;
	}

	@Override
	public String toString() {
		return "PostCreateRequest [userId=" + userId + ", postId=" + postId + ", workoutTag=" + workoutTag + ", emotionTags=" + emotionTags
				+ ", musicId=" + musicId + ", caption=" + caption + ", spotifyTrackUrl=" + spotifyTrackUrl + "]";
	}

	
	

}
