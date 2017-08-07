package com.wt.apptest;

import com.wt.pojo.ColumnTransformerEntity;
import com.wt.repositories.ColumnTransformerEntityRepository;
import org.junit.Test;

import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:04 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
public class ColumnTransformerEntityTest extends SupperTest {

    private ColumnTransformerEntityRepository cfe;

    {
        cfe = context.getBean(ColumnTransformerEntityRepository.class);
    }

    @Test
    public void test() {
        ColumnTransformerEntity c = new ColumnTransformerEntity();
        c.setPassword("123456");
        cfe.save(c);
        List<ColumnTransformerEntity> all = cfe.findAll();
        show(all);
    }

}
