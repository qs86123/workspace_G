package com.changhong.data.jxw.ktr;

import org.dom4j.Element;

import com.changhong.data.jxw.db.entity.ktr.DataSourceInfo;

public interface IParseKtrFile
{
    void updateNodeInfo(Element root, String[] types, DataSourceInfo[] tableInputAndTableOutput);
}
