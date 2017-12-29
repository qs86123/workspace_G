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
    public static int page = 1;

    public static void main(String[] args) {
        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
        System.out.println("----------zTree---start");
//        ZTreeData.zTree(webcookie + cookie,"D:zuzhijigou.txt");
        //多媒体组织机构树
        ZTreeData.zTree(webcookie + cookie,"D:DMTzuzhijigou.txt");
        //一次性获取所有党员信息
//        System.out.println("----------入库党员信息---start");
//        DangyuanInfo.dangyuanInfo(webcookie + cookie,false,"D:dangyuanInfo.txt");
//        System.out.println("----------失联党员信息---start");
//        DangyuanInfo.dangyuanInfo(webcookie + cookie, true,"D:失联dangyuanInfo.txt");

        String allcookie="mapid=64a0a160; JSESSIONID=197F5656AB0807AC7BB04E68774B8097.csdj1; SRV=257abe1e-1fa3-4afb-80ae-ab34a4b462fe";

        //党员信息太多，分页获取
        //入库党员信息
//        pushDangyuanInfoWithPage(webcookie, cookie, "dangyuanInfo2", false);
        //失联党员信息,返回的数据28条，与上一个数据全部重复
//        AppStart.page = 1;
//        pushDangyuanInfoWithPage(webcookie, cookie, "dangyuanInfonotconnect2", true);
        //停止党籍的只有一条数据，需要改查询条件，单独执行
//        AppStart.page = 1;
//        pushDangyuanInfoWithPage(webcookie, cookie, "dyzt", true);

    }

    public static void pushDangyuanInfoWithPage(String webcookie, String cookie, String fileName, boolean iscontact) {
        String content = DangyuanInfo2.dangyuanInfo2(webcookie + cookie, iscontact, "D:" + fileName + "_" + page + ".txt", page);
        JSONObject json = JSON.parseObject(content);
        int totalPages = json.getIntValue("totalPages");
        page++;
        while (page <= totalPages) {
            DangyuanInfo2.dangyuanInfo2(webcookie + cookie, iscontact, "D:" + fileName + "_" + page + ".txt", page);
            page++;
        }
    }

}
