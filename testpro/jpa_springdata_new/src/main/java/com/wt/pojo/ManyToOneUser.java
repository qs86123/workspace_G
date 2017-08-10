package com.wt.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:40 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name="many_to_one_user")
public class ManyToOneUser extends AbstractMappedType {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
