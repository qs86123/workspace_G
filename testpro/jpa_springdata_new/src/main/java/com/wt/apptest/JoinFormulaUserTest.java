package com.wt.apptest;

import com.wt.pojo.JoinformulaUser;
import com.wt.repositories.JoinFormulaUserRepository;
import org.junit.Test;

import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:44 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
public class JoinFormulaUserTest extends SupperTest {

    private JoinFormulaUserRepository jfur = context.getBean(JoinFormulaUserRepository.class);

    @Test
    public void save() {
        JoinformulaUser user = new JoinformulaUser();
        user.setFirstName("wang");
        user.setPhoneNumber("+40-123-4567");
        jfur.save(user);
    }

    @Test
    public void find() {
        JoinformulaUser user = jfur.findOne("1");
//        JoinformulaUser user = all.get(0);
        System.out.println(user.getCountry().getName());
        System.out.println(user.getCountry2().getPrimaryLanguage());
    }

}
