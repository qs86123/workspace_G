package com.changhong.data.ip.cloud.parse.entity;

import java.io.Serializable;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月25日
 */
public class AddressEntity implements Serializable{
    private static final long serialVersionUID = 5229488125112403829L;
    private String _pro;
    private String _country;
    private String _city;

    public String get_pro() {
        return _pro;
    }

    public void set_pro(String _pro) {
        this._pro = _pro;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }
}
