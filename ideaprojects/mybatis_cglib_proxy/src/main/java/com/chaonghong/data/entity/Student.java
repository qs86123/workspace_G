package com.chaonghong.data.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;
    private String studentName;
    private int sub_id;
    private Set<Subject> subjects = new HashSet<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public Set<Subject> getSubjects()
    {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects)
    {
        this.subjects = subjects;
    }

    public int getSub_id()
    {
        return sub_id;
    }

    public void setSub_id(int sub_id)
    {
        this.sub_id = sub_id;
    }

}
