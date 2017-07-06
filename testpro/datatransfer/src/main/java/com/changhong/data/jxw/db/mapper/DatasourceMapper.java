package com.changhong.data.jxw.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.entity.DatasourceInfo;

/**
 * 
 * 对应表datasource
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:55:01
 */
@Mapper
public interface DatasourceMapper {

	public void insert(DatasourceInfo datasourceInfo);

	// 根据id删除数据源
	@Transactional
	@Delete("delete from datasource where id=#{datasourceId} and uid=#{userId}")
	public void delete(@Param("userId") String userId, @Param("datasourceId") String datasourceId);

	// 根据用户id查询用户拥有的数据源
	@Select("select id,url,db_type dbType,username,pwd,add_time addTime,validate,uid,port,schema_name schemaName from datasource where uid=#{userId}")
	public List<DatasourceInfo> selectAll(String userId);

	// 根据数据源id查询用户的单个数据信息
	@Select("select id,url,db_type dbType,username,pwd,add_time addTime,validate,uid,port,schema_name schemaName from datasource where id=#{datasourceId}")
	public DatasourceInfo selectOne(String datasourceId);

	// 更新用户数据源信息
	@Transactional
	@Update("update datasource set url=#{datasourceInfo.url},db_type=#{datasourceInfo.dbType},username=#{datasourceInfo.username},"
			+ "pwd=#{datasourceInfo.pwd},add_time=#{datasourceInfo.addTime},validate=#{datasourceInfo.validate},uid=#{datasourceInfo.uid},"
			+ "port=#{datasourceInfo.port},schema_name=#{datasourceInfo.schemaName} where id=#{datasourceInfo.id}")
	public void update(@Param("datasourceInfo") DatasourceInfo datasourceInfo);

	// 根据load_data_package的id查询用户数据原信息，暂未使用
	@Select("select id,url,db_type dbType,username,pwd,add_time addTime,validate,uid,port,schema_name schemaName from datasource where id="
			+ "(select dsid from load_data_package where id=#{dataPackageId})")
	public DatasourceInfo selectByDataPackageId(String dataPackageId);

}
