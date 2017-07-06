package com.changhong.data.jxw.ktr.imlp;

import org.dom4j.Element;

import com.changhong.data.jxw.db.entity.ktr.DataSourceInfo;
import com.changhong.data.jxw.ktr.IParseKtrFile;
import com.changhong.data.jxw.ktr.base.ParseKtrFile;

public class ParseKtrFileMongo extends ParseKtrFile implements IParseKtrFile
{

    @Override
    public void updateNodeInfo(Element root, String[] types, DataSourceInfo[] tableInputAndTableOutput)
    {

    }

}
