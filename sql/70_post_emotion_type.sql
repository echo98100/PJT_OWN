DROP TABLE IF EXISTS post_emotion_type;

CREATE TABLE post_emotion_type (
    post_id INT NOT NULL,
    emotion_type_id INT NOT NULL,
    PRIMARY KEY (post_id, emotion_type_id),
    CONSTRAINT fk_post_emotion_post FOREIGN KEY (post_id) 
        REFERENCES post(post_id) ON DELETE CASCADE,
    CONSTRAINT fk_post_emotion_type FOREIGN KEY (emotion_type_id) 
        REFERENCES emotion_type(emotion_type_id) ON DELETE CASCADE
);