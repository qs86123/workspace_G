package com.wt.apptest;

import com.wt.pojo.FormulaEntity;
import com.wt.repositories.FormulaEntityRepository;
import org.junit.Test;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:10 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
public class FormulaEntityTest extends SupperTest {

    private FormulaEntityRepository fer = context.getBean(FormulaEntityRepository.class);

    @Test
    public void save() {
        FormulaEntity fe = new FormulaEntity();
        fe.setCredit(100);
        fe.setRate(0.2);
        fer.save(fe);
        show(fer.findAll());
    }

}
