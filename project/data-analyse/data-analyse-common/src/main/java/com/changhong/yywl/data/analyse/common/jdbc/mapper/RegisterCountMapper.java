package com.changhong.yywl.data.analyse.common.jdbc.mapper;

import com.changhong.yywl.data.analyse.common.jdbc.entity.AllUserCountEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.UserCountEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-9-7.
 */
public interface RegisterCountMapper {
    /**
     * 查询各个用户类别的注册总人数
     * @return
     */
    public List<AllUserCountEntity> findAllUC();

    /**
     * 查询所有用户类别
     * @param map
     * @return
     */
    public List<String> findTypeList(Map<String,String> map);

    /**
     * 查询各个用户类别，在指定时间段内的用户注册数据
     * @param map
     * @return
     */
    public List<UserCountEntity> findUserCount(Map<String,String> map);
}
