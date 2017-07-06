package com.changhong.data.jxw.report.service;

import com.changhong.data.jxw.report.dao.TradeReportMapper;
import com.changhong.data.jxw.report.entity.TradeReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/15
 * @Description:
 */
@Service("tradeReportService")
public class TradeReportService {

    @Autowired
    private TradeReportMapper tradeReportMapper;
    public List<TradeReport> queryBetweenDate(String startTime, String endTime){

        return tradeReportMapper.selectBetweenDate(startTime,endTime);
    }
}
