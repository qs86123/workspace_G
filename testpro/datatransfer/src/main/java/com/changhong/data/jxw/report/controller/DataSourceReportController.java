package com.changhong.data.jxw.report.controller;

import com.changhong.data.jxw.report.commons.BaseRes;
import com.changhong.data.jxw.report.commons.ResponseConstants;
import com.changhong.data.jxw.report.entity.DataSourceReport;
import com.changhong.data.jxw.report.service.DataSourceReportService;
import com.changhong.data.jxw.report.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/14
 * @Description:报表控制器
 */
@RestController
@RequestMapping("/api/v1/report/datasource")
public class DataSourceReportController {

    @Autowired
    private DataSourceReportService dataSourceReportService;

    private Logger logger = LoggerFactory.getLogger(DataSourceReportController.class);


    /**
     * @Author:ZhouFan [https://git.oschina.net/fantazy]
     * @Date:2017/3/15 17:00
     * @Content:查询所有的数据源报表
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    public BaseRes query() {
        BaseRes baseRes = new BaseRes();
        List<DataSourceReport> dataSourceReports = null;

        try {
            dataSourceReports = dataSourceReportService.queryAll();
            baseRes.setData(dataSourceReports);
        } catch (Exception e) {
            baseRes.setError(500);
            baseRes.setMsg("DataSourceReportController#query() error");
            logger.error("DataSourceReportController#query() error : ", e);
        }
        return baseRes;
    }

    /**
     * @Author:ZhouFan [https://git.oschina.net/fantazy]
     * @Date:2017/3/15 17:01
     * @Content:查询某天的数据源报表
     */
    @RequestMapping(value = "/query/{date}", method = RequestMethod.POST)
    public BaseRes query(@PathVariable String date) {
        BaseRes baseRes = new BaseRes();
        if (!DateUtils.isValidate(date, "yyyy-MM-dd")) {
            baseRes.setError(ResponseConstants.STATUS_ERROR);
            baseRes.setMsg(ResponseConstants.MESSAGE_FAIL);
            return baseRes;
        }
        try {
            DataSourceReport dataSourceReport = this.dataSourceReportService.findByDate(date);
            baseRes.setData(dataSourceReport);
            Map<String,Object> result = new HashMap<>();
            result.put("dates", DateUtils.format(dataSourceReport.getDate(), "yyyy-MM-dd"));
            baseRes.setData(result);
        } catch (Exception e) {
            baseRes.setError(ResponseConstants.STATUS_ERROR);
            baseRes.setMsg("DataSourceReportController#query() error");
            logger.error("DataSourceReportController#query() error : ", e);
            return baseRes;
        }
        return baseRes;
    }

    /**
     * @Author:ZhouFan [https://git.oschina.net/fantazy]
     * @Date:2017/3/15 17:01
     * @Content:查询某段时间的数据源报表
     */
    @RequestMapping(value = "/query/between", method = RequestMethod.POST)
    public BaseRes query(String startTime, String endTime) {
        System.out.println(startTime + "==" + endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BaseRes baseRes = new BaseRes();
        if (!DateUtils.isValidate(startTime,"yyyy-MM-dd") || !DateUtils.isValidate(endTime,"yyyy-MM-dd")){
            baseRes.setError(ResponseConstants.STATUS_ERROR);
            baseRes.setMsg("日期格式错误,请使用:yyyy-MM-dd");
            return baseRes;
        }
        try {
            List<DataSourceReport>  dataSourceReports = this.dataSourceReportService.findBetweenDate(startTime,endTime);
            List<String> dates = new ArrayList<>();
            List<Integer> cnts = new ArrayList<>();
            List<Integer> increasements = new ArrayList<>();
            for (DataSourceReport dataSourceReport: dataSourceReports){
                dates.add(DateUtils.format(dataSourceReport.getDate(), "yyyy-MM-dd"));
                cnts.add(dataSourceReport.getCnt());
                increasements.add(dataSourceReport.getIncreasement());
            }
            Map<String,Object> result = new HashMap<>();
            result.put("dates",dates);
            result.put("cnts",cnts);
            result.put("increasements",increasements);
            //JSONObject jsonObject = Json
            baseRes.setData(result);
        } catch (Exception e) {
            baseRes.setError(ResponseConstants.STATUS_ERROR);
            baseRes.setMsg("DataSourceReportController#query() error");
            logger.error("DataSourceReportController#query() error : ", e);
            return baseRes;
        }
        return baseRes;
    }
}
