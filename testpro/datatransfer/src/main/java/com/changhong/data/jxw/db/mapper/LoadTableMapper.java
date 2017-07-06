package com.changhong.data.jxw.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.entity.LoadTable;

/**
 * 
 * 对应表load_table
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:55:01
 */
@Mapper
public interface LoadTableMapper {

	@Insert("insert into load_table (id,ldpid,table_source,table_name,target_table,increment_field) "
			+ " values(#{id},#{ldpid},#{tableSource},#{tableName},#{targetTable},#{incrementField})")
	public void insert(LoadTable loadTable);

	// 根据load_data_package的id来删除记录
	@Transactional
	@Delete("delete from load_table where ldpid=#{loadDataPackageId}")
	public void deleteByLoadDataPackageId(String loadDataPackageId);

	// 根据load_data_package的id来查询目标表名字，测试的时候要删除测试建立的目标表，会用到
	@Select("select target_table from load_table where ldpid=#{loadDataPackageId}")
	public List<String> selectTargetTablesByLoadDataPackageId(String loadDataPackageId);

}
