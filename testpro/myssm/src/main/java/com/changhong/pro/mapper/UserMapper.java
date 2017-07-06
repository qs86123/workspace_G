package com.changhong.pro.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.changhong.pro.entity.User;

public interface UserMapper {

	@Select("select * from person where name='wt1'")
	public User getUserByName();
	
	@Delete("delete from person where name='wt1'")
	public void deleteOne();
	
	@Delete("delete from person where name='wt2'")
	public void deleteTwo();
	
}
