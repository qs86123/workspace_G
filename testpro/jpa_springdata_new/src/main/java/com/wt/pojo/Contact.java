package com.wt.pojo;

import com.wt.convert.GenderConverter;

import javax.persistence.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:08 2017/8/4
 * @Email:tao8.wang@changhong.com
 */
@Entity
@Table(name = "contact")
public class Contact extends AbstractMappedType {

    private Name name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phone_type_ordinal")
    private PhoneType phoneTypeOrdinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type_string")
    private PhoneType phoneTypeString;

    //使用@conver注解就不要再使用@Enumerated注解了
    @Convert(converter = GenderConverter.class)
    @Column(name = "gender_ordinal")
    private Gender genderOrdinal;

    @Convert(converter = GenderConverter.class)
    @Column(name = "gender_string")
    private Gender genderString;

    public enum PhoneType {
        LAND_LINE,
        PHONE;
    }

    public enum Gender {
        MALE('M'),
        FEMALE('F');
        private final char code;

        Gender(char code) {
            this.code = code;
        }

        public static Gender fromCode(char code) {
            if (code == 'M' || code == 'm') {
                return MALE;
            }
            if (code == 'F' || code == 'f') {
                return FEMALE;
            }
            throw new UnsupportedOperationException(
                    "The code " + code + " is not supported!"
            );
        }

        public char getCode() {
            return code;
        }
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public PhoneType getPhoneTypeOrdinal() {
        return phoneTypeOrdinal;
    }

    public void setPhoneTypeOrdinal(PhoneType phoneTypeOrdinal) {
        this.phoneTypeOrdinal = phoneTypeOrdinal;
    }

    public PhoneType getPhoneTypeString() {
        return phoneTypeString;
    }

    public void setPhoneTypeString(PhoneType phoneTypeString) {
        this.phoneTypeString = phoneTypeString;
    }

    public Gender getGenderOrdinal() {
        return genderOrdinal;
    }

    public void setGenderOrdinal(Gender genderOrdinal) {
        this.genderOrdinal = genderOrdinal;
    }

    public Gender getGenderString() {
        return genderString;
    }

    public void setGenderString(Gender genderString) {
        this.genderString = genderString;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", phoneTypeOrdinal=" + phoneTypeOrdinal +
                ", phoneTypeString=" + phoneTypeString +
                ", genderOrdinal=" + genderOrdinal +
                ", genderString=" + genderString +
                '}';
    }
}
