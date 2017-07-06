package com.wt.theboot.entity;

/**
 * @Description
 * @Author: wangtao
 * @Date:19:21 2017/6/7
 * @Email:tao8.wang@changhong.com
 */
public class TGDZAttrbute {

    private String[] attr = {"海中金", "炉中火", "大林木", "路旁土", "剑锋金", "山头火",
            "涧下水", "城头土", "白蜡金", "杨柳木", "泉中水", "屋上土",
            "霹雳火", "松柏木", "长流水", "沙中金", "山下火", "平地木",
            "壁上土", "金箔金", "覆灯火", "天河水", "大驿土", "钗钐金",
            "桑松木", "大溪水", "沙中土", "天上火", "石榴木", "大海水"};

    public String get(int index) {
        return attr[index - 1];
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("天干地支属性：");
        for (String s : attr)
            sb.append(s + ",");
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

}
