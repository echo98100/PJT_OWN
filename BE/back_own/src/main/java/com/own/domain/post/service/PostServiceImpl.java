package com.own.domain.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.own.domain.emotion.service.EmotionService;
import com.own.domain.post.dao.PostDao;
import com.own.domain.post.dto.request.PostCreateRequest;
import com.own.domain.post.dto.request.PostSearchRequest;
import com.own.domain.post.dto.request.PostUpdateRequest;
import com.own.domain.post.dto.response.PostResponse;
import com.own.domain.workout.service.WorkoutService;
import com.own.global.exception.CustomException;
import com.own.global.exception.ErrorCode;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	@Autowired
	private WorkoutService workoutService;

	@Autowired
	private EmotionService emotionService;

	@Override
	@Transactional
	public PostResponse createPost(PostCreateRequest request) {
		 // ===== 1. 요청 검증 (DB 접근 전) =====

	    if (request.getMusicId() == null) {
	        throw new CustomException(ErrorCode.INVALID_MUSIC_REQUEST);
	    }

	    if (request.getWorkoutTag() == null) {
	        throw new CustomException(ErrorCode.INVALID_WORKOUT_REQUEST);
	    }

	    if (request.getEmotionTags() == null || request.getEmotionTags().isEmpty()) {
	        throw new CustomException(ErrorCode.INVALID_EMOTION_REQUEST);
	    }

	    // ===== 2. 게시글 저장 =====
	    postDao.insertPost(request);
	    Integer postId = request.getPostId();

	    if (postId == null) {
	        throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
	    }

	    // ===== 3. 연관 데이터 저장 =====
	    workoutService.saveWorkoutTypesForPost(postId, request.getWorkoutTag());
	    emotionService.saveEmotionTypesForPost(postId, request.getEmotionTags());

	    // ===== 4. 응답 조회 =====
	    PostResponse response = postDao.findPostResponseById(postId);
		
	  //PostResponse 객체 반환
	  		return response;
		
	}

	@Override
	public List<PostResponse> getFeedPosts(PostSearchRequest request) {
		return postDao.findAll(request);

	}

	@Override
	public List<PostResponse> getUserPosts(PostSearchRequest request) {
		return postDao.findByUserId(request);
	}

	@Override
	public List<PostResponse> getLikedPosts(PostSearchRequest request) {
		return postDao.findLikedPosts(request);
	}

	@Override
	public List<PostResponse> getBookmarkedPosts(PostSearchRequest request) {
		return postDao.findBookmarkedPosts(request);
	}

	@Override
	@Transactional
	public int updatePost(int postId, PostUpdateRequest request) {
		int updated = postDao.updatePost(postId, request);

		if (updated == 0) {
			throw new CustomException(ErrorCode.POST_NOT_FOUND);
		}
		return updated; // 성공 시 1
	}

	@Override
	@Transactional
	public int deletePost(int postId) {
		int deleted = postDao.deletePost(postId);

		if (deleted == 0) {
			throw new CustomException(ErrorCode.POST_NOT_FOUND);

		}
		return deleted; // /성공 시 1

	}
}
