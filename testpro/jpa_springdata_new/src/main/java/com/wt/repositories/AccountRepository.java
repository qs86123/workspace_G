package com.wt.repositories;

import com.wt.pojo.WhereAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:18 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
public interface AccountRepository extends JpaRepository<WhereAccount, Serializable> {
}
