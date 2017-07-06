package com.changhong.data.jxw.report.dao;

import com.changhong.data.jxw.report.entity.DataSourceReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author:ZhouFan [https://git.oschina.net/fantazy]
 * @Date:2017/3/15 17:17
 * @Content:数据源报表dao
 */
@Mapper
public interface DataSourceReportMapper {

    int save(DataSourceReport record);

    List<DataSourceReport> selectAll();

    DataSourceReport selectByDate(String date);

    List<DataSourceReport> selectBetweenDate(String startTime, String endTime);
}