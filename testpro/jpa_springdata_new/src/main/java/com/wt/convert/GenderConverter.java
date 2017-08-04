package com.wt.convert;

import com.wt.pojo.Contact;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:09 2017/8/4
 * @Email:tao8.wang@changhong.com
 */
@Converter
public class GenderConverter
        implements AttributeConverter<Contact.Gender, Character> {

    public Character convertToDatabaseColumn(Contact.Gender value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    public Contact.Gender convertToEntityAttribute(Character value) {
        if (value == null) {
            return null;
        }

        return Contact.Gender.fromCode(value);
    }
}
