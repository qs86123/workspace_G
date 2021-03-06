package com.wt.apptest;

import com.wt.pojo.Person;
import com.wt.repositories.PersonNoAddresses;
import com.wt.repositories.PersonRepository;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

public class PersonTest extends SupperTest {

    private PersonRepository p = null;

    {
        p = context.getBean(PersonRepository.class);
    }

    /**
     * 保存
     */
    @Test
    public void save() {
//        for (int i = 0; i < 3; i++)
//            p.save(new Person("wangtao", "23", "男"));
        List<Person> persons = p.findByName("wangtao");
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    @Test
    public void delete() {
        Long count = p.deleteByName("wangtao");
        System.out.println(count);
        List<Person> ps = p.removeByName("wangtao");
        System.out.println(ps.size());
        for (Person p : ps) {
            System.out.println(p);
        }
    }

    @Test
    public void deleteByNameQueryAnnontation() {
        for (int i = 0; i < 3; i++)
            p.save(new Person("aaa", "23", "男"));
        p.deleteByNameQueryAnnotation("aaa");
        //////////////////
        p.save(new Person("a", "23", "男"));
        p.save(new Person("b", "23", "男"));
        p.save(new Person("c", "23", "男"));
        p.save(new Person("a", "23", "男"));
        p.save(new Person("b", "23", "男"));
        p.save(new Person("c", "23", "男"));
        p.deleteByNameIn(Arrays.asList("a", "b", "c"));
        //////////////////////////////
        p.save(new Person("a", "23", "男"));
        p.save(new Person("b", "23", "男"));
        p.save(new Person("c", "23", "男"));
        p.save(new Person("a", "23", "男"));
        p.save(new Person("b", "23", "男"));
        p.save(new Person("c", "23", "男"));
        p.deleteByNameIn2(new String[]{"a", "b", "c"});
        p.save(new Person("a", "23", "男"));
        p.save(new Person("b", "23", "男"));
        p.save(new Person("c", "23", "男"));
        p.save(new Person("a", "23", "男"));
        p.save(new Person("b", "23", "男"));
        p.save(new Person("c", "23", "男"));
        p.deleteByNameIn(new String[]{"a", "b", "c"});
    }

    @Test
    public void findByNameNameQuery() {
        List<Person> ps = p.findByNameNameQuery("wangtao");
        for (Person p : ps) {
            System.out.println(p);
        }
    }

    @Test
    public void findById() {
        Person ps = p.findById("1");
        System.out.println(ps);
    }

    @Test
    public void findByAge() {
        List<PersonNoAddresses> nas = p.findByAge("23");
        for (PersonNoAddresses na : nas) {
            System.out.println(na.getPersonName() + ":" + na.getAge());
            System.out.println(na.getNameAndSex());
            System.out.println(na.getAddressId());
            System.out.println(na.getPassword() + "");
        }
    }

    @Test
    public void findDistinctNameByAge() {
        List<Person> nas = p.findNameDistinctNameByAge("23");
        for (Person na : nas) {
            System.out.println(na.getName() + ":" + na.getAge());
            System.out.println(na.getSex());
        }
    }

    @Test
    public void findByAddressAddrName() {
        Person pp = p.findByAddressAddrName("addrNameValue");
        System.out.println(pp);
    }


//     不通
//    @Test
//    public void findByNameNativeQuery() {
//        Page<Person> page = p.findByName("wangtao", new PageRequest(0, 3));
//        List<Person> ps = page.getContent();
//        for (Person p : ps) {
//            System.out.println(p);
//        }
//    }
}
