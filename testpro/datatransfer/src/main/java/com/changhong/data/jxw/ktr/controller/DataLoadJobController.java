package com.changhong.data.jxw.ktr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.ktr.entity.res.BaseRes;
import com.changhong.data.jxw.ktr.entity.res.DataLoadInfo;
import com.changhong.data.jxw.ktr.service.dataloadjob.DataLoadJobService;

@Controller
@RequestMapping("/rest/ktr")
public class DataLoadJobController
{
    @Autowired
    private DataLoadJobService dataLoadJobService;

    @RequestMapping(value = "/dataloadjob", method = RequestMethod.GET)
    @ResponseBody
    public BaseRes getDataLoadJob(@RequestParam(value = "id") String id)
    {
        BaseRes br = new BaseRes();
        try
        {
            DataLoadInfo data = dataLoadJobService.selectDataLoadJobByUid(id);
            br.setError(0);
            br.setData(data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            br.setError(1);
            br.setMsg("获取数据源失败");
        }
        return br;
    }

    /**
     * 删除任务
     * 
     * @param id
     */
    @RequestMapping(value = "/dataloadjob/{id}", method = RequestMethod.DELETE)
    public void delDataLoadJob(@PathVariable String id)
    {
        dataLoadJobService.deleteDataLoadJob(id);
    }

    /**
     * 新增loadJob
     * 
     * @throws ConnException
     */
    @RequestMapping(value = "/dataloadjob/{id}", method = RequestMethod.POST)

    public void addDataLoadJob(@PathVariable String id) throws ConnException
    {
        dataLoadJobService.addDataLoadJob(id);
    }

    @RequestMapping(value = "/dataloadjob/start/{id}", method = RequestMethod.POST)
    public void startLoadJob(@PathVariable String id)
    {
        dataLoadJobService.startLoadJob(id);
    }

    @RequestMapping(value = "/dataloadjob/stop/{id}", method = RequestMethod.POST)
    public void stopLoadJob(@PathVariable String id)
    {
        dataLoadJobService.stopLoadJob(id);
    }

}
