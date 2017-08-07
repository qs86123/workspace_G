package com.wt.repositories;

import com.wt.pojo.ColumnTransformerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:03 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
public interface ColumnTransformerEntityRepository extends JpaRepository<ColumnTransformerEntity,Serializable>{
}
