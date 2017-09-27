package com.wt.repositories;

import com.wt.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    //如果是@Query写的更新和删除语句，必须加@Modifying注解
    @Transactional
    @Modifying
    @Query("delete from Person p where p.name=?1")
    void deleteByNameQueryAnnotation(String name);

    @Transactional
    @Modifying
    @Query("delete from Person p where p.name in (?1)")
    void deleteByNameIn(List<String> names);

    @Transactional
    @Modifying
    @Query("delete from Person p where p.name in (?1)")
    void deleteByNameIn2(String[] names);

    //如果使用jpa的方法解析的话，执行删除都是先查询，将查询到的数据通过id逐个删除，效率上来讲自己的写的语句效率高
    @Transactional
    void deleteByNameIn(String[] names);

    List<Person> findByNameNameQuery(String name);

    Person findById(String id);

    List<Person> findNameDistinctNameByAge(String age);

    List<PersonNoAddresses> findByAge(String age);

//    List<PersonNoAddresses> findDistinctNameByAge(String age);

    //支持级联属性address.addrName
    Person findByAddressAddrName(String addrName);


    //不通
//    @Query(value = "SELECT * FROM PERSON WHERE NAME = ?1",
//            countQuery = "SELECT count(*) FROM PERSON WHERE NAME = ?1",
//            nativeQuery = true)
//    Page<Person> findByNameNativeQuery(String lastname, Pageable pageable);


}
