package com.changhong.data.jxw.ktr.entity;

import java.util.Date;

public class DataPackage
{
    private String id;
    private String dsId;
    private String name;
    private Date addTime;
    private String description;

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

    public Date getAddTime()
    {
        return addTime;
    }

    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDsId()
    {
        return dsId;
    }

    public void setDsId(String dsId)
    {
        this.dsId = dsId;
    }

    public DataPackage(String id, String dsId, String name, Date addTime, String description)
    {
        super();
        this.id = id;
        this.dsId = dsId;
        this.name = name;
        this.addTime = addTime;
        this.description = description;
    }

    public DataPackage()
    {
        super();
        // TODO Auto-generated constructor stub
    }

}
