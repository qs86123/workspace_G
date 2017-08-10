package com.wt.repositories;

import com.wt.pojo.OrderPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:30 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public interface OrderPersonRepository extends JpaRepository<OrderPerson, Serializable> {
}
