package com.own.domain.music.dto.request;

public class MusicRequest {
	//Spotify API 호출할 때만 사용
	private String query;
	
	public MusicRequest(String query) {
		super();
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "MusicRequest [query=" + query + "]";
	}

	
	
}
