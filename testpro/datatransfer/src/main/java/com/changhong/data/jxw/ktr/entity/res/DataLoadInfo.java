package com.changhong.data.jxw.ktr.entity.res;

import java.util.List;

import com.changhong.data.jxw.ktr.entity.Cron;
import com.changhong.data.jxw.ktr.entity.CronLog;
import com.changhong.data.jxw.ktr.entity.DataLoadJob;

public class DataLoadInfo
{
    private DataLoadJob dataLoadJob;
    private Cron cron;
    private List<CronLog> list;

    public DataLoadJob getDataLoadJob()
    {
        return dataLoadJob;
    }

    public void setDataLoadJob(DataLoadJob dataLoadJob)
    {
        this.dataLoadJob = dataLoadJob;
    }

    public Cron getCron()
    {
        return cron;
    }

    public void setCron(Cron cron)
    {
        this.cron = cron;
    }

    public List<CronLog> getList()
    {
        return list;
    }

    public void setList(List<CronLog> list)
    {
        this.list = list;
    }

}
