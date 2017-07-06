package com.changhong.data.jxw.report.controller;

import com.changhong.data.jxw.report.commons.BaseRes;
import com.changhong.data.jxw.report.commons.ResponseConstants;
import com.changhong.data.jxw.report.entity.TradeReport;
import com.changhong.data.jxw.report.service.TradeReportService;
import com.changhong.data.jxw.report.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/15
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/report/trade")
public class TradeReportController {

    @Autowired
    private TradeReportService tradeReportService;

    @PostMapping("/query/between")
    public BaseRes query(String startTime, String endTime) {
        BaseRes baseRes = new BaseRes();
        if (!DateUtils.isValidate(startTime, "yyyy-MM") || !DateUtils.isValidate(endTime,"yyyy-MM")) {
            baseRes.setError(ResponseConstants.STATUS_ERROR);
            baseRes.setMsg("date param should be like : 'yyyy-MM'");
            return baseRes;
        }
        List<TradeReport> tradeReports = this.tradeReportService.queryBetweenDate(startTime,endTime);
        if (tradeReports == null || tradeReports.size() == 0 )
            return baseRes;
        Map<String ,Object> result = new HashMap<>();
        List<String> dates = new ArrayList<>();
        List<Integer> cnts = new ArrayList<>();
        List<Integer> increasements = new ArrayList<>();
        for (TradeReport tradeReport : tradeReports){
            dates.add(tradeReport.getDate());
            cnts.add(tradeReport.getCnt());
            increasements.add(tradeReport.getIncreasement());
        }

        result.put("date",dates);
        result.put("cnts",cnts);
        result.put("increasements",increasements);

        baseRes.setData(result);
        return baseRes;
    }
}
