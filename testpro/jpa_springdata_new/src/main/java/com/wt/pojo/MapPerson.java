package com.wt.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: wangtao
 * @Date:8:58 2017/8/11
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "map_person")
public class MapPerson extends AbstractMappedType {

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "map_phone_register")
    @Column(name = "since")
    @MapKeyJoinColumn(name = "phone_id", referencedColumnName = "id")
    //map还有其他注解，感觉不是很有用，没有demo，可以看官方文档2.8.8
    private Map<MapPhone, Date> phoneRegister = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<MapPhone, Date> getPhoneRegister() {
        return phoneRegister;
    }

    public void setPhoneRegister(Map<MapPhone, Date> phoneRegister) {
        this.phoneRegister = phoneRegister;
    }

    @Embeddable
    public static class MapPhone {

        public enum PhoneType {
            LAND_LINE,
            MOBILE
        }

        private PhoneType type;

        @Column(name = "`number`")
        private String number;

        public MapPhone() {
        }

        public MapPhone(PhoneType type, String number) {
            this.type = type;
            this.number = number;
        }

        public PhoneType getType() {
            return type;
        }

        public void setType(PhoneType type) {
            this.type = type;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "MapPhone{" +
                    "type=" + type +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

}
