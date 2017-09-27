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

    //jpa的方法distinct只能是主键，所以这里查询distinct的时候用原生方法查询，PersonTest里面也有原生方法的查询，不过没有调试通过
    @Test
    public void findDistinctNameByAge() {
        List<User> ps = u.findDistinctNameByAge(23);
        for (User p : ps) {
            System.out.println(p);
        }
    }

}
