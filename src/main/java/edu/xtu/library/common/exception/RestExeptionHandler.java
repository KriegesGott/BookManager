package edu.xtu.library.common.exception;

import edu.xtu.library.common.result.ResultData;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExeptionHandler {

	@ExceptionHandler(value = ProjectException.class)
	public ResultData<String> projectHandler(ProjectException e){
		return ResultData.fail(e);
	}

	@ExceptionHandler(value = Exception.class)
	public ResultData<String> exceptionHandler(Exception e){
		e.printStackTrace();
		return ResultData.fail();
	}
}
