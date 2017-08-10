package com.wt.pojo;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:51 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "one_to_many_phone")
public class OneToManyPhone extends AbstractMappedType implements Serializable {

    private String price;

    @ManyToOne(fetch = FetchType.EAGER)
    //name指定当前外键的名字
    //referencedColumnName指定当前外键关联目标表的那一列，不写默认为id,该列如果不是id，则会自动标为unique
    @JoinColumn(name = "u_name", referencedColumnName = "name")
    //查询出来之后如果关联的user不存在则直接用null替代，
    //测试结果：
    //user表加一列：id=1，name=wt
    //默认是Exception，该情况下不用加该注解，因为建立的表会有外键关联，所以不存在为空的情况，因为如果外键（u_name）得值在关联表中不存在（即不为wt时）的话插入是插不进去的，即插入异常
    //IGNORE：将数据库相关表删除，重新运行，可以看到建立的表已经没有外键关联了，这个时候，phone表中u_name属性就可以不为wt了，即查询的时候就可能为空,这个注解就是解决为空时使用null代替
    //实际上我们可以不用这个注解，直接建立外键关联即刻，会想到，如果查询user的时候，user中的phone为空的时候会不会报错，答案是不会，直接是null，所以一般轻款项这个注解不常用
    //可以这样理解：这个注解就是取消外键在库中的关联，但是又是外键，是取消关联的外键
    @NotFound(action = NotFoundAction.IGNORE)
    private OneToManyUser user;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public OneToManyUser getUser() {
        return user;
    }

    public void setUser(OneToManyUser user) {
        this.user = user;
    }
}
