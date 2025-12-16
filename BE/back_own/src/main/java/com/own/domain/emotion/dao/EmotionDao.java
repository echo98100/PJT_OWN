package com.own.domain.emotion.dao;

import java.util.List;

import com.own.domain.emotion.dto.model.EmotionType;

public interface EmotionDao {

	
	// 전체 리스트 조회 (db 연결 체크용)
	List<EmotionType> selectAllEmotionTypes();
		
		
    // 특정 포스트의 감정 태그 조회
    List<EmotionType> selectEmotionTypesByPostId(int postId);

    // post_emotion_type 테이블 insert
    void insertPostEmotionType(int postId, int typeId);
}
