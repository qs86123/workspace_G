package com.changhong.yywl.data.analyse.common;

/**
 * Created by Administrator on 16-9-6.
 */
public class RestApiResponse {
    /**
     * 返回码：200成功
     * 404 失败
     */
    private int code;
    private String msg;
    private Object data;
    public RestApiResponse()
    {
        code=404;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
