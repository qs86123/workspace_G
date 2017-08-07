package com.wt.apptest;

import com.wt.pojo.DateEntity;
import com.wt.repositories.DateEntityRepository;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:10 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
public class DateEntityTest extends SupperTest {

    private DateEntityRepository der;

    {
        der = context.getBean(DateEntityRepository.class);
    }

    @Test
    public void save() {
        DateEntity de = new DateEntity();
        Date d = new Date();
        de.setDate(d);
        de.setTime(d);
        de.setTimeStamp(d);
        der.save(de);
        List<DateEntity> all = der.findAll();
        for (DateEntity dd : all)
            System.out.println(dd);
    }

}
