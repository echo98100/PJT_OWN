package com.own.domain.emotion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.own.domain.emotion.dao.EmotionDao;
import com.own.domain.emotion.dto.model.EmotionType;
import com.own.domain.emotion.dto.response.EmotionTypeResponse;

@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionDao emotionDao;

    @Override
    public List<EmotionTypeResponse> getEmotionTypesByPostId(int postId) {

        List<EmotionType> list = emotionDao.selectEmotionTypesByPostId(postId);
        List<EmotionTypeResponse> responseList = new ArrayList<>();

        for (EmotionType e : list) {
            responseList.add(new EmotionTypeResponse(
                    e.getEmotionTypeId(),
                    e.getEmotionName()
            ));
        }

        return responseList;
    }

    @Override
    public void saveEmotionTypesForPost(int postId, List<Integer> typeIds) {

        for (int typeId : typeIds) {
            emotionDao.insertPostEmotionType(postId, typeId);
        }
    }

	@Override
	public List<EmotionType> getAllEmotionTypes() {
		
		return emotionDao.selectAllEmotionTypes();
	}
}

