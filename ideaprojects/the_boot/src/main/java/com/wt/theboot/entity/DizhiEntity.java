package com.wt.theboot.entity;

/**
 * @Description
 * @Author: wangtao
 * @Date:19:12 2017/6/7
 * @Email:tao8.wang@changhong.com
 */
public class DizhiEntity {
    private String[] dizhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    public String get(int index) {
        return dizhi[index - 1];
    }

    public int find(String tg) {
        for (int i = 0; i < 12; i++)
            if (dizhi[i].equals(tg))
                return i + 1;
        return -1;
    }

    public String[] getDizhi() {
        return dizhi;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("地支：");
        for (String s : dizhi)
            sb.append(s + ",");
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

}
