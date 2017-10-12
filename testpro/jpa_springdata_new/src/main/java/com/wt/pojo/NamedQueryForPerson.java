package com.wt.pojo;

import org.hibernate.annotations.NamedQuery;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:19 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
//这个注解写在这里也没错,不过建议写在实体类上面,也可以使用xml文件，见classpath:META-INF/orm.xml
//@NamedQuery(name = "Person.findByNameNameQuery", query = "select u from Person u where u.name = ?1")
public class NamedQueryForPerson {
}
