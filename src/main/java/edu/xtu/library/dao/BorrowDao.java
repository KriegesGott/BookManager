package edu.xtu.library.dao;
import java.util.List;

import edu.xtu.library.entity.Borrow;
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
public interface BorrowDao {

	@Insert("insert into borrow (user_id, book_id, start_time, end_time, state) values (#{borrow.userId}, #{borrow.bookId}, #{borrow.startTime}, #{borrow.endTime}, #{borrow.state})")
	int insertOne(@Param("borrow") Borrow borrow);

	@Results(value = {@Result(column = "user_id", property = "userId"),
			@Result(column = "book_id", property = "bookId"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime")})
	@Select("select * from borrow")
	List<Borrow> selectAll();

	@Results(value = {@Result(column = "user_id", property = "userId"),
			@Result(column = "book_id", property = "bookId"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime")})
	@Select("select * from borrow where book_id = #{bookId}")
	List<Borrow> selectByBookId(@Param("bookId") Long bookId);

	@Results(value = {@Result(column = "user_id", property = "userId"),
			@Result(column = "book_id", property = "bookId"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime")})
	@Select("select * from borrow where id = #{id}")
	Borrow selectById(@Param("id") Long id);

	@Update("update borrow set state = #{borrow.state} where id = #{borrow.id}")
	int updateStaueById(@Param("borrow") Borrow borrow);

	@Delete("delete from borrow where id = #{id}")
	int delete(@Param("id") Long id);

	@Results(value = {@Result(column = "user_id", property = "userId"),
			@Result(column = "book_id", property = "bookId"),
			@Result(column = "start_time", property = "startTime"),
			@Result(column = "end_time", property = "endTime")})
	@Select("select * from borrow where user_id = #{userId}")
	List<Borrow> selectByUserId(@Param("userId") Long userId);

	@Select("select count(id) from borrow where user_id = #{userId}")
	int selectCountByUserId(@Param("userId") Long userId);

}
