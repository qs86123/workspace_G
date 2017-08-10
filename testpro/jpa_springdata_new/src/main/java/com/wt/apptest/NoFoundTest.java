package com.wt.apptest;

import com.wt.pojo.OneToManyPhone;
import com.wt.pojo.OneToManyUser;
import com.wt.repositories.OneToManyPhoneRepository;
import com.wt.repositories.OneToManyUserRepository;
import org.junit.Test;

import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:32 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public class NoFoundTest extends SupperTest {

    private OneToManyPhoneRepository pr = context.getBean(OneToManyPhoneRepository.class);
    private OneToManyUserRepository ur = context.getBean(OneToManyUserRepository.class);

    @Test
    public void save() {
        OneToManyPhone phone = new OneToManyPhone();
        phone.setPrice("100");
        pr.save(phone);
        OneToManyUser user = new OneToManyUser();
        user.setName("name");
        ur.save(user);
        OneToManyUser user2 = new OneToManyUser();
        user2.setName("name2");
        ur.save(user2);
    }

    @Test
    public void findPhone() {
        OneToManyPhone phone = pr.findOne("1");
    }

    @Test
    public void findUser() {
        OneToManyUser user = ur.findOne("1");
        List<OneToManyPhone> p = user.getPhone();
        System.out.println(user.getName());
        show(p);
    }

    @Test
    public void delete() {
        pr.delete("1");
//        ur.delete("1");
    }


}
