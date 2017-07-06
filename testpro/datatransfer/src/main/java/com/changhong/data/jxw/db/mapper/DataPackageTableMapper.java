package com.changhong.data.jxw.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.entity.DataPackageTableInfo;

/**
 * 
 * 对应表data_package_table
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:55:01
 */
@Mapper
public interface DataPackageTableMapper {
	@Insert("insert into data_package_table (id,dpid,table_source,table_name,target_table,sumary_table) "
			+ "VALUES(#{id},#{dpid},#{tableSource},#{tableName},#{targetTable},#{sumaryTable})")
	public void insert(DataPackageTableInfo dataPackageTableInfo);

	// 根据数据包id删除相关联的记录
	@Transactional
	@Delete("delete from data_package_table where dpid=#{dataPackageId}")
	public void deleteByDataPackageId(String dataPackageId);

	// 根据数据包id查询记录
	@Select("select id,dpid,table_source tableSource,table_name tableName,target_table targetTable,sumary_table sumaryTable "
			+ "from data_package_table where dpid=#{dataPackageId}")
	public List<DataPackageTableInfo> selectByDataPackageId(String dataPackageId);

	// 根据数据包id查询所有记录的id
	@Select("select id from data_package_table where dpid=#{dataPackageId}")
	public List<String> selectIdsByDataPackageId(String dataPackageId);

}
