package com.wt.repositories;

import com.wt.pojo.MapPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:11 2017/8/11
 * @Email:tao8.wang@changhong.com
 */
public interface MapPersonRepository extends JpaRepository<MapPerson,Serializable>{
}
