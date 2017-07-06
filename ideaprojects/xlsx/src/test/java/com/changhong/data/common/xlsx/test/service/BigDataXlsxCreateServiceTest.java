/**
 *
 */
package com.changhong.data.common.xlsx.test.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.changhong.data.common.xlsx.datasource.OutputDataSource;
import com.changhong.data.common.xlsx.test.domain.MultiColUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.changhong.data.common.xlsx.adapter.OutputCellAdapter;
import com.changhong.data.common.xlsx.adapter.OutputCellAdapterSet;
import com.changhong.data.common.xlsx.adapter.StringToStringCellAdapter;
import com.changhong.data.common.xlsx.adapter.builder.XlsHeaderBuilder;
import com.changhong.data.common.xlsx.service.api.XlsxCreateService;
import com.changhong.data.common.xlsx.test.annotation.XlsxTest;

/**
 * @author QiYao yao.qi@changhong.com

 * @version 1.0.0 2014年10月10日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@XlsxTest
public class BigDataXlsxCreateServiceTest {

    @Autowired
    private XlsxCreateService xlsxCreateService;

    private File file2 = new File("./Target/BigDataXlsxCreateServiceTest_f1.xlsx");
    private File file3 = new File("./Target/BigDataXlsxCreateServiceTest_f2.xlsx");

    private Method m1;
    private Method m2;
    private OutputDataSource data = null;
    private List<OutputCellAdapter> headers = XlsHeaderBuilder.build("地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名","地址", "姓名");
    private List<OutputCellAdapter> adapters = new ArrayList<OutputCellAdapter>();
    private OutputCellAdapterSet ocas;



    private long start;//开始时间
    private long end;//结束时间
    private long size=50000;//测试数据量



    /**
     * 对裸数据执行生成xlsx测试
     *
     * @throws IOException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    @Test
    public void testCreateByRowData() throws IOException, NoSuchMethodException {
        start=System.currentTimeMillis();//开始计时
        System.out.println("未使用注解，开始生成"+size+"条数据的的excel，当前时间戳"+start);

        data=new RepeatDataSources(size);
        m1 = MultiColUser.class.getMethod("getAddress");
        m2 = MultiColUser.class.getMethod("getName");

        for (int i=0;i<10;i++){
            adapters.add(new StringToStringCellAdapter(m1));
            adapters.add(new StringToStringCellAdapter(m2));
        }
        ocas = new OutputCellAdapterSet("用户信息", headers, adapters);
        ocas.setSize(500001);


        FileOutputStream fos2 = new FileOutputStream(file2);
        this.xlsxCreateService.create(fos2, data, ocas);
        end=System.currentTimeMillis();
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
    }

    /**
     * 测试，通过注解进行生成
     *
     * @throws IOException
     */
    @Test
    public void testCraeteByAnnotation() throws IOException {
        start=System.currentTimeMillis();//开始计时
        System.out.println("使用注解，开始生成"+size+"条数据的的excel，当前时间戳"+start);
        data=new RepeatDataSources(size);
        FileOutputStream fos = new FileOutputStream(file3);
        this.xlsxCreateService.create(fos, data,MultiColUser.class);
        end=System.currentTimeMillis();
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
    }

}
