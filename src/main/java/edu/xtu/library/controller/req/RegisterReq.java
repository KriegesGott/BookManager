package edu.xtu.library.controller.req;

import lombok.Data;

@Data
public class RegisterReq {

	private String name;
	private String password;
	private String studentCode;
	private String department;
	private String mail;
}
