package com.changhong.data.jxw.db.entity.ktr;

public class DataSourceInfo
{
    private String serviceIp;
    private String port;
    private String username;
    private String password;
    private String databaseName;
    private String tableName;

    public String getServiceIp()
    {
        return serviceIp;
    }

    public void setServiceIp(String serviceIp)
    {
        this.serviceIp = serviceIp;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
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

    public DataSourceInfo(String serviceIp, String port, String username, String password,
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

    public DataSourceInfo()
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
