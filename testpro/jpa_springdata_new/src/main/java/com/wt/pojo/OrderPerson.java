package com.wt.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:27 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "order_person")
public class OrderPerson extends AbstractMappedType {

    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //指定查询关联的表排序方式沒有指定字段的话默认为主键，因为默认是懒加载，这里指定加载方式为EAGER好测试
    //排序还有个注解是@OrderColumn(name="order_id")，但是测试有问题，会在phone表中建立另外一个字段order_id，hibernate用来排序，sql中查询不会用到此字段，
    //但是测试的时候查出来的phone集合会比实际条数多（先插入3条，删除1条，集合大小是3条），而且每条记录都是一样的
    //测试这个注解请看官方文档2.8.5
    @OrderBy("number desc,`type` asc")
    private List<OrderPhone> phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderPhone> getPhone() {
        return phone;
    }

    public void setPhone(List<OrderPhone> phone) {
        this.phone = phone;
    }

}
