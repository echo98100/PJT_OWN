package com.own.domain.bookmark.dao;

public interface BookmarkDao {
	// 북마크 하기
	void insertBookmark(int userId, int postId);

	// 북마크 취소
	void deleteBookmark(int userId, int postId);

	// 특정 포스트의 북마크 개수 세기
	void countBookmarkByPostId(int postId);

	// 특정 유저가 특정 게시물에 북마크했는지 체크
	boolean existsBookmark(int userId, int postId);

}
