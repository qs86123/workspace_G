package com.changhong.data.jxw.ktr.dao.impl;

import com.changhong.data.jxw.ktr.dao.ApprovalDPDao;
import com.changhong.data.jxw.ktr.entity.DataPackage;

public class ApprovalDPDaoImpl implements ApprovalDPDao
{

    // @Autowired
    // private ApprovalDPMapper approvalDPMapper;

    public boolean updataDP(DataPackage dataPackage)
    {
        // approvalDPMapper.updateApprovalDP(dataPackage);
        return true;
    }

    public DataPackage findDataPackageById(String id)
    {
        // return approvalDPMapper.findDataPackageById(id);
        return null;
    }
}
