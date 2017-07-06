package com.changhong.data.jxw.ktr.service.dataloadjob.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.ktr.common.Constant;
import com.changhong.data.jxw.ktr.entity.Cron;
import com.changhong.data.jxw.ktr.entity.CronLog;
import com.changhong.data.jxw.ktr.entity.DataLoadJob;
import com.changhong.data.jxw.ktr.entity.DataPackage;
import com.changhong.data.jxw.ktr.entity.DataPackageColumn;
import com.changhong.data.jxw.ktr.entity.DataPackageTable;
import com.changhong.data.jxw.ktr.entity.DataSource;
import com.changhong.data.jxw.ktr.entity.TableInputAndTableOutPut;
import com.changhong.data.jxw.ktr.entity.res.DataLoadInfo;
import com.changhong.data.jxw.ktr.mapper.DataLoadJobMapper;
import com.changhong.data.jxw.ktr.service.dataloadjob.DataLoadJobService;
import com.changhong.data.jxw.ktr.service.parseKtr.parseKtrImpl.ParseKtrFileMysql;
import com.changhong.data.jxw.ktr.util.HttpClientUtil;

@Service
public class DataLoadJobServiceImpl implements DataLoadJobService
{
    @Autowired
    private DataLoadJobMapper dataLoadJobMapper;

    @Value("${db.ip}")
    private String ip;
    @Value("${db.port}")
    private int port;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${db.Name}")
    private String schema;

    @Override
    public void deleteDataLoadJob(String id)
    {

        DataLoadJob dlj = dataLoadJobMapper.selectDataLoadJobByUid(id);
        Cron cron = dataLoadJobMapper.selectCronByCronId(dlj.getCornId());
        dataLoadJobMapper.deleteCronLog(cron.getId());
        dataLoadJobMapper.deleteCron(dlj.getCornId());
        dataLoadJobMapper.deleteDataLoadJob(id);

    }

    @Override
    public void addDataLoadJob(String id) throws ConnException
    {
        List<String> columns = null;
        // 根据id找到dp
        DataPackage dp = dataLoadJobMapper.selectDataPackageById(id);
        // 根据dpid找到ds
        DataSource ds = dataLoadJobMapper.selectDataSourceById(dp.getDsId());
        // 根据dpid找到dpt
        List<DataPackageTable> dpt = dataLoadJobMapper.selectDataPackageTableAll(id);
        // 根据dptid找到dpc
        for (DataPackageTable dataPackageTable : dpt)
        {
            List<DataPackageColumn> dptc =
                dataLoadJobMapper.selectDataPackageColumnAll(dataPackageTable.getId());

            for (DataPackageColumn dataPackageColumn : dptc)
            {
                columns = new ArrayList<String>();
                columns.add(dataPackageColumn.getColumnName());
            }
            // 写ktr文件
            String fileName =
                ParseKtrFileMysql.getParseKtrFile().parseKtrFile(packagData(ds, dataPackageTable, columns));
            // 穿件cron
            String cronId = createCron();
            createDataLoadJob(cronId, dp.getId(), fileName, ds.getUid());
        }

    }

    @Override
    public DataLoadInfo selectDataLoadJobByUid(String dpId)
    {
        DataLoadJob dlj = dataLoadJobMapper.selectDataLoadJobByUid(dpId);
        Cron cron = dataLoadJobMapper.selectCronByCronId(dlj.getCornId());
        List<CronLog> cronLog = dataLoadJobMapper.selectCronLogByCornid(cron.getId());
        DataLoadInfo info = new DataLoadInfo();
        info.setCron(cron);
        info.setDataLoadJob(dlj);
        info.setList(cronLog);

        return info;

    }

    // 组装数据信息

    private TableInputAndTableOutPut[] packagData(DataSource ds, DataPackageTable dataPackageTable,
        List<String> columns)
    {
        TableInputAndTableOutPut input = new TableInputAndTableOutPut();
        TableInputAndTableOutPut output = new TableInputAndTableOutPut();
        // 组装tableInput信息
        input.setServiceIp(ds.getUrl());
        input.setUsername(ds.getUsername());
        input.setPassword(ds.getPwd());
        input.setPort(ds.getPort());
        input.setDatabaseName(ds.getSchemaName());
        input.setTableName(dataPackageTable.getTableName());
        input.setColumns(columns);
        // 先暂时这样做
        output.setServiceIp(ip);
        output.setUsername(username);
        output.setPassword(password);
        output.setPort(port);
        output.setDatabaseName(schema);
        output.setTableName(dataPackageTable.getTargetTable());
        output.setColumns(columns);

        TableInputAndTableOutPut[] tableInputAndTableOutPut = new TableInputAndTableOutPut[] {input, output };
        return tableInputAndTableOutPut;
    }

    @Override
    public Cron selectCronByDPid(String dpId)
    {
        // TODO Auto-generated method stub

        // Cron cron = dataLoadJobMapper.selectCronLogByCornid(dpId);
        return null;
    }

    @Override
    public CronLog selectCronLogByCornid(String cronId)
    {
        // TODO Auto-generated method stub
        List<CronLog> cronLog = dataLoadJobMapper.selectCronLogByCornid(cronId);
        return null;
    }

    // 创建cron
    private String createCron()
    {
        UUID uuid = UUID.randomUUID();
        Cron cron = new Cron();
        String id = uuid.toString().substring(0, 32);
        cron.setId(id);
        cron.setAddTime(new Date());
        cron.setEp("wait");
        cron.setName("");
        cron.setStatus(new Integer(1));
        dataLoadJobMapper.insertCron(cron);
        return id;
    }

    // 创建loadJob
    private void createDataLoadJob(String cronId, String dpId, String fileName, String uId)
    {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().substring(0, 32);
        DataLoadJob dlj = new DataLoadJob();
        dlj.setId(id);
        dlj.setCornId(cronId);
        dlj.setAddTime(new Date());
        dlj.setDpId(dpId);
        dlj.setFileName(fileName);
        dlj.setName("");
        dlj.setuId(uId);
        dlj.setLastRun(null);
        dataLoadJobMapper.insertDataLoadJob(dlj);
    }

    @Override
    public void startLoadJob(String dpId)
    {
        // TODO Auto-generated method stub
        DataLoadJob dlj = dataLoadJobMapper.selectDataLoadJobByUid(dpId);
        Cron cron = dataLoadJobMapper.selectCronByCronId(dlj.getCornId());
        JSONObject json = new JSONObject();

        json.put("cron_expression", cron.getEp());
        json.put("job_description", "");
        json.put("mailto", "");
        json.put("method", "POST");
        json.put("smsto", "15208388578");
        json.put("url", "");
        json.put("job_group", "chiq3232345444");
        json.put("job_id", dlj.getId());
        json.put("job_status", 1);

        String data = json.toString();
        HttpClientUtil.doPost(Constant.JOB_START_PATH, data, null);
    }

    @Override
    public void stopLoadJob(String dpId)
    {
        DataLoadJob dlj = dataLoadJobMapper.selectDataLoadJobByUid(dpId);
        Cron cron = dataLoadJobMapper.selectCronByCronId(dlj.getCornId());
        JSONObject json = new JSONObject();
        json.put("job_group", "chiq3232345444");
        json.put("job_id", dlj.getId());
        json.put("job_status", 1);
        String data = json.toString();
        HttpClientUtil.doPost(Constant.JOB_STOP_PATH, data, null);

    }

}
