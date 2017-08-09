package com.wt.apptest;

import com.wt.pojo.VersionPerson;
import com.wt.repositories.VersionPersonRepository;
import org.junit.Test;

import java.util.Random;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:04 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
public class VersionPersonTest extends SupperTest {

    private VersionPersonRepository vpr = context.getBean(VersionPersonRepository.class);

    @Test
    public void save() {
        VersionPerson vp = new VersionPerson();
        vp.setName("name");
        vpr.save(vp);
    }

    //注意：只使用@Version注解，不适用@OptiimisticLock配合时，这种直接new一个对象设置id来更新的方式不起作用
    //看下一个更新方法，自己查询
    @Test
    public void update2() {
        VersionPerson vp = new VersionPerson();
        vp.setId("1");
        vp.setName("name" + new Random().nextInt(100));
        vpr.save(vp);
    }

    @Test
    public void update() {
        VersionPerson vp = vpr.findOne("2");
        vp.setName("name" + new Random().nextInt(100));
        vpr.save(vp);
    }


}
