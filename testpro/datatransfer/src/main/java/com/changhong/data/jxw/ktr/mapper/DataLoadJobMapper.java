package com.changhong.data.jxw.ktr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.changhong.data.jxw.ktr.entity.Cron;
import com.changhong.data.jxw.ktr.entity.CronLog;
import com.changhong.data.jxw.ktr.entity.DataLoadJob;
import com.changhong.data.jxw.ktr.entity.DataPackage;
import com.changhong.data.jxw.ktr.entity.DataPackageColumn;
import com.changhong.data.jxw.ktr.entity.DataPackageTable;
import com.changhong.data.jxw.ktr.entity.DataSource;

@Mapper
public interface DataLoadJobMapper
{
    @Select("select id,url,db_type dbType,username,pwd,add_time addTime,validate,uid from datasource where uid=#{userId}")
    public List<DataSource> selectAll(String userId);

    @Delete("delete from data_load_job where dpid=#{id} ")
    public void deleteDataLoadJob(@Param("id") String id);

    @Delete("delete from cron where cron_id =#{id} ")
    public void deleteCron(@Param("id") String id);

    @Delete("delete from cron_log where cid =#{id} ")
    public void deleteCronLog(@Param("id") String id);

    @Select("select id,dsid,name, add_time,description from data_package where id=#{id}")
    public DataPackage selectDataPackageById(@Param("id") String id);

    @Select("select id,db_type,url, username,pwd,add_time,validate,uid,port,schema_name from datasource where id=#{id}")
    public DataSource selectDataSourceById(@Param("id") String id);

    @Select("select id,dpid,table_source, table_name,target_table,sumary_table from data_package_table where dpid=#{dpId}")
    public List<DataPackageTable> selectDataPackageTableAll(@Param("dpId") String dpId);

    @Select("select id,dptid,column_source, column_name,column_type from data_package_column where dptid=#{dptId}")
    public List<DataPackageColumn> selectDataPackageColumnAll(@Param("dptId") String dptId);

    // 根据dpid查询loadjob表
    @Select("select id,dpid,name,file_name, add_time,last_run,cron_id,uid from data_load_job where dpid=#{id}")
    public DataLoadJob selectDataLoadJobByUid(@Param("id") String id);

    // 根据cron_id查询cron表
    @Select("select id,name,group,file_name, add_time,ep,status from cron where id=#{id}")
    public Cron selectCronByCronId(@Param("id") String dpId);

    // 根据cron_id查询cron_log表
    @Select("select id,cid,info,run_start, run_end,result from cron_log where cid=#{id}")
    public List<CronLog> selectCronLogByCornid(@Param("id") String cornId);

    // 添加cron
    @Insert("insert into cron values(#{cron.id},#{cron.name},#{cron.group},#{cron.addTime},#{cron.ep},#{cron.status})")
    public void insertCron(@Param("cron") Cron cron);

    // 添加DLJ
    @Insert("insert into data_load_job values(#{dataLoadJob.id},#{dataLoadJob.dpid},#{dataLoadJob.name},#{dataLoadJob.fileName},#{dataLoadJob.addTime},#{dataLoadJob.lastRun},#{dataLoadJob.cronId},#{dataLoadJob.uId})")
    public void insertDataLoadJob(@Param("dataLoadJob") DataLoadJob dataLoadJob);
}
