package com.wt.apptest;

import com.wt.pojo.OrderPerson;
import com.wt.pojo.OrderPhone;
import com.wt.repositories.OrderPersonRepository;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:31 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public class OrderByTest extends SupperTest {

    private OrderPersonRepository op = context.getBean(OrderPersonRepository.class);

    @Test
    public void save() {
        OrderPerson p = new OrderPerson();
        p.setName("aa");
        op.save(p);
    }

    @Test
    public void find() {
//        OrderPerson p = new OrderPerson();
//        p.setName("aa");
//        String id = op.save(p).getId();
        OrderPerson fp = op.findOne("1");
        System.out.println(fp.getPhone().size());
        show(fp.getPhone());
    }

}
