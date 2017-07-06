package com.wt.theboot;

import com.wt.theboot.entity.WuxingEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author: wangtao
 * @Date:18:58 2017/6/7
 * @Email:tao8.wang@changhong.com
 */
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        System.out.println(new WuxingEntity().toString());
        String str1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);
        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
//        SpringApplication.run(AppStart.class, args);
    }
}
