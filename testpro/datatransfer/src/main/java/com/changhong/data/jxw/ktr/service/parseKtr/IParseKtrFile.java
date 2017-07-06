package com.changhong.data.jxw.ktr.service.parseKtr;

import org.dom4j.Element;

import com.changhong.data.jxw.ktr.entity.TableInputAndTableOutPut;

public interface IParseKtrFile
{
    void updateNodeInfo(Element root, String[] types, TableInputAndTableOutPut[] tableInputAndTableOutput);
}
