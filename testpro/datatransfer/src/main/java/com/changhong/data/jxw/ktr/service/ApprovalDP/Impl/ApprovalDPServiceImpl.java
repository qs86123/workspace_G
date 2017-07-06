package com.changhong.data.jxw.ktr.service.ApprovalDP.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.jxw.ktr.dao.ApprovalDPDao;
import com.changhong.data.jxw.ktr.entity.DataPackage;
import com.changhong.data.jxw.ktr.service.ApprovalDP.ApprovalDPService;

@Service
public class ApprovalDPServiceImpl implements ApprovalDPService , Runnable
{
    @Autowired
    private ApprovalDPDao approvalDPDao;
    private DataPackage dp;

    public boolean updataDP(DataPackage dataPackage)
    {
        this.dp = dataPackage;
        if ("pass".equals(dataPackage.getDescription()) && approvalDPDao.updataDP(dataPackage))
        {
            new Thread(new ApprovalDPServiceImpl()).start();

        }
        return true;
    }

    @Override
    public void run()
    {
        // 查询数据源
        DataPackage data = approvalDPDao.findDataPackageById(dp.getId());
        // 查询tagart 不清楚tagart在什么位置

    }

}
