package com.wt.repositories;

import com.wt.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:05 2017/8/1
 * @Email:tao8.wang@changhong.com
 */
public interface PersonRepository extends JpaRepository<Person, Serializable> {
    List<Person> findByName(String name);

    //delete和remove意思一样，可以返回Long，List
    //返回值为long的时候直接返回删除的条数
    @Transactional
    Long deleteByName(String lastname);

    @Transactional
    List<Person> removeByName(String lastname);

    List<Person> findByNameNameQuery(String name);

    Person findById(String id);

    List<PersonNoAddresses> findByAge(String age);


    //不通
//    @Query(value = "SELECT * FROM PERSON WHERE NAME = ?1",
//            countQuery = "SELECT count(*) FROM PERSON WHERE NAME = ?1",
//            nativeQuery = true)
//    Page<Person> findByNameNativeQuery(String lastname, Pageable pageable);


}
