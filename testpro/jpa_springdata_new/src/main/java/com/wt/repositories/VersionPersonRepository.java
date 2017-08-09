package com.wt.repositories;

import com.wt.pojo.VersionPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:04 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
public interface VersionPersonRepository extends JpaRepository<VersionPerson, Serializable> {
}
