package com.wt.apptest;

import com.wt.pojo.DefaultValueEntity;
import com.wt.repositories.DefaultValueEntityDao;
import org.junit.Test;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:10 2017/10/12
 * @Email:tao8.wang@changhong.com
 */
public class DefaultValueEntityTest extends SupperTest {

    private DefaultValueEntityDao dao;

    {
        dao = context.getBean(DefaultValueEntityDao.class);
    }

    @Test
    public void save() {
        dao.save(new DefaultValueEntity());
    }

    @Test
    public void dynamicUpdate() {
        DefaultValueEntity d=dao.findOne("1");
        d.setAge(22);
        dao.save(d);
    }
}
