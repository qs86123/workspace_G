package com.chaonghong.data.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaonghong.data.entity.Subject;
import com.chaonghong.data.mapper.SubjectMapper;

@Service
public class SubjectService
{
    @Autowired
    SubjectMapper subjectMapper;

    public void getSubject()
    {
        Set<Subject> sub = subjectMapper.findSubjectById(2);

        for (Subject subject : sub)
        {

            System.out.println(subject.getSubjectName());
        }
    }
}
