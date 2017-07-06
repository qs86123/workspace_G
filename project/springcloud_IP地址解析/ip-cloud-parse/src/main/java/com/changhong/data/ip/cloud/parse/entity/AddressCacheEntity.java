package com.changhong.data.ip.cloud.parse.entity;

import java.io.Serializable;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月26日
 */
public class AddressCacheEntity implements Serializable{
    private static final long serialVersionUID = -4657789107349134218L;

    public AddressCacheEntity()
    {}
    public AddressCacheEntity(AddressEntity addressEntity)
    {
        this.setAddressEntity(addressEntity);
        this.setTimes(System.currentTimeMillis());
    }


    private long times;
    private AddressEntity addressEntity;

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }
}
