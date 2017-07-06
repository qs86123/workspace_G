package com.changhong.data.jxw.db.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.entity.DataPackageColumnInfo;

/**
 * 
 * 对应表data_package_column
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:55:01
 */
@Mapper
public interface DataPackageColumnMapper {
	@Insert("insert into data_package_column (id,dptid,column_source,column_name,column_type) "
			+ "VALUES(#{id},#{dptid},#{columnSource},#{columnName},#{columnType})")
	public void insert(DataPackageColumnInfo dataPackageColumnInfo);

	// 根据id删除数据包
	@Transactional
	@Delete("delete from data_package_column where dptid in "
			+ "(select id from data_package_table where dpid=#{dataPackageId})")
	public void deleteByDataPackageId(String dataPackageId);

}
