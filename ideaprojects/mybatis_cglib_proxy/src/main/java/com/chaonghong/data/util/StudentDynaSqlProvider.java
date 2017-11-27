package com.chaonghong.data.util;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class StudentDynaSqlProvider
{
    public String getStudentById(final Map<String, Object> map)
    {
        String sql = new SQL()
        {
            {
                SELECT("*");
                FROM("student");
                if (map.containsKey("id"))
                {
                    WHERE("id = #{id}");
                }
                else
                {
                    WHERE("id = 2");
                }

            }
        }.toString();
        System.out.println(sql);
        return sql;
    }
}
