package edu.xtu.library.common.exception;

import java.io.IOException;

public class ProjectException extends IOException {

	private int code;
	private String message;

	public ProjectException(String message){
		this.code = 500;
		this.message = message;
	}

	public ProjectException(int code, String message){
		this.code = code;
		this.message = message;
	}

	public int getCode(){
		return this.code;
	}

	public String getMessage(){
		return this.message;
	}
}
