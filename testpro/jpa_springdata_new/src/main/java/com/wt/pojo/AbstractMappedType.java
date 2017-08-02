package com.wt.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:32 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
@MappedSuperclass
//是否抽象都无所谓
public abstract class AbstractMappedType {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
