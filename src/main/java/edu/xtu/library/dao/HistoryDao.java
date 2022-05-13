package edu.xtu.library.dao;

import edu.xtu.library.entity.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HistoryDao {

	@Insert("insert into history (user_id, text, recommend) values (#{history.userId}, #{history.text}, #{history.recommend})")
	public void insertOne(@Param("history") History history);

	@Select("select recommend from history where user_id = #{userId} order by id desc limit 1")
	public String selectByUser(Long userId);
}
