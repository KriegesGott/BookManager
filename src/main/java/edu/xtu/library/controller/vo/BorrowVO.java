package edu.xtu.library.controller.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import edu.xtu.library.entity.Borrow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowVO {

	private Long id;
	private Long userId;
	private Long bookId;
	private String startTime;
	private String endTime;
	private String state;
	private Double price;

	public BorrowVO(Borrow borrow){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.id = borrow.getId();
		this.userId = borrow.getUserId();
		this.bookId = borrow.getBookId();
		this.startTime = simpleDateFormat.format(new Date(borrow.getStartTime().getTime()));
		this.endTime = simpleDateFormat.format(new Date(borrow.getEndTime().getTime()));
		this.state = borrow.getState();
		this.price = borrow.getPrice();
	}
}
