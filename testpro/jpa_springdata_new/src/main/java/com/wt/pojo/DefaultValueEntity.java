package com.wt.pojo;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:07 2017/10/12
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "default_value")
@DynamicInsert
@DynamicUpdate
public class DefaultValueEntity extends AbstractMappedType {

    @ColumnDefault("028000000000")
    private String tel;
    @ColumnDefault("23")
    private Integer age;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}