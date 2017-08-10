package com.wt.pojo;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:50 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "one_to_many_user")
public class OneToManyUser extends AbstractMappedType implements Serializable {

    private String name;

    //cascade：是否级联删除，指的是删除本实体的时候，该实体关联的对象是否一并删除
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OneToManyPhone> phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OneToManyPhone> getPhone() {
        return phone;
    }

    public void setPhone(List<OneToManyPhone> phone) {
        this.phone = phone;
    }
}
