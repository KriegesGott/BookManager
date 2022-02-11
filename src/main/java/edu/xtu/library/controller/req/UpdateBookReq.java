package edu.xtu.library.controller.req;

import lombok.Data;

@Data
public class UpdateBookReq {
	private Long id;
	private String name;
	private String author;
	private String publisher;
	private String type;
	private String state;
}
