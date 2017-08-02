package com.wt.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description
 * @Author: wangtao
 * @Date:11:04 2017/8/2
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "address")
public class Address extends AbstractMappedType {

    @Column(name = "addr_name")
    private String addrName;

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addrName='" + addrName + '\'' +
                '}';
    }
}
