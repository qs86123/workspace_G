package com.changhong.data.jxw.ktr.service.parseKtr.parseKtrImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.changhong.data.jxw.db.exception.ConnException;
import com.changhong.data.jxw.ktr.common.Constant;
import com.changhong.data.jxw.ktr.entity.TableInputAndTableOutPut;
import com.changhong.data.jxw.ktr.service.parseKtr.IParseKtrFile;

public abstract class ParseKtrFile implements IParseKtrFile
{

    private String fileName;

    /**
     * 解析文档，并根据传入的输入表与输出表信息更新文档
     * 
     * @param tableInputAndTableOutput 输入表信息输出表信息
     * @throws ConnException
     */
    public String parseKtrFile(TableInputAndTableOutPut[] tableInputAndTableOutput) throws ConnException
    {
        // if (checkParame(tableInputAndTableOutput))
        // {
        try
        {
            // 读取文件解析
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(Constant.KTR_FILE_PATH));
            Element root = document.getRootElement();

            // 遍历指定节点并修改
            updateNodeInfo(root,
                new String[] {Constant.CONNECTION, Constant.STEP },
                tableInputAndTableOutput);

            fileName = new StringBuffer().append(tableInputAndTableOutput[0].getTableName())
                .append("And")
                .append(tableInputAndTableOutput[1].getTableName())
                .append(".ktr")
                .toString();
            // 保存修改文件
            writeToFile(document, fileName, Constant.ENCODING);

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        // }
        return fileName;
    }

    /**
     * 保存修改好的文件
     * 
     * @param doc
     * @param filePath
     * @param encoding
     */

    public void writeToFile(Document doc, String fileName, String encoding)
    {
        OutputFormat fmt = null;
        XMLWriter xmlWriter = null;
        // SimpleDateFormat format = new SimpleDateFormat("");

        try
        {
            fmt = OutputFormat.createPrettyPrint();
            fmt.setEncoding(encoding);
            xmlWriter = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream(
                    new File(new StringBuffer().append(Constant.WRITE_KTR_PATH).append(fileName).toString())),
                encoding), fmt);
            xmlWriter.write(doc);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (null != xmlWriter)
                {
                    xmlWriter.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 参数校验，传入对象不能存在空字段
     * 
     * @param data
     * @return
     * @throws ConnException
     */
    private boolean checkParame(TableInputAndTableOutPut[] dataSource) throws ConnException
    {
        if (null == dataSource)
        {
            throw new ConnException("传入的参数有误，参数不能为空");
        }
        Field[] fields = null;
        for (TableInputAndTableOutPut tableInputAndTableOutPut : dataSource)
        {
            fields = tableInputAndTableOutPut.getClass().getDeclaredFields();
            for (Field field : fields)
            {
                field.setAccessible(true);

                try
                {
                    if (null == field.get(tableInputAndTableOutPut))
                    {
                        throw new ConnException("传入的参数有空值");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    throw new ConnException("传入的参数有误，请确认传入的参数正确");
                }

            }
        }
        return true;
    }
}
