package com.own.global.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({
	"com.own.domain.bookmark.dao",
	"com.own.domain.emotion.dao",
	"com.own.domain.like.dao",
	"com.own.domain.music.dao",
	"com.own.domain.post.dao",
	"com.own.domain.user.dao",
	"com.own.domain.workout.dao"
})
public class MyBatisConfig {
	
}
