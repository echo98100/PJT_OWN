package com.own.domain.post.service;


import java.util.List;

import com.own.domain.post.dto.request.PostCreateRequest;
import com.own.domain.post.dto.request.PostSearchRequest;
import com.own.domain.post.dto.request.PostUpdateRequest;
import com.own.domain.post.dto.response.MusicRankResponse;
import com.own.domain.post.dto.response.PostResponse;

public interface PostService {
		//포스트 작성
		PostResponse createPost(PostCreateRequest request);
		
		//전체 포스트 리스트 조회
		List<PostResponse> getFeedPosts(PostSearchRequest request);
		
		//특정 유저의 게시글 리스트 조회
		List<PostResponse> getUserPosts(PostSearchRequest request);
			
		//유저별 좋아요한 게시물 조회
		List<PostResponse> getLikedPosts(PostSearchRequest request);
			
		//유저별 북마크한 게시물 조회
		List<PostResponse> getBookmarkedPosts(PostSearchRequest request);
			
		//포스트 수정
		int updatePost(int postId, PostUpdateRequest request);
			
		//특정 포스트 삭제
		int deletePost(int postId);
		
		
		// 태그별 랭킹 1등 노래 조회
		List<MusicRankResponse> getMusicRank();
		
		// 이번주 운동횟수 조회
		int getWeeklyWorkoutCount(int userId);
}
