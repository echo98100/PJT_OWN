DROP TABLE IF EXISTS user_post_bookmark;

CREATE TABLE user_post_bookmark (
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, post_id),
    CONSTRAINT fk_bookmark_user FOREIGN KEY (user_id) 
        REFERENCES user(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_bookmark_post FOREIGN KEY (post_id) 
        REFERENCES post(post_id) ON DELETE CASCADE
);