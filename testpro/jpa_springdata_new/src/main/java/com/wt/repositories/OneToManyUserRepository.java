package com.wt.repositories;

import com.wt.pojo.OneToManyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:30 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public interface OneToManyUserRepository extends JpaRepository<OneToManyUser, Serializable> {
}
