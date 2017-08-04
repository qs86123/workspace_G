package com.wt.apptest;

import com.wt.pojo.Contact;
import com.wt.pojo.Name;
import com.wt.repositories.ContactRepository;
import org.junit.Test;

import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:14:13 2017/8/4
 * @Email:tao8.wang@changhong.com
 */
public class ContactTest extends SupperTest {

    private ContactRepository cr;

    {
        cr = context.getBean(ContactRepository.class);
    }

    @Test
    public void save() {
        Contact c = new Contact();
        c.setId("aaa");
        c.setGenderOrdinal(Contact.Gender.MALE);
        c.setGenderString(Contact.Gender.FEMALE);
        c.setPhoneTypeOrdinal(Contact.PhoneType.LAND_LINE);
        c.setPhoneTypeString(Contact.PhoneType.PHONE);
        Name name = new Name();
        name.setFirst("f");
        name.setLast("l");
        c.setName(name);
        cr.save(c);
    }

    @Test
    public void findById() {
        Contact c = cr.findById("1");
        System.out.println(c);
    }

    @Test
    public void findByNameFirst() {
        List<Contact> cs = cr.findByNameFirst("w");
        for (Contact c : cs)
            System.out.println(c);
    }

}
