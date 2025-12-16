package com.own.domain.emotion.service;

import java.util.List;

import com.own.domain.emotion.dto.model.EmotionType;
import com.own.domain.emotion.dto.response.EmotionTypeResponse;

public interface EmotionService {
	
	List<EmotionType> getAllEmotionTypes();

    List<EmotionTypeResponse> getEmotionTypesByPostId(int postId);

    void saveEmotionTypesForPost(int postId, List<Integer> typeIds);
}
