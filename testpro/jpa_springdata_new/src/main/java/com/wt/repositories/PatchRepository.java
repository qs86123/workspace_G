package com.wt.repositories;

import com.wt.pojo.Patch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:46 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
public interface PatchRepository extends JpaRepository<Patch, Serializable> {
}
