package com.wt.pojo;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.ColumnTransformers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:25 2017/8/7
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "column_transformer_entity")
public class ColumnTransformerEntity extends AbstractMappedType {

    @Column(name = "pswd")
    //看sql语句输出
    @ColumnTransformer(
            read = "sha(pswd)",
            write = "sha(?)")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ColumnTransformerEntity{" +
                "password='" + password + '\'' +
                '}';
    }
}
