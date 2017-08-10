package com.wt.pojo;

import javax.persistence.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:41 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "many_to_one_phone")
public class ManyToOnePhone extends AbstractMappedType {

    private String price;

    @ManyToOne
    @JoinColumn(
            //name指定外键栏位，即外键所在的列名
            name = "person_id",
            //foreignKey指定外键的名称，如果没有会随机生成，具体可以查看表结构-->外键
            foreignKey = @ForeignKey(name = "PERSON_ID_FK")
    )
    private ManyToOneUser user;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ManyToOneUser getUser() {
        return user;
    }

    public void setUser(ManyToOneUser user) {
        this.user = user;
    }
}
