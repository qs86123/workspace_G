package com.changhong.data.jxw.report.service;

import com.changhong.data.jxw.report.dao.DataSourceReportMapper;
import com.changhong.data.jxw.report.entity.DataSourceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Zhoufan [https://git.oschina.net/fantazy/].
 * @Date 2017/3/15
 * @Description:数据源报表service层
 */
@Service("dataSourceReportService")
public class DataSourceReportService {

    @Autowired
    DataSourceReportMapper dataSourceReportMapper;
    public List<DataSourceReport> queryAll(){
        return this.dataSourceReportMapper.selectAll();
    }

    public DataSourceReport findByDate(String date){
        return this.dataSourceReportMapper.selectByDate(date);
    }

    public List<DataSourceReport> findBetweenDate(String startTime,String endTime){
        return this.dataSourceReportMapper.selectBetweenDate(startTime,endTime);
    }
}
