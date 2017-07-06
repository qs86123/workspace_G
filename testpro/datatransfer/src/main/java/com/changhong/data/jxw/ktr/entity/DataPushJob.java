package com.changhong.data.jxw.ktr.entity;

import java.util.Date;

public class DataPushJob
{
    private String id;
    private String dpId;
    private String dsId;
    private String uId;
    private String oId;
    private String name;
    private String fileName;
    private Date addTime;
    private Date lastRun;
    private String cronId;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDpId()
    {
        return dpId;
    }

    public void setDpId(String dpId)
    {
        this.dpId = dpId;
    }

    public String getDsId()
    {
        return dsId;
    }

    public void setDsId(String dsId)
    {
        this.dsId = dsId;
    }

    public String getuId()
    {
        return uId;
    }

    public void setuId(String uId)
    {
        this.uId = uId;
    }

    public String getoId()
    {
        return oId;
    }

    public void setoId(String oId)
    {
        this.oId = oId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getLastRun()
    {
        return lastRun;
    }

    public void setLastRun(Date lastRun)
    {
        this.lastRun = lastRun;
    }

    public String getCronId()
    {
        return cronId;
    }

    public void setCronId(String cronId)
    {
        this.cronId = cronId;
    }

}
