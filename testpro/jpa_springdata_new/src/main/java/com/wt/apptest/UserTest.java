package com.wt.apptest;

import com.wt.pojo.User;
import com.wt.repositories.UserRepository;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserTest extends SupperTest {

    private UserRepository u = null;

    {
        u = context.getBean(UserRepository.class);
    }

    @Test
    public void findUserByNameNameQuery() {
        List<User> ps = u.findByNameNameQuery("wangtao");
        for (User p : ps) {
            System.out.println(p);
        }
    }

}
