DROP DATABASE IF EXISTS own_db;

CREATE DATABASE IF NOT EXISTS own_db
 CHARACTER SET utf8mb4  #문자셋 설정 (모든 문자 + 이모지 저장 가능)
COLLATE utf8mb4_general_ci; #정렬 규칙 - 대소문자 구분 안 함

USE own_db;