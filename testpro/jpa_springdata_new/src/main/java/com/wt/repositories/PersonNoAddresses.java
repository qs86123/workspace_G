package com.wt.repositories;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Description
 * @Author: wangtao
 * @Date:13:08 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
public interface PersonNoAddresses {
    //获取目标属性的age属性
    String getAge();

    //获取目标name属性
    @Value("#{target.name}")
    String getPersonName();

    //支持字符串连接
    @Value("姓名：#{target.name}---性别：#{target.sex}")
    String getNameAndSex();

    //支持级联属性及三目运算符
    @Value("#{(target.address==null?'address_id:null':'address_id:'+target.address.id)}")
    String getAddressId();

    //支持方法调用
    @Value("#{(target.password == null||target.password.empty) ? null : ((target.password+'1').equals('12341')) ? 'empty':target.password}")
    String getPassword();

}
