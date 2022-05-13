package edu.xtu.library.controller.req;

import lombok.Data;

@Data
public class UpdateUserReq {

	private Long id;
	private String name;
	private String studentCode;
	private String department;
	private Double price;
}
