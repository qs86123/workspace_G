package com.wt.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wt.test.ex.MyException;

@ControllerAdvice
public class ExceptionHandler {

	@ResponseBody
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public Object MethodArgumentNotValidHandler(HttpServletRequest request, Exception exception){

		if (exception instanceof MyException)
			return ((MyException) exception).getMsg();
		return exception.getMessage();
	}

}
