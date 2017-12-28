package com.myself.edu.movedata.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:43 2017/11/13
 * @Email:tao8.wang@changhong.com
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        parseColumnToFile();
        parsesql();
    }

    public static void parsesql() throws Exception {
        InputStream is = new FileInputStream(new File("C:\\Users\\The_kid\\Desktop\\党务系统数据-20171108.xlsx"));
        SqlXslxParse pw = new SqlXslxParse();
        pw.setOldFile("C:\\Users\\The_kid\\Desktop\\党务系统数据库.xlsx");
        pw.setInputStream(is);
        pw.setOutFile("D:/sql.txt");
        pw.parse();
    }

    /**
     * 将两个excel都存在的字段输出到文本
     *
     * @throws Exception
     */
    public static void parseColumnToFile() throws Exception {
        InputStream is = new FileInputStream(new File("C:\\Users\\The_kid\\Desktop\\党务系统数据-20171108.xlsx"));
        ColumnXslxOutParse pw = new ColumnXslxOutParse();
        pw.setOldFile("C:\\Users\\The_kid\\Desktop\\党务系统数据库.xlsx");
        pw.setInputStream(is);
        pw.setOutFile("D:/columnsMap.properties");
        pw.parse();
    }


}
