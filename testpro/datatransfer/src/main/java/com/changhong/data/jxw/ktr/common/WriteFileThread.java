package com.changhong.data.jxw.ktr.common;

import com.changhong.data.jxw.ktr.dao.ApprovalDPDao;
import org.springframework.beans.factory.annotation.Autowired;

public class WriteFileThread implements Runnable
{
    @Autowired
    private ApprovalDPDao approvalDPDao;

    @Override
    public void run()
    {
        // 查询数据源

    }

}
