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

    //jpa的方法distinct只能是主键，所以这里查询distinct的时候用原生方法查询,因为是原生查询封装实体，
    //所以这里查询的字段必须是所有，也就是说这个查询了name，id，age，少一个都不行
    //因为加了id，id是不可能重复的，所有实际上没有去重，要去重需要去掉id这一列，看下一个方法
    @Query(value = "select distinct a.name,a.id,a.age from user a where a.age=?1",nativeQuery = true)
    List<User> findDistinctNameByAge(int age);

    //上一个方法中查询出来的数据不算是重复的，因为id不同，distinct去重是去掉查询出来的结果的所有字段值都相同的才算相同
    //该方法吧id去掉，去重就可以了，上一个理解错误了
    @Query(value = "select distinct new User(name,age) from User a where a.age=?1")
    List<User> findDistinctNameByAge2(int age);

}
