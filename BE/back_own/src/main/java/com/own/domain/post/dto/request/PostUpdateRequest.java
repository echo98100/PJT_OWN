package com.own.domain.post.dto.request;

import java.util.List;

public class PostUpdateRequest {
	private String caption;
	private List <Integer> emotionTags;
	
	public PostUpdateRequest(String caption, List<Integer> emotionTags) {
		super();
		this.caption = caption;
		this.emotionTags = emotionTags;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public List<Integer> getEmotionTags() {
		return emotionTags;
	}
	public void setEmotionTags(List<Integer> emotionTags) {
		this.emotionTags = emotionTags;
	}
	@Override
	public String toString() {
		return "PostUpdateRequest [caption=" + caption + ", emotionTags=" + emotionTags + "]";
	} 
}
