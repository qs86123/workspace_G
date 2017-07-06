package com.changhong.data.jxw.ktr.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataPushJobMapper
{
    // @Select("select id,dpid,dsid,uid,oid,name,file_name,add_time,last_run,cron_id from data_push_job where
    // uid=#{uid}")
    // public List<DataPushJob> selectAll(@Param("uid") String uId);
    //
    // @Delete("delete from data_push_job where id=#{id} ")
    // public void deleteDataLoadJob(@Param("id") String id);
}
