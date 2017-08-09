package com.wt.repositories;

import com.wt.pojo.EmbeddablePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:25 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
public interface EmbeddablePersonRepository extends JpaRepository<EmbeddablePerson, Serializable> {

    //级联属性测试
    EmbeddablePerson findByHomeAddressZipCodePostalCode(String postCode);

}
