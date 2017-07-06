package com.wt.theboot.service;

import com.wt.theboot.entity.DizhiEntity;
import com.wt.theboot.entity.TGDZAttrbute;
import com.wt.theboot.entity.TianganEntity;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author: wangtao
 * @Date:19:29 2017/6/7
 * @Email:tao8.wang@changhong.com
 */
@Service
public class JiaziService {

    private TianganEntity tge = new TianganEntity();
    private DizhiEntity dze = new DizhiEntity();
    private TGDZAttrbute attre = new TGDZAttrbute();

    public String get60Jiazi() {
        StringBuffer sb = new StringBuffer();
        int i = 0, j = 0, count = 0;
        while (count++ < 60) {
            if (++i > 10)
                i = 1;
            if (++j > 12)
                j = 1;
            sb.append(tge.get(i) + dze.get(j));
            if (count % 2 == 0)
                sb.append("--" + attre.get(count / 2) + "  \n");
        }
        String result = sb.toString();
        return result;
    }

    public String getJiaziAttr(String str) {
        int i, j;
        if ((i = tge.find(str.substring(0, 1))) == -1)
            return "年号有误";
        if ((j = dze.find(str.substring(1, 2))) == -1)
            return "年号有误";
        //计算当前甲子在60甲子中排第几位
        int cha = i - j, jiaziIndex = 0;
        //如果天干序号减去地支序号不能被2整除，说明有误
        if (cha % 2 != 0)
            return "年号有误";
        //天干序号小于地支序号时
        if (cha < 0)
            //例如甲申，甲1，申9，(12+(1-9))*6+(12-9)=21
            jiaziIndex = (12 + cha) * 6 - 12 + j;
        else
            //天干序号大于地支序号时
            //例如：丙子，丙3，子1,(3-1)*6+1=13
            jiaziIndex = (i - j) * 6 + j;
        return attre.get((jiaziIndex + 1) / 2);
    }

}
