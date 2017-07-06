package com.changhong.yywl.data.analyse.common.jdbc.mapper;

import com.changhong.yywl.data.analyse.common.jdbc.entity.PageBrowseCountEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-9-7.
 */
public interface PageBrowseTrendMapper {
    /**
     * 查询所有的页面名称
     * @return
     */
    public List<String> findPages();

    /**
     * 查询所有用户类别
     * @param map
     * @return
     */
    public List<String> findTypeList(Map<String,String> map);

    /**
     * 查询各个用户类别的用户在指定时间段内浏览指定页面的数据
     * @param map
     * @return
     */
    public List<PageBrowseCountEntity> findPageBrowseData(Map<String,String> map);
}
