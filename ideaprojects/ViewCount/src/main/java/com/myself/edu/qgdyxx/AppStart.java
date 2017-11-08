package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description
 * @Author: wangtao
 * @Date:20:36 2017/11/6
 * @Email:tao8.wang@changhong.com
 */
public class AppStart {
    public static void main(String[] args) {
        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
//        System.out.println("----------zTree---start");
//        ZTreeData.zTree(webcookie + cookie);
        //一次性获取所有党员信息
//        System.out.println("----------入库党员信息---start");
//        DangyuanInfo.dangyuanInfo(webcookie + cookie,false,"D:dangyuanInfo.txt");
//        System.out.println("----------失联党员信息---start");
//        DangyuanInfo.dangyuanInfo(webcookie + cookie, true,"D:失联dangyuanInfo.txt");

        //党员信息太多，分页获取
        //入库党员信息
//        pushDangyuanInfoWithPage(webcookie, cookie, "dangyuanInfo2", false);
        //失联党员信息
        pushDangyuanInfoWithPage(webcookie, cookie, "失联jjdangyuanInfo2", true);

    }

    public static void pushDangyuanInfoWithPage(String webcookie, String cookie, String fileName, boolean iscontact) {
        int page = 1;
        String content = DangyuanInfo2.dangyuanInfo2(webcookie + cookie, iscontact, "D:" + fileName + "_1.txt", page);
        JSONObject json = JSON.parseObject(content);
        int totalPages = json.getIntValue("totalPages");
        page++;
        while (page <= totalPages) {
            DangyuanInfo2.dangyuanInfo2(webcookie + cookie, iscontact, "D:" + fileName + "_" + page + ".txt", page);
            page++;
        }
    }

}
