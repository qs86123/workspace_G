package com.wt.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:16 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "user")
public class User extends AbstractMappedType {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
