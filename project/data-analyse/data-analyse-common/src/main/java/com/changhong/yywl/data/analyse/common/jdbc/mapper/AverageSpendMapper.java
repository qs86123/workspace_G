package com.changhong.yywl.data.analyse.common.jdbc.mapper;

import com.changhong.yywl.data.analyse.common.jdbc.entity.AverageConsumeEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-9-7.
 */
public interface AverageSpendMapper {
    /**
     * 查询对应用户类别的平均消费数据
     * @param map
     * @return
     */
    public List<AverageConsumeEntity> findAveConsume(Map<String,String> map);

    /**
     * 查询所有用户类别
     * @param map
     * @return
     */
    public List<String> findTypeList(Map<String,String> map);
}
