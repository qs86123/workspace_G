package com.changhong.data.jxw.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.entity.LoadDataPackage;
import com.changhong.data.jxw.db.entity.TableFieldInfo;
import com.changhong.data.jxw.db.entity.res.LoadDataPackageRes;

/**
 * 
 * 对应表load_data_package
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:55:01
 */
@Mapper
public interface LoadDataPackageMapper {

	@Insert("insert into load_data_package (id,dsid,name,add_time,description,is_del) "
			+ "values (#{id},#{dsid},#{name},#{addTime},#{description},#{isDel})")
	public void insert(LoadDataPackage loadDataPackage);

	// 依靠修改状态的方式来表示该数据已经删除
	@Update("update load_data_package set is_del=1 where id=#{loadPackageId}")
	public void deleteByChangeIsDel(String loadPackageId);

	// 根据id删除记录
	@Transactional
	@Delete("delete from load_data_package where id=#{loadPackageId}")
	public void deleteById(String loadPackageId);

	// 根据数据源id查询与平台同步的表信息(包含表的字段信息也通锅left join一并查询，sql太长，容易出错，暂不使用这种形式)
	public List<LoadDataPackageRes> selectWithColumnInfoByUserId(@Param("schemaName") String schemaName,
			@Param("userId") String userId);

	// 根据数据源id查询与凭条同步的表信息,不包括表字段信息
	public List<LoadDataPackageRes> selectByUserId(String userId);

	// 使用此种方式如果表名不存在不会报错
	// 查询某张表的字段信息，上面一个方法不包含字段信息，这里加一个获取字段信息的方法
	@Select("SELECT COLUMN_NAME columnName,COLUMN_TYPE columnType,IS_NULLABLE isNullAble, COLUMN_KEY columnKey,COLUMN_DEFAULT columnDefault,`extra`,COLUMN_COMMENT columnComment "
			+ "FROM  information_schema.columns WHERE table_name=#{tableName} AND table_schema =#{schemaName}")
	public List<TableFieldInfo> selectFieldInfo(@Param("schemaName") String schemaName,
			@Param("tableName") String tableName);
}
