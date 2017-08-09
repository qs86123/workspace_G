package com.wt.repositories;

import com.wt.pojo.OptimisticLockingPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:40 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
public interface OptimisticLockingPersonRepository extends JpaRepository<OptimisticLockingPerson, Serializable> {
}
