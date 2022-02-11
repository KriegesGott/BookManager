package edu.xtu.library.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBorrowVO {

	private String bookName;
	private String author;
	private String publisher;
	private String startTime;
	private String endTime;
	private String state;
}
