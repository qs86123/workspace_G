package com.wt.theboot.entity;

/**
 * @Description
 * @Author: wangtao
 * @Date:20:34 2017/6/7
 * @Email:tao8.wang@changhong.com
 */
public class WuxingEntity {

    private String[] wuxing = {"金", "水", "木", "火", "土"};

    public String getKe(String wx) {
        return "";
    }

    public String getSheng(String wx) {
        return "";
    }

    @Override
    public String toString() {
        String wxxs = "五行相生：" + "金-->水-->木-->火-->土";
        String wxxk = "五行相克：" + "金-x-木-x-土-x-水-x-火";
        return wxxs + "\n" + wxxk;
    }
}
