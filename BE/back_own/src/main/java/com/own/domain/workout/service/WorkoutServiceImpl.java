package com.own.domain.workout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.own.domain.workout.dao.WorkoutDao;
import com.own.domain.workout.dto.model.WorkoutType;
import com.own.domain.workout.dto.response.WorkoutTypeResponse;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	@Autowired
	private WorkoutDao workoutDao;
	
	@Override
	public List<WorkoutTypeResponse> getWorkoutTypesByPostId(int postId) {
		
		List<WorkoutType> list = workoutDao.selectWorkoutTypesByPostId(postId);
		
		List<WorkoutTypeResponse> responseList = new ArrayList<>();
		
		for(WorkoutType w : list) {
			WorkoutTypeResponse res = new WorkoutTypeResponse(
					w.getWorkoutTypeId(),
					w.getWorkoutName()
			);
			responseList.add(res);
		}
		
		return responseList;
	}

	@Override
	public void saveWorkoutTypesForPost(int postId, List<Integer> typeIds) {
		
		for(int typeId : typeIds) {
			workoutDao.insertPostWorkoutType(postId, typeId);
		}
		
	}

	@Override
	public List<WorkoutType> getAllWorkoutTypes() {
		
		return workoutDao.selectAllWorkoutTypes();
	}

}
