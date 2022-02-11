package edu.xtu.library.controller.req;

import lombok.Data;

@Data
public class UpdatePasswordReq {
	private String oldPassword;
	private String newPassword;
	private String repeat;
}
