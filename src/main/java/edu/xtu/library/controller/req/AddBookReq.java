package edu.xtu.library.controller.req;

import lombok.Data;

@Data
public class AddBookReq {
	private String name;
	private String author;
	private String publisher;
	private String type;
}
