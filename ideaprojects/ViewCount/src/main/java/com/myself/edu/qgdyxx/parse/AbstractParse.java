package com.myself.edu.qgdyxx.parse;

import java.io.FileInputStream;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:14 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public abstract class AbstractParse {

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
        FileInputStream fis = new FileInputStream(file);
        byte[] b = new byte[4096];
        int len = -1;
        while ((len = fis.read(b)) != -1) {
            sb.append(new String(b, 0, len, "UTF-8"));
        }
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

    public abstract Object parse();

}
