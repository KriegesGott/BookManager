package edu.xtu.library.dao;

import java.util.List;

import edu.xtu.library.entity.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BookDao {

	@Select("select * from book")
	List<Book> selectAll();

	@Select("select * from book where type = #{type}")
	List<Book> selectBookByType(String type);

	@Select("select * from book where name like CONCAT('%', #{text}, '%') ")
	List<Book> selectBookByText(String text);

	@Insert("insert into book (name, author, publisher, type, creator, modifier, create_time, update_time) values (#{book.name}, #{book.author}, #{book.publisher}, #{book.type}, #{book.creator}, #{book.modifier}, #{book.createTime}, #{book.updateTime})")
	int insertOne(@Param("book") Book book);

	@Update("update book set name = #{book.name}, author = #{book.author}, publisher = #{book.publisher}, type = #{book.type}, state = #{book.state} ,modifier = #{book.modifier}, update_time = #{book.updateTime} where id = #{book.id}")
	int updateById(@Param("book") Book book);

	@Delete("delete from book where id = #{id}")
	int deleteById(Long id);

	@Select("select * from book where id = #{id}")
	Book selectById(Long id);
}
