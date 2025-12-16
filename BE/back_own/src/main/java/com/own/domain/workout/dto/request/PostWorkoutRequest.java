package com.own.domain.workout.dto.request;

import java.util.List;

public class PostWorkoutRequest {
	
	private int postId;
	private List<Integer> workoutTypeIds;
	
	public PostWorkoutRequest() {
		
	}
	
	public PostWorkoutRequest(int postId, List<Integer> workoutTypeIds) {
		this.postId = postId;
		this.workoutTypeIds = workoutTypeIds;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public List<Integer> getWorkoutTypeIds() {
		return workoutTypeIds;
	}

	public void setWorkoutTypeIds(List<Integer> workoutTypeIds) {
		this.workoutTypeIds = workoutTypeIds;
	}
	
	
}
