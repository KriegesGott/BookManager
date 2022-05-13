package edu.xtu.library.dao;

import java.util.List;

import edu.xtu.library.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

	@Results(value = {@Result(column = "student_code", property = "studentCode")})
	@Select("select * from user where name = #{name}")
	User selectUserByName(String name);

	@Insert("insert into user (name, password, student_code, department, creator, modifier, create_time, update_time, mail) values (#{user.name}, #{user.password}, #{user.studentCode}, #{user.department}, #{user.creator}, #{user.modifier}, #{user.createTime}, #{user.updateTime}, #{user.mail})")
	int insertOne(@Param("user") User user);

	@Update("update user set name = #{user.name}, password = #{user.password}, student_code = #{user.studentCode}, department = #{user.department}, role = #{user.role}, modifier = #{user.modifier}, update_time = #{user.updateTime}, price = #{user.price} where id = #{user.id}")
	int updateById(@Param("user") User user);

	@Results(value = {@Result(column = "student_code", property = "studentCode")})
	@Select("select * from user where name like CONCAT('%', #{text}, '%') and role = '用户'")
	List<User> selectUserByText(String text);

	@Results(value = {@Result(column = "student_code", property = "studentCode")})
	@Select("select id, name, student_code, mail, department, price from user where role = '用户'")
	List<User> selectAllUser();

	@Results(value = {@Result(column = "student_code", property = "studentCode"),
					  @Result(column = "create_time", property = "updateTime"),
					  @Result(column = "update_time", property = "updateTime")})
	@Select("select * from user where id = #{id}")
	User selectUserById(Long id);

	@Delete("delete from user where id = #{id}")
	int deleteById(Long id);
}
