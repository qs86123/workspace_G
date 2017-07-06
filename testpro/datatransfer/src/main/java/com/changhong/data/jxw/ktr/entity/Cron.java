package com.changhong.data.jxw.ktr.entity;

import java.util.Date;

public class Cron
{
    private String id;
    private String name;
    private String group;
    private Date addTime;
    private String ep;
    private Integer status;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public String getEp()
    {
        return ep;
    }

    public void setEp(String ep)
    {
        this.ep = ep;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

}
