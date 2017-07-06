package com.changhong.data.common.xlsx.test.service;

import com.changhong.data.common.xlsx.adapter.*;
import com.changhong.data.common.xlsx.adapter.builder.XlsHeaderBuilder;
import com.changhong.data.common.xlsx.datasource.OutputDataSource;
import com.changhong.data.common.xlsx.service.impl.XlsxCreateServiceImpl;
import com.changhong.data.common.xlsx.test.annotation.XlsxTest;
import com.changhong.data.common.xlsx.test.domain.FunctionDomin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月18日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@XlsxTest
public class FunctionTest {
    @Autowired
    XlsxCreateServiceImpl xlsxCreateService;

    @Test
    public void test1() throws NoSuchMethodException, FileNotFoundException {
        long start,end;
        int size=50000;
        start=System.currentTimeMillis();
        System.out.println("未使用注解，开始生成"+size+"条数据的的excel，当前时间戳"+start);

        List<Object> data=new ArrayList<>(size);
        for(int i=0;i<size;i++)
        {
            FunctionDomin functionDomin=new FunctionDomin();
            functionDomin.setABoolean(true);
            functionDomin.setADouble(i * 1.1);
            functionDomin.setAnInt(i);
            functionDomin.setDate(new Date(i*1000000l));
            functionDomin.setString("数据"+(i+1));
            data.add(functionDomin);
        }

        List<OutputCellAdapter> headers = XlsHeaderBuilder.build(
                "日期数据", "字符串数据","整数数据","浮点数数据","布尔数据");
        List<OutputCellAdapter> adapters = new ArrayList<OutputCellAdapter>();
        OutputCellAdapterSet ocas=null;
        adapters.add(new DateCellAdapter(FunctionDomin.class.getMethod("getDate")));
        adapters.add(new StringToStringCellAdapter(FunctionDomin.class.getMethod("getString")));
        adapters.add(new NumberCellAdapter(FunctionDomin.class.getMethod("getAnInt")));
        adapters.add(new NumberCellAdapter(FunctionDomin.class.getMethod("getADouble")));
        adapters.add(new BooleanToStringCellAdapter(FunctionDomin.class.getMethod("getABoolean")));
        ocas = new OutputCellAdapterSet("测试数据", headers, adapters);

        xlsxCreateService.create(new FileOutputStream("./function1.xlsx"),data,ocas);

        end=System.currentTimeMillis();
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
    }

    @Test
    public void test2() throws NoSuchMethodException, FileNotFoundException {
        long start,end;
        int size=50000;
        start=System.currentTimeMillis();
        System.out.println("使用注解，开始生成"+size+"条数据的的excel，当前时间戳"+start);

        List<Object> data=new ArrayList<>(size);
        for(int i=0;i<size;i++)
        {
            FunctionDomin functionDomin=new FunctionDomin();
            functionDomin.setABoolean(true);
            functionDomin.setADouble(i * 1.1);
            functionDomin.setAnInt(i);
            functionDomin.setDate(new Date(i*1000000l));
            functionDomin.setString("数据"+(i+1));
            data.add(functionDomin);
        }



        xlsxCreateService.create(new FileOutputStream("./function2.xlsx"),data);

        end=System.currentTimeMillis();
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
    }



    @Test
    public void test3() throws NoSuchMethodException, FileNotFoundException {
        long start,end;
        int size=500000;
        start=System.currentTimeMillis();
        System.out.println("未使用注解，使用自定义数据源，开始生成"+size+"条数据的的excel，当前时间戳"+start);


        List<OutputCellAdapter> headers = XlsHeaderBuilder.build(
                "日期数据", "字符串数据","整数数据","浮点数数据","布尔数据");
        List<OutputCellAdapter> adapters = new ArrayList<OutputCellAdapter>();
        OutputCellAdapterSet ocas=null;
        adapters.add(new DateCellAdapter(FunctionDomin.class.getMethod("getDate")));
        adapters.add(new StringToStringCellAdapter(FunctionDomin.class.getMethod("getString")));
        adapters.add(new NumberCellAdapter(FunctionDomin.class.getMethod("getAnInt")));
        adapters.add(new NumberCellAdapter(FunctionDomin.class.getMethod("getADouble")));
        adapters.add(new BooleanToStringCellAdapter(FunctionDomin.class.getMethod("getABoolean")));
        ocas = new OutputCellAdapterSet("测试数据", headers, adapters);

        OutputDataSource outputDataSource=new FunctionDataSources(size);

        xlsxCreateService.create(new FileOutputStream("./function3.xlsx"),outputDataSource,ocas);

        end=System.currentTimeMillis();
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
    }


    @Test
    public void test4() throws NoSuchMethodException, FileNotFoundException {
        long start,end;
        int size=500000;
        start=System.currentTimeMillis();
        System.out.println("使用注解，使用自定义数据源，开始生成"+size+"条数据的的excel，当前时间戳"+start);

        OutputDataSource outputDataSource=new FunctionDataSources(size);

        xlsxCreateService.create(new FileOutputStream("./function4.xlsx"),outputDataSource,FunctionDomin.class);

        end=System.currentTimeMillis();
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
    }
}
