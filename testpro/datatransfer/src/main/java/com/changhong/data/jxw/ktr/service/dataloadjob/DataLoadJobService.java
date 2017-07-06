package com.changhong.data.jxw.ktr.service.dataloadjob;

import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.ktr.entity.Cron;
import com.changhong.data.jxw.ktr.entity.CronLog;
import com.changhong.data.jxw.ktr.entity.res.DataLoadInfo;

public interface DataLoadJobService
{
    void deleteDataLoadJob(String id);

    void addDataLoadJob(String id) throws ConnException;

    DataLoadInfo selectDataLoadJobByUid(String uid);

    Cron selectCronByDPid(String dpId);

    CronLog selectCronLogByCornid(String cronId);

    void startLoadJob(String id);

    void stopLoadJob(String id);
}
