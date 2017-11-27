package com.chaonghong.data.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Subject implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int id;
    private String subjectName;
    private Set<Student> students = new HashSet<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
        this.subjectName = subjectName;
    }

    public Set<Student> getStudents()
    {
        return students;
    }

    public void setStudents(Set<Student> students)
    {
        this.students = students;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

}
