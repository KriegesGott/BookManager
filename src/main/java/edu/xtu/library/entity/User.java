package edu.xtu.library.entity;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Long id;
	private String name;
	private String password;
	private String studentCode;
	private String department;
	private String role;
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
