package com.wt.springboot.demo.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wt.springboot.demo.entity.Item;

//@Mapper
public interface ItemMapper {

	@Select("select * from items")
	public List<Item> findItems();

}
