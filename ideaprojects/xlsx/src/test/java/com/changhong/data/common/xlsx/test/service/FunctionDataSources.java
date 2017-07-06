package com.changhong.data.common.xlsx.test.service;

import com.changhong.data.common.xlsx.datasource.OutputDataSource;
import com.changhong.data.common.xlsx.test.domain.FunctionDomin;
import com.changhong.data.common.xlsx.test.domain.MultiColUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月18日
 */
public class FunctionDataSources implements OutputDataSource {
    /**
     * 总数据条数
     */
    private long size;
    private int finshSize;
    private int onceSize=50000;
    public FunctionDataSources(int size)
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
            for (int i=finshSize;i<finshSize+length;i++)
            {
                FunctionDomin functionDomin=new FunctionDomin();
                functionDomin.setABoolean(true);
                functionDomin.setADouble(i*1.1);
                functionDomin.setAnInt(i);
                functionDomin.setDate(new Date(i*1000000l));
                functionDomin.setString("数据"+(i+1));
                list.add(functionDomin);
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
