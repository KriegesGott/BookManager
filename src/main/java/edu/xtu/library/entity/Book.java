package edu.xtu.library.entity;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Long id;
	private String name;
	private String author;
	private String publisher;
	private String type;
	private String state;
	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 修改人
	 */
	private String modifier;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 修改时间
	 */
	private Timestamp updateTime;

}
