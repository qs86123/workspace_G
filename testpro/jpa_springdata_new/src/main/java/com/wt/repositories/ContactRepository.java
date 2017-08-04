package com.wt.repositories;

import com.wt.pojo.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:11 2017/8/4
 * @Email:tao8.wang@changhong.com
 */
public interface ContactRepository extends JpaRepository<Contact, Serializable> {

    Contact findById(String id);

    //支持级联属性，name.first
    List<Contact> findByNameFirst(String first);


}
