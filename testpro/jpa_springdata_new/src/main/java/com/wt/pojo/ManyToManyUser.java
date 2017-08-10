package com.wt.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:40 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "many_to_many_user")
public class ManyToManyUser extends AbstractMappedType {

    private String name;

    // 1. 使用JoinTable来映射中间表
    // 2. JoinColums：映射当前类所在的表在中间表中的外键
    // 2.1 name指定外键列名
    // 2.2 referencedColumnName 指定外键关联当前表的哪一列
    // 3. inverseJoinColumns 映射关联的类所在中间表的外键
    @ManyToMany
    @JoinTable(name = "many_to_many_user_course",
            joinColumns = {@JoinColumn(name = "u_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "c_id", referencedColumnName = "id")})
    private List<ManyToManyCourse> course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManyToManyCourse> getCourse() {
        return course;
    }

    public void setCourse(List<ManyToManyCourse> course) {
        this.course = course;
    }
}
