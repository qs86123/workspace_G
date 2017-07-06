package com.changhong.data.jxw.ktr.entity;

import java.util.Date;

public class CronLog
{
    private String id;
    private String cId;
    private String info;
    private Date runStart;
    private Date runEnd;
    private String result;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getcId()
    {
        return cId;
    }

    public void setcId(String cId)
    {
        this.cId = cId;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public Date getRunStart()
    {
        return runStart;
    }

    public void setRunStart(Date runStart)
    {
        this.runStart = runStart;
    }

    public Date getRunEnd()
    {
        return runEnd;
    }

    public void setRunEnd(Date runEnd)
    {
        this.runEnd = runEnd;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

}
