package com.changhong.data.common.xlsx.test.service;

import com.changhong.data.common.xlsx.datasource.OutputDataSource;
import com.changhong.data.common.xlsx.test.domain.MultiColUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月18日
 */
public class RepeatDataSources implements OutputDataSource {
    /**
     * 总数据条数
     */
    private long size;
    /**
     *
     */
    private long finshSize;
    private long onceSize=50000;
    public RepeatDataSources(long size)
    {
        this.size=size;
        finshSize=0;
    }
    @Override
    public List<Object> getData() {
        List<Object> list=new ArrayList<>();
        if (hasNext())
        {
            long length=onceSize;
            if (length>size-finshSize)
            {
                length=size-finshSize;
            }
            for (int i=0;i<length;i++)
            {
                list.add(new MultiColUser("这个是地址" + i, "name" + i, new Date(), i, i * 1.1));
            }


            finshSize+=length;
        }
        else
        {
            return null;
        }
        return list;
    }

    @Override
    public boolean hasNext() {
        return finshSize<size;
    }
}
