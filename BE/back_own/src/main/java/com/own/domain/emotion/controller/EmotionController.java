package com.own.domain.emotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.own.domain.emotion.dto.model.EmotionType;
import com.own.domain.emotion.dto.request.PostEmotionRequest;
import com.own.domain.emotion.dto.response.EmotionTypeResponse;
import com.own.domain.emotion.service.EmotionService;

@RestController
@RequestMapping("/api/emotion")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;
    
    @GetMapping
	public List<EmotionType> getAllWorkoutTypes() {
		return emotionService.getAllEmotionTypes();
	}

    // 특정 포스트의 감정 태그 조회
    @GetMapping("/post/{postId}")
    public List<EmotionTypeResponse> getEmotionTypesByPost(@PathVariable int postId) {
        return emotionService.getEmotionTypesByPostId(postId);
    }

    // 감정 태그 추가
    @PostMapping("/post")
    public void savePostEmotion(@RequestBody PostEmotionRequest request) {
        emotionService.saveEmotionTypesForPost(request.getPostId(), request.getEmotionTypeIds());
    }
}
