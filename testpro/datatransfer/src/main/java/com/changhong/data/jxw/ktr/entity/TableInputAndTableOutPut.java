package com.changhong.data.jxw.ktr.entity;

import java.util.List;

public class TableInputAndTableOutPut
{
    private String serviceIp;
    private int port;
    private String username;
    private String password;
    private String databaseName;
    private String tableName;
    private List<String> columns;

    public List<String> getColumns()
    {
        return columns;
    }

    public void setColumns(List<String> columns)
    {
        this.columns = columns;
    }

    public String getServiceIp()
    {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp)
    {
        this.serviceIp = serviceIp;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public TableInputAndTableOutPut(String serviceIp, int port, String username, String password,
        String databaseName, String tableName)
    {
        super();
        this.serviceIp = serviceIp;
        this.port = port;
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
        this.tableName = tableName;

    }

    public TableInputAndTableOutPut()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString()
    {
        return "DataSourceInfo [serviceIp=" + serviceIp + ", port=" + port + ", username=" + username
            + ", password=" + password + ", databaseName=" + databaseName + ", tableName=" + tableName + "]";
    }

}
