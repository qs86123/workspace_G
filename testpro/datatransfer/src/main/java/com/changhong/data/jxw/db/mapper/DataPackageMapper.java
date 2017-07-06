package com.changhong.data.jxw.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import com.changhong.data.jxw.db.entity.DataPackageInfo;
import com.changhong.data.jxw.db.entity.res.DataPackageInfoRes;

/**
 * 
 * 对应表data_package
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:55:01
 */
@Mapper
public interface DataPackageMapper {

	@Insert("insert into data_package (id,uid,title,is_del) VALUES(#{id},#{uid},#{title},#{isDel})")
	public void insert(DataPackageInfo dataPackageInfo);

	// 根据用户id查询数据包
	@Select("select id,uid,title from data_package where `is_del`='0' and uid=#{userId}")
	public List<DataPackageInfo> selectPackageByUserId(String userId);

	// 根据数据包id查询数据包的详细信息，包括package包含的所有表信息等
	public DataPackageInfoRes selectPackageInfoByPackageId(String dataPackageId);

	// 根据id删除数据包
	@Transactional
	@Delete("delete from data_package where id=#{dataPackageId}")
	public void deleteById(String dataPackageId);

	// 根据表名drop掉表，在测试的时候，测试完之后删除测试建立的表会使用
	@Transactional
	public void deleteTable(@Param("tableName") String tableName);

	// 改变package的状态来表示该数据已经被删除
	@Update("update data_package set is_del=1 where id=#{dataPackageId}")
	public void deleteByChangeIsDel(String dataPackageId);

}
