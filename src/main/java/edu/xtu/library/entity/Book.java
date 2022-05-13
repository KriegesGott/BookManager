package edu.xtu.library.entity;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	/**
	 * 图书号
	 */
	private Long id;

	/**
	 * 书名
	 */
	private String name;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 出版社
	 */
	private String publisher;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 图书状态
	 */
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

	/**
	 * 价格
	 */
	private Double price;

	/**
	 * 图书码
	 */
	private String code;

}
