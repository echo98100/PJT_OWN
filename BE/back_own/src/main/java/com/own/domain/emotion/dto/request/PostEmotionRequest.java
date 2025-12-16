package com.own.domain.emotion.dto.request;

import java.util.List;

public class PostEmotionRequest {

    private int postId;
    private List<Integer> emotionTypeIds;

    public PostEmotionRequest() {}

    public PostEmotionRequest(int postId, List<Integer> emotionTypeIds) {
        this.postId = postId;
        this.emotionTypeIds = emotionTypeIds;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public List<Integer> getEmotionTypeIds() {
        return emotionTypeIds;
    }

    public void setEmotionTypeIds(List<Integer> emotionTypeIds) {
        this.emotionTypeIds = emotionTypeIds;
    }
}

