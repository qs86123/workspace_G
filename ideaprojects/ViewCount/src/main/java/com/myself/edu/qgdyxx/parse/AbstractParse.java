package com.myself.edu.qgdyxx.parse;

import com.myself.edu.movedata.utils.MysqlUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:14 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public abstract class AbstractParse {

    protected Connection conn;

    protected String data = null;
    protected String file;

    public AbstractParse() {
    }

    public AbstractParse(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void readFile() throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = null;
        //使用readLine方法，一次读一行
        while ((s = br.readLine()) != null) {
            sb.append(System.lineSeparator() + s);
        }
        br.close();
        data = sb.toString();
    }

    public final Object doParse() {
        try {
            readFile();
            return parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void init() {
        if (conn == null) {
            try {
                this.conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract Object parse();

}
