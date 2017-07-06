package com.changhong.data.jxw.report.dao;

import com.changhong.data.jxw.report.entity.TradeReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TradeReportMapper {

    TradeReport selectByDate(String id);
    List<TradeReport> selectBetweenDate(String startTime,String endTime);
}