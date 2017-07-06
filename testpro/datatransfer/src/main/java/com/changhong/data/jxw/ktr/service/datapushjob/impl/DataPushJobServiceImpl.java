package com.changhong.data.jxw.ktr.service.datapushjob.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.changhong.data.jxw.ktr.entity.DataPushJob;
import com.changhong.data.jxw.ktr.service.datapushjob.DataPushJobService;

@Service
public class DataPushJobServiceImpl implements DataPushJobService
{
    // @Autowired
    // private DataPushJobMapper dataPushJobMapper;

    @Override
    public void deleteDataPushJob(String id)
    {
        // dataPushJobMapper.deleteDataLoadJob(id);
    }

    @Override
    public List<DataPushJob> getAllDataPushJob(String uId)
    {

        // return dataPushJobMapper.selectAll(uId);
        return null;
    }

}
