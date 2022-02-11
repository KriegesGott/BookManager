package edu.xtu.library.controller.req;

import lombok.Data;

@Data
public class AddUserReq {

	private String name;
	private String studentCode;
	private String department;
}
