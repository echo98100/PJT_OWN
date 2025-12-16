package com.own.domain.music.dao;

import com.own.domain.music.dto.model.Music;
import com.own.domain.music.dto.request.MusicSaveRequest;

public interface MusicDao {
	
	//특정 음악이 db에 존재하는지 조회
	Music findBySpotifyTrackId(String spotifyTrackId);
	
	//DB에 없는 음악은 신규 저장 
	void insertMusic(MusicSaveRequest request);
}
