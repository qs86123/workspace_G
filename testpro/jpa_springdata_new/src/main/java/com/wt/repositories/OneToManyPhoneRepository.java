package com.wt.repositories;

import com.wt.pojo.OneToManyPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:31 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public interface OneToManyPhoneRepository extends JpaRepository<OneToManyPhone, Serializable>{
}
