package com.own.domain.like.dao;

public interface LikeDao {
	//좋아요하기 
	void insertLike(int userId, int postId);
	
	//좋아요 취소 
	void deleteLike(int userId, int postId);
	
	//특정 포스트의 좋아요 개수 세기
	void countLikeByPostId(int postId);
	
	//특정 유저가 특정 게시물에 좋아요했는지 체크
	boolean existsLike(int userId, int postId);
	
}
