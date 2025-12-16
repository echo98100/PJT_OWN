DROP TABLE IF EXISTS workout_type;

CREATE TABLE IF NOT EXISTS workout_type (
workout_type_id INT PRIMARY KEY AUTO_INCREMENT,
workout_name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO workout_type (workout_name) 
VALUES
	('헬스'),
	('러닝'),
	('사이클'),
	('클라이밍'),
	('등산'),
	('요가'),
	('복싱'),
	('댄스');