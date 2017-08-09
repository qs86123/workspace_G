package com.wt.apptest;

import com.wt.pojo.Address;
import com.wt.pojo.EmbeddableAddress;
import com.wt.pojo.EmbeddablePerson;
import com.wt.repositories.EmbeddablePersonRepository;
import org.junit.Test;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:26 2017/8/8
 * @Email:tao8.wang@changhong.com
 */
public class EmbeddablePersonTest extends SupperTest {

    private EmbeddablePersonRepository epr = context.getBean(EmbeddablePersonRepository.class);

    @Test
    public void save() {
        EmbeddablePerson ep = new EmbeddablePerson();
        ep.setName("name");
        ep.setHomeAddress(new EmbeddableAddress("home_line1", "home_line2", new EmbeddableAddress.ZipCode("home_postalCode", "home_plus")));
        ep.setMailingAddress(new EmbeddableAddress("mailing_line1", "mailing_line2", new EmbeddableAddress.ZipCode("mailing_postalCode", "mailing_plus")));
        ep.setWorkAddress(new EmbeddableAddress("work_line1", "work_line2", new EmbeddableAddress.ZipCode("work_postalCode", "work_plus")));
        epr.save(ep);
    }

    @Test
    public void find() {
        EmbeddablePerson ep = epr.findByHomeAddressZipCodePostalCode("home_postalCode");
        System.out.println(ep.getName());
    }
}
