package com.wt.apptest;

import com.wt.repositories.PersonRepository;
import com.wt.repositories.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:13 2017/8/4
 * @Email:tao8.wang@changhong.com
 */
public abstract class SupperTest {
    protected ApplicationContext context = null;

    {
        context = new ClassPathXmlApplicationContext("classpath:application.xml");
    }

    protected void show(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(0));
        }
    }

}
