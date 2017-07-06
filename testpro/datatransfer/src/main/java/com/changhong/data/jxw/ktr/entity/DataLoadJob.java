package com.changhong.data.jxw.ktr.entity;

import java.util.Date;

public class DataLoadJob
{
    private String id;
    private String dpId;
    private String name;
    private String fileName;
    private Date addTime;
    private Date lastRun;
    private String cornId;
    private String uId;

    public String getuId()
    {
        return uId;
    }

    public void setuId(String uId)
    {
        this.uId = uId;
    }

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

    public String getCornId()
    {
        return cornId;
    }

    public void setCornId(String cornId)
    {
        this.cornId = cornId;
    }

}
