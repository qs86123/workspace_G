package com.changhong.data.jxw.ktr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.changhong.data.jxw.ktr.entity.DataPushJob;
import com.changhong.data.jxw.ktr.entity.res.BaseRes;
import com.changhong.data.jxw.ktr.service.datapushjob.DataPushJobService;

@Controller
@RequestMapping("/rest/ktr")
public class DataPushJobController
{
    @Autowired
    private DataPushJobService dataPushJobService;

    @RequestMapping(value = "/datapushjob", method = RequestMethod.DELETE)
    public void delDataLoadJob(@RequestParam(value = "id") String id)
    {
        dataPushJobService.deleteDataPushJob(id);
    }

    @RequestMapping(value = "/datapushjob", method = RequestMethod.GET)
    public BaseRes getDataPushJob(@RequestParam(value = "uId") String uId)
    {
        BaseRes br = new BaseRes();
        try
        {
            List<DataPushJob> list = dataPushJobService.getAllDataPushJob(uId);
            br.setError(0);
            br.setData(list);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            br.setError(1);
            br.setMsg("获取数据源失败");
        }
        return br;

    }
}
