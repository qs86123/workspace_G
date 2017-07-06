package com.changhong.data.jxw.ktr.entity;

public class DataPackageTable
{
    private String id;
    private String tableSource;
    private String tableName;
    private String targetTable;
    private String sumaryTalbe;
    private String dpId;

    public String getDpId()
    {
        return dpId;
    }

    public void setDpId(String dpId)
    {
        this.dpId = dpId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTableSource()
    {
        return tableSource;
    }

    public void setTableSource(String tableSource)
    {
        this.tableSource = tableSource;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTargetTable()
    {
        return targetTable;
    }

    public void setTargetTable(String targetTable)
    {
        this.targetTable = targetTable;
    }

    public String getSumaryTalbe()
    {
        return sumaryTalbe;
    }

    public void setSumaryTalbe(String sumaryTalbe)
    {
        this.sumaryTalbe = sumaryTalbe;
    }

}
