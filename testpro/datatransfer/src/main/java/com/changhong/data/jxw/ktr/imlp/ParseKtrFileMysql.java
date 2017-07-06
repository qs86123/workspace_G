package com.changhong.data.jxw.ktr.imlp;

import java.util.List;

import org.dom4j.Element;

import com.changhong.data.jxw.db.entity.ktr.DataSourceInfo;
import com.changhong.data.jxw.ktr.Constant;
import com.changhong.data.jxw.ktr.IParseKtrFile;
import com.changhong.data.jxw.ktr.base.ParseKtrFile;

public class ParseKtrFileMysql extends ParseKtrFile implements IParseKtrFile
{
    private static ParseKtrFileMysql parseKtrFile = new ParseKtrFileMysql();

    private ParseKtrFileMysql()
    {
    }

    /**
     * 获取parseKtrFile对象
     * 
     * @return
     */
    public static ParseKtrFileMysql getParseKtrFile()
    {
        if (null == parseKtrFile)
        {
            return new ParseKtrFileMysql();
        }
        return parseKtrFile;
    }

    /**
     * 更新文档节点
     * 
     * @param root 根节点
     * @param types
     * @param tableInputAndTableOutput 输入表信息输出表信息
     */
    @Override
    public void updateNodeInfo(Element root, String[] types, DataSourceInfo[] tableInputAndTableOutput)
    {
        for (String type : types)
        {
            @SuppressWarnings("unchecked")
            List<Element> elements = root.elements(type);
            Element element = null;
            DataSourceInfo sourceInfo = null;
            for (int i = 0; i < elements.size(); i++)
            {
                element = elements.get(i);
                sourceInfo = tableInputAndTableOutput[i];
                if (Constant.CONNECTION.equals(type))
                {
                    element.element(Constant.TABLE_NAME).setText(sourceInfo.getTableName());
                    element.element(Constant.SERVER_IP).setText(sourceInfo.getServiceIp());
                    element.element(Constant.DATABASE).setText(sourceInfo.getDatabaseName());
                    element.element(Constant.PORT).setText(sourceInfo.getPort());
                    element.element(Constant.USER_NAME).setText(sourceInfo.getUsername());
                    element.element(Constant.PASSWORD).setText(sourceInfo.getPassword());
                    element.element(Constant.PASSWORD).setText(sourceInfo.getPassword());

                    updataAttrInfo(element, sourceInfo.getPort());
                }
                if (Constant.STEP.equals(type))
                {
                    if (Constant.TABLE_INPUT.equals(element.element(Constant.TYPE).getText()))
                    {
                        element.element(Constant.CONNECTION).setText(sourceInfo.getTableName());
                        element.element(Constant.SQL).setText(new StringBuffer().append("SELECT &#x2a; FROM ")
                            .append(sourceInfo.getTableName())
                            .toString());
                    }
                    if (Constant.TABLE_OUTPUT.equals(element.element(Constant.TYPE).getText()))
                    {
                        element.element(Constant.CONNECTION).setText(sourceInfo.getTableName());
                        element.element(Constant.TABLE).setText(sourceInfo.getTableName());
                    }
                }
            }
        }

    }

    /**
     * 修改attributes中attribute的默认端口号
     * 
     * @param element
     * @param port
     */
    private void updataAttrInfo(Element element, String port)
    {
        @SuppressWarnings("unchecked")
        List<Element> elements = element.element(Constant.ATTRIBUTES).elements();
        Element ele = null;
        for (Element e : elements)
        {
            ele = e.element(Constant.ATTRIBUTE);
            if (Constant.DEFAULT_PORT.equals(ele.getText()))
            {
                ele.setText(port);
            }
        }
    }
}
