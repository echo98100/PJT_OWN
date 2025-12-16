package com.own.domain.emotion.dto.model;

public class EmotionType {
	
	private int emotionTypeId;
	private String emotionName;
	
	public EmotionType() {
		
	}

	public EmotionType(int emotionTypeId, String emotionName) {
		this.emotionTypeId = emotionTypeId;
		this.emotionName = emotionName;
	}

	public int getEmotionTypeId() {
		return emotionTypeId;
	}

	public void setEmotionTypeId(int emotionTypeId) {
		this.emotionTypeId = emotionTypeId;
	}

	public String getEmotionName() {
		return emotionName;
	}

	public void setEmotionName(String emotionName) {
		this.emotionName = emotionName;
	}
	
	

}
