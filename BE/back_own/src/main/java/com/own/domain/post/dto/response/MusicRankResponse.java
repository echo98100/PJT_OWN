package com.own.domain.post.dto.response;

public class MusicRankResponse {
	
	private String workoutName;
	private String musicTitle;
	private String artist;
	private String albumImg;
	private String previewUrl;
	
	public MusicRankResponse() {
		
	}
	
	public MusicRankResponse(String workoutName, String musicTitle, String artist, String albumImg, String previewUrl) {
		this.workoutName = workoutName;
		this.musicTitle = musicTitle;
		this.artist = artist;
		this.albumImg = albumImg;
		this.previewUrl = previewUrl;
	}
	
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public String getMusicTitle() {
		return musicTitle;
	}
	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbumImg() {
		return albumImg;
	}
	public void setAlbumImg(String albumImg) {
		this.albumImg = albumImg;
	}
	public String getPreviewUrl() {
		return previewUrl;
	}
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	
	
}
