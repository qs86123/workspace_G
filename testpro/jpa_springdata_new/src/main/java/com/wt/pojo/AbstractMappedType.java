package com.wt.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:32 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
@MappedSuperclass
//是否抽象都无所谓
public abstract class AbstractMappedType {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    //参数： name:生成器的名字
    //      strategy:生成器的实现类全限定类名
    //      parameter.name:name的值全部是生成器是实现类里面的屬性的值，具体去看实现类，不太明白
    // optimizer：优化策略，可以见官方文档Optimizer
//    @GenericGenerator(
//            name = "product_generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "product_sequence"),
//                    @Parameter(name = "initial_value", value = "1"),
//                    @Parameter(name = "increment_size", value = "3"),
//                    @Parameter(name = "optimizer", value = "pooled-lo")
//            }
//    )
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
