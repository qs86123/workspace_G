package com.wt.repositories;

import com.wt.pojo.Person;
import com.wt.pojo.PersonAndItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByName(String name);

    @Query("select p from Person p where p.name = ?1")
    Person findqqqq(String name);

    @Query("select new PersonAndItem(p.name,i.price) from Person p, Item i where p.name='wt' and i.name='itemwt' ")
    PersonAndItem findpersonAnditem();

    long countByName(String name);

    long countByNameAndSex(String name, String sex);

    @Query(value = "select p.* from person p where p.name = ?1", nativeQuery = true)
    Person findNativeQuery(String name);

    @Query(value = "select p.* from person p limit ?1,?2", nativeQuery = true)
    List<Person> findNativeQuery(int n, int m);
}
