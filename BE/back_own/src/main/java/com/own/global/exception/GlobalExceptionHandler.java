package com.own.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 모든 예외를 처리
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception e) {
//       
//    	e.printStackTrace();
//        
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("서버 오류가 발생했습니다: " + e.getMessage());
//    }
	
	 @ExceptionHandler(CustomException.class)
	    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
	        ErrorCode errorCode = e.getErrorCode();
	        return ResponseEntity
	                .status(errorCode.getStatus())
	                .body(ErrorResponse.of(errorCode));
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleException(Exception e) {
	        e.printStackTrace(); // 로그 출력

	        ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
	        return ResponseEntity
	                .status(code.getStatus())
	                .body(ErrorResponse.of(code));
	    }
    
}
