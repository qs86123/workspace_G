package com.wt.xlsxparse.demo.parse;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:28 2017/6/16
 * @Email:tao8.wang@changhong.com
 */
public abstract class ParseWorker {

    protected ParseWorker nextParseWorker;
    protected File file;
    protected String[] head;
    protected String fileName;
    protected InputStream inputStream;
    protected Map<Integer, Object> body = new HashMap<Integer, Object>();

    public abstract void parse() throws Exception;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ParseWorker getNextParseWorker() {
        return nextParseWorker;
    }

    public void setNextParseWorker(ParseWorker nextParseWorker) {
        this.nextParseWorker = nextParseWorker;
    }

    public Map<Integer, Object> getBody() {
        return body;
    }

    public void setBody(Map<Integer, Object> body) {
        this.body = body;
    }
}
