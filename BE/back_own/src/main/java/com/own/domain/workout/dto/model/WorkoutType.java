package com.own.domain.workout.dto.model;

public class WorkoutType {
	
	private int workoutTypeId;
	private String workoutName;
	
	public WorkoutType() {
		
	}

	public WorkoutType(int workoutTypeId, String workoutName) {
		this.workoutTypeId = workoutTypeId;
		this.workoutName = workoutName;
	}


	public int getWorkoutTypeId() {
		return workoutTypeId;
	}

	public void setWorkoutTypeId(int workoutTypeId) {
		this.workoutTypeId = workoutTypeId;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	
	
	
}
