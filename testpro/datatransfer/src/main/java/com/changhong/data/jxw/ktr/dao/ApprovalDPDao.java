package com.changhong.data.jxw.ktr.dao;

import org.apache.ibatis.annotations.Mapper;

import com.changhong.data.jxw.ktr.entity.DataPackage;

@Mapper
public interface ApprovalDPDao
{
    boolean updataDP(DataPackage dataPackage);

    DataPackage findDataPackageById(String id);
}
