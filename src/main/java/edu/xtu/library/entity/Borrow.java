package edu.xtu.library.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Borrow {

	private Long id;
	private Long userId;
	private Long bookId;
	private Timestamp startTime;
	private Timestamp endTime;
	private String state;
}
