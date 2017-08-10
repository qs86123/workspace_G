package com.wt.pojo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:29 2017/8/10
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "order_phone")
public class OrderPhone extends AbstractMappedType {

    private String type;

    private String number;

    @ManyToOne
    private OrderPerson person;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public OrderPerson getPerson() {
        return person;
    }

    public void setPerson(OrderPerson person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "OrderPhone{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", person=" + person +
                '}';
    }
}
