package edu.xtu.library.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoVO {
	private String name;
	private String school = "湘潭大学";
	private String department;
	private String role;
	private String borrowCount;

}
