package com.changhong.yyhl.data.encrypt;

import org.junit.Test;



/**
 * Created by Administrator on 16-9-14.
 */
public class SHAUtilsTest {

    @Test
    public void shaTest()
    {
        System.out.println(SHAUtils.requestParam("abcd"));
        System.out.println(SHAUtils.requestParam("abcd"));
        System.out.println(SHAUtils.requestParam("abcd"));
        System.out.println(SHAUtils.requestParam("abcd"));
    }
}
