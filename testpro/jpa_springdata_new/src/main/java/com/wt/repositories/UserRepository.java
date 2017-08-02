package com.wt.repositories;

import com.wt.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:18 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
public interface UserRepository extends JpaRepository<User, Serializable> {

    @Query("select u from #{#entityName} u where u.name=?1")
    List<User> findByNameNameQuery(String name);

}
