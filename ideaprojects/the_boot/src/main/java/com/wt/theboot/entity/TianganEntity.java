package com.wt.theboot.entity;

/**
 * @Description
 * @Author: wangtao
 * @Date:19:05 2017/6/7
 * @Email:tao8.wang@changhong.com
 */
public class TianganEntity {

    private String[] tiangan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    public String get(int index) {
        return tiangan[index - 1];
    }

    public int find(String tg) {
        for (int i = 0; i < 10; i++)
            if (tiangan[i].equals(tg))
                return i + 1;
        return -1;
    }

    public String[] getTiangan() {
        return tiangan;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("天干：");
        for (String s : tiangan)
            sb.append(s + ",");
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }
}
