package com.own.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.own.global.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
//	 // 인터셉터 등록
//	@Autowired
//	private AuthInterceptor authInterceptor;
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(authInterceptor)
//		 		.addPathPatterns("/api/**")              // 인증 필요한 경로
//		 		.excludePathPatterns("/api/auth/**");    // 로그인/회원가입은 제외
//	    }
}
