package com.wt.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:41 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "many_to_many_course")
public class ManyToManyCourse extends AbstractMappedType {

    private String price;

    @ManyToMany(mappedBy = "course")
    private List<ManyToManyUser> user;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ManyToManyUser> getUser() {
        return user;
    }

    public void setUser(List<ManyToManyUser> user) {
        this.user = user;
    }
}
