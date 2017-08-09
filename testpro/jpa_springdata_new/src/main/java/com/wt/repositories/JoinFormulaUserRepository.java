package com.wt.repositories;

import com.wt.pojo.JoinformulaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:40 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
public interface JoinFormulaUserRepository extends JpaRepository<JoinformulaUser, Serializable> {
}
