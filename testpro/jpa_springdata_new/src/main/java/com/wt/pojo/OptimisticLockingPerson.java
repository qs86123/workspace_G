package com.wt.pojo;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:40 2017/8/9
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "optimistic_locking_person")
//乐观锁锁定是基于where语句的，更新的时候将锁定的字段与更行之前进行比对，如果没有被更改过就执行更新，具体查看更新测试会更清楚
//以下两个注解配套使用，使用乐观锁锁定的时候查看更新的时候输出语句
//type：ALL:基于所有属性执行乐观锁
//NONE：不执行乐观锁，即使有@Version注解也直接忽略掉
//VERSION:默认值，只对@Version注解标记的字段执行乐观锁锁定
//DIRTY：基于脏字段执行乐观锁锁定，修改的那些字段，就将那些字段作为乐观锁锁定
@OptimisticLocking(type = OptimisticLockType.DIRTY)
//@DynamicUpdate注解需要使用，因为ALL和DIRTY在更新的时候需要考虑所有的字段属性
@DynamicUpdate
//@SelectBeforeUpdate注解是DIRTY时使用，更新之前查询，实际测试不加也会在更新之前查询，可以从更新的时候看到会输出两条select语句，
//第一条是findone查询的语句，第二条是更新前查询的语句，详细看更新测试
//@SelectBeforeUpdate

//官方文档解释：您还应该使用@dynamicupdate，因为UPDATE语句必须考虑所有的脏实体属性值，以及@selectbeforeupdate注释，以便分离的实体能够被会话更新(实体)操作正确地处理。
public class OptimisticLockingPerson extends AbstractMappedType {

    private String name;

    private String country;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
