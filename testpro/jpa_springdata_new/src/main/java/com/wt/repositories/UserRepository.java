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

    //jpa的方法distinct只能是主键，所以这里查询distinct的时候用原生方法查询,因为是原生查询封装实体，所以这里查询的字段必须是所有，也就是说这个查询了name，id，age，少一个都不行
    @Query(value = "select distinct a.name,a.id,a.age from user a where a.age=?1",nativeQuery = true)
    List<User> findDistinctNameByAge(int age);

}
