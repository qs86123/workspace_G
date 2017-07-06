package com.wt.xlsxparse.demo;

import com.wt.xlsxparse.demo.parse.ParseWorker;
import com.wt.xlsxparse.demo.parse.XlsxParserWorker;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:28 2017/6/16
 * @Email:tao8.wang@changhong.com
 */
public class Test {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream(new File("C:\\Users\\The_kid\\Desktop\\aa.xlsx"));
        ParseWorker pw = new XlsxParserWorker();
        pw.setInputStream(is);
        pw.parse();
        String[] head = pw.getHead();
        for (String s : head)
            System.out.print(s + "   ");
        System.out.println("");
        Map<Integer, Object> body = pw.getBody();
        for (Integer i : body.keySet()) {
            System.out.println(body.get(i));
        }
    }

}
