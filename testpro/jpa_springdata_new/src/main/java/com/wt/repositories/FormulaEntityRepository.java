package com.wt.repositories;

import com.wt.pojo.FormulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:18 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
public interface FormulaEntityRepository extends JpaRepository<FormulaEntity, Serializable> {


}
