package com.changhong.yywl.data.analyse.common.util;

import java.util.List;

/**
 * Created by Administrator on 16-8-16.
 */
public class ListUtils {
    /**
     * 判断字符串是否为空
     * @param list
     * @return
     */
    public static Boolean isEmpty(List list)
    {
        if(list!=null&&list.size()>0)
        {
            return false;
        }
        return true;
    }
}
