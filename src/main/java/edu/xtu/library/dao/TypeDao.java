package edu.xtu.library.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TypeDao {

	@Select("select name from type where code = #{code}")
	public String selectNameByCode(String code);
}
