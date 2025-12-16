DROP TABLE IF EXISTS post_workout_type;

CREATE TABLE post_workout_type (
    post_id INT NOT NULL,
    workout_type_id INT NOT NULL,
    PRIMARY KEY (post_id, workout_type_id),
    CONSTRAINT fk_post_workout_post FOREIGN KEY (post_id) 
        REFERENCES post(post_id) ON DELETE CASCADE,
    CONSTRAINT fk_post_workout_type FOREIGN KEY (workout_type_id) 
        REFERENCES workout_type(workout_type_id) ON DELETE CASCADE
);