package edu.xtu.library.controller.req;

import java.util.Date;

import lombok.Data;

@Data
public class BorrowReq {

	private Long userId;
	private Long bookId;
	private Date startTime;
	private String state;

}
