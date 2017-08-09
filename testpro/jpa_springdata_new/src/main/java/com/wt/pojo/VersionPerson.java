package com.wt.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:02 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "version_person")
public class VersionPerson extends AbstractMappedType {

    private String name;

    //@version注解只能用在整形（Short，Long，Integer）或者timeStamp类型，原因是：更新完之后verison字段会改变，自增或者其他方式
    //一般情况下这个直接会配合@OptimisticLocking一起使用，这里只使用@Version，也能实现乐观锁锁定，
    //但是更新的时候不能直接new一个对象，通过给这个对象设置id来更新，需要先查询，详细见更新测试方法
    //可以不为该属性提供get/set方法,让业务逻辑不会对此字段进行修改
    @Version
    private Integer version;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
