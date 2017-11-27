package com.chaonghong.data.service.impl;

import com.chaonghong.data.entity.Person;
import com.chaonghong.data.mapper.PersonMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.chaonghong.data.Application;
import com.github.pagehelper.PageHelper;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class StudnetServiceTest {
    @Autowired
    StudnetService studentServiceImpl;

    @Autowired
    PersonMapper pm;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void testGetStudents() {
        studentServiceImpl.getStudents();
    }

    @Test
    public void pm() {
        List<Person> allStudents = pm.getAllStudents();
        System.out.println(">>>>>>>>>>>>>>>>>:" + allStudents.size());
        Person person = new Person();
        person.setName("aaabbbbbbbbbb");
        pm.insert(person);
        System.out.println("id=" + person.getId());
    }

    @Test
    public void testGetStudent3s() {
        studentServiceImpl.getStudentById();
    }

    @Test
    public void testGetStudent3ss() {
        studentServiceImpl.getStudentWithSubject();
    }
}
