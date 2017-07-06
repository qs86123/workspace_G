package com.changhong.data.jxw.ktr.service.datapushjob;

import java.util.List;

import com.changhong.data.jxw.ktr.entity.DataPushJob;

public interface DataPushJobService
{
    void deleteDataPushJob(String id);

    List<DataPushJob> getAllDataPushJob(String uId);
}
