package com.wt.apptest;

import com.wt.pojo.OptimisticLockingPerson;
import com.wt.repositories.OptimisticLockingPersonRepository;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:42 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
public class OptimisticLockingPersonTest extends SupperTest {

    private OptimisticLockingPersonRepository opr = context.getBean(OptimisticLockingPersonRepository.class);

    @Test
    public void save() {
        OptimisticLockingPerson p = new OptimisticLockingPerson();
        p.setName("wt");
        p.setCountry("country");
        p.setCreateTime(new Date());
        opr.save(p);
    }

    @Test
    public void update() {
        OptimisticLockingPerson p = new OptimisticLockingPerson();
        p.setId("1");
        p.setName("wt");
        p.setCountry("country");
        p.setCreateTime(new Date());
        opr.save(p);
    }


    //使用OptimisticLockType.DIRTY的时候，需要先查询在更新，
    //原因：如果直接new一个对象出来，就相当于是把这个对象的所有属性都设置了，再去更新的时候就会对所有属性都加乐观锁，相当于是是用了OptimisticLockType.All
    @Test
    public void updateDirty1() {
        //只改createTime，看输出语句where部分
        OptimisticLockingPerson p = opr.findOne("1");
        p.setCreateTime(new Date());
        opr.save(p);
    }

    @Test
    public void updateDirty2() {
        //该createTime和country，看输出语句where部分
        OptimisticLockingPerson p = opr.findOne("1");
        p.setCountry("country" + new Random().nextInt(100));
        p.setCreateTime(new Date());
        opr.save(p);
    }

}
