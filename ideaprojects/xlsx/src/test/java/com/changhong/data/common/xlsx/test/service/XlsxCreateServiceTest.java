/**
 * 
 */
package com.changhong.data.common.xlsx.test.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
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
import com.changhong.data.common.xlsx.test.domain.UserInfoTestDomain;

/**
 * @author QiYao yao.qi@changhong.com

 * @version 1.0.0 2014年10月10日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@XlsxTest
public class XlsxCreateServiceTest {

	@Autowired
	private XlsxCreateService xlsxCreateService;

	private File file1 = new File("./Target/file1.xlsx");
	private File file2 = new File("./Target/file2.xlsx");
	private File file3 = new File("./Target/file3.xlsx");

	private Method m1;
	private Method m2;
	private List<Object> data = new ArrayList<Object>();
	private List<OutputCellAdapter> headers = XlsHeaderBuilder.build("地址", "姓名");
	private List<OutputCellAdapter> adapters = new ArrayList<OutputCellAdapter>();
	private OutputCellAdapterSet ocas;

    private long start;//开始时间
    private long end;//结束时间
    private long size=50000;//测试数据量

	/**
	 * 准备数据
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Before
	public void prepare() throws NoSuchMethodException, SecurityException {

		for (int i = 0; i < size; i++) {
			data.add(new UserInfoTestDomain("这个是地址" + i, "name" + i, new Date(), i, i * 1.1));
		}

		m1 = UserInfoTestDomain.class.getMethod("getAddress");
		m2 = UserInfoTestDomain.class.getMethod("getName");

		adapters.add(new StringToStringCellAdapter(m1));
		adapters.add(new StringToStringCellAdapter(m2));
		ocas = new OutputCellAdapterSet("用户信息", headers, adapters);
	}

	/**
	 * 对裸数据执行生成xls测试
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testCreateByRowData() throws IOException {
        start=System.currentTimeMillis();//开始计时
        System.out.println("未使用注解，开始生成"+size+"条数据的的excel，当前时间戳"+start);
		FileOutputStream fos = new FileOutputStream(file1);
		this.xlsxCreateService.create(fos, data, ocas);

		ocas.setSize(5000);
		FileOutputStream fos2 = new FileOutputStream(file2);
		this.xlsxCreateService.create(fos2, data, ocas);
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
		FileOutputStream fos = new FileOutputStream(file3);
		this.xlsxCreateService.create(fos, data);
        System.out.println("生成excel文件结束，当前时间戳"+end);
        System.out.println("共耗时："+(end-start)+"ms");
	}

	@After
	public void testClean() {
//		file1.delete();
//		file2.delete();
//		file3.delete();// renameTo(new File("./a"));
	}
}
