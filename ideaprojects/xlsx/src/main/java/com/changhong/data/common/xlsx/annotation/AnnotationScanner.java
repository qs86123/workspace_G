package com.changhong.data.common.xlsx.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.stereotype.Service;

import com.changhong.data.common.exception.ReflectException;
import com.changhong.data.common.xlsx.adapter.OutputCellAdapter;
import com.changhong.data.common.xlsx.adapter.OutputCellAdapterSet;

/**
 * 注解扫描器
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 */
@Service
public class AnnotationScanner {

	/**
	 * 根据cname获取class文件，扫描class的注解生成适配器组
	 * @param cname
	 * @return 适配器组
	 */
	public OutputCellAdapterSet scanClass(String cname){
		try {
			Class<?> t = Class.forName(cname);
			Xlsx x = t.getAnnotation(Xlsx.class);

			if (null == x) {
				return null;
			}

			String name = x.value();
			int size = x.size();

			// 存放所有需要转换的Method
			AnnotationPairTrasfer apt = new AnnotationPairTrasfer();

			//注解于字段
			Field[] fs = t.getDeclaredFields();
			for (Field f : fs) {
				XlsxColumn xc = f.getAnnotation(XlsxColumn.class);
				if(null != xc){
					StringBuffer fName = new StringBuffer(f.getName());
					fName.setCharAt(0, Character.toUpperCase(fName.charAt(0)));
					String mName = "get" + fName.toString();
					Method m = t.getDeclaredMethod(mName, new Class[]{});
					AnnotationPair ap = new AnnotationPair(m, xc);
					apt.add(ap);
				}
			}
			
			//注解于方法
			Method[] ms = t.getDeclaredMethods();
			for (Method m : ms) {
				XlsxColumn xc = m.getAnnotation(XlsxColumn.class);
				if (null != xc) {
					AnnotationPair ap = new AnnotationPair(m, xc);
					apt.add(ap);
				}
			}
			
			List<OutputCellAdapter> headers = apt.getHeaders();
			List<OutputCellAdapter> adapters = apt.getAdapters();
			return new OutputCellAdapterSet(name, size, headers, adapters);
		} catch (Exception e) {
			ReflectException re = new ReflectException(e);
			throw re;
		}
	}

	
}
