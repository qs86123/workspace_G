package com.changhong.data.common.xlsx.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.changhong.data.common.xlsx.adapter.OutputCellAdapter;
import com.changhong.data.common.xlsx.adapter.OutputCellAdapterSet;
import com.changhong.data.common.xlsx.annotation.AnnotationAdapterMap;
import com.changhong.data.common.xlsx.datasource.OnceDataSource;
import com.changhong.data.common.xlsx.datasource.OutputDataSource;
import com.changhong.data.common.xlsx.service.api.XlsxCreateService;

/**
 * 生成xlsx服务
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2016年9月30日
 */
@Service
public class XlsxCreateServiceImpl implements XlsxCreateService {

	/**
	 * row达到1000之后刷新磁盘，不在放在内存。意味着将不能再从内存读取
	 */
	public static final int DEFAULT_WINDOW_SIZE = 20000;
	/**
	 * 最大的sheet单页数量
	 */
	public static int MAX_SHEET_SIZE = 500001;

	@Autowired
	private AnnotationAdapterMap annotationAdapterMap;


	/**
	 * 单分页数据
	 * 
	 * @param os
	 *            输出流
	 * @param o
	 *            数据源
	 * @param ocas
	 *            输出适配器
	 */
	private void createSingleSource(OutputStream os, OutputDataSource o, OutputCellAdapterSet ocas) {
		List<OutputCellAdapterSet> locas = new ArrayList<>();
		locas.add(ocas);

		List<OutputDataSource> lo = new ArrayList<>();
		lo.add(o);

		int size = ocas.getSize();
		if(size < 1){
			size = MAX_SHEET_SIZE;
		}
		this.createWorkBook(os, lo, locas, size);
	}

	/**
	 * 输出数据到workbook
	 * 
	 * @param os
	 *            输出流
	 * @param lo
	 *            数据来源组
	 * @param locas
	 *            数据适配器组
	 */
	private void createWorkBook(OutputStream os, List<OutputDataSource> lo, List<OutputCellAdapterSet> locas, int maxSize) {
		Workbook xls = new SXSSFWorkbook(DEFAULT_WINDOW_SIZE);
		int loSize = lo.size();
		int locasSize = locas.size();

		// 数据的组合必须和定义的组合数量一致
		if (loSize == locasSize) {
			for (int i = 0; i < loSize; i++) {
				OutputDataSource ods = lo.get(i);
				OutputCellAdapterSet ocas = locas.get(i);
				this.createSheet(xls, ods, ocas, maxSize);
			}
		}
		try {
			xls.write(os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	

	// 输出sheet页
	private void createSheet(Workbook book, OutputDataSource ods, OutputCellAdapterSet ocas, int maxSize) {
		Sheet sheet = this.createSheetAndHeader(book, ocas);
		int sheetSize = sheet.getLastRowNum();

		while(ods.hasNext()){
			List<Object> datas = ods.getData();
			for(Object obj : datas){
				sheetSize++;
				
				if (sheetSize < maxSize) {
					this.createRow(sheet, sheetSize, obj, ocas);
				}else{
					sheet = this.createSheetAndHeader(book, ocas);
					sheetSize = sheet.getLastRowNum() + 1;
					
					this.createRow(sheet, sheetSize, obj, ocas);
				}
			}
		}
		// 单页不能超过最大限制
		
	}

	/**
	 * 创建行
	 * @param sheet
	 * @param rowNum
	 * @param data
	 * @param ocas
	 */
	private void createRow(Sheet sheet, int rowNum, Object data, OutputCellAdapterSet ocas){
		Row row = sheet.createRow(rowNum);
		List<OutputCellAdapter> adapters = ocas.getAdapters();
		for(int i=1; i<=adapters.size();i++){
			Cell cell = row.createCell(i-1);
			adapters.get(i-1).fillRow(cell, data);
		}
	}
	/**
	 * 创建一个带名称的sheet页, 并初始化表头[如果有表头定义的话]
	 * 
	 * @param book
	 * @param ocas
	 * @return
	 */
	private Sheet createSheetAndHeader(Workbook book, OutputCellAdapterSet ocas) {
		String name = ocas.getName();
		int ssize = book.getNumberOfSheets() + 1;
		if (StringUtils.isEmpty(name)) {
			name = "工作表";
		}
		name = name + ssize;
		Sheet sheet = book.createSheet(name);

		// 创建表头
		List<OutputCellAdapter> headers = ocas.getHeaders();

		if (headers != null) {
			Row row = sheet.createRow(0);

			Font headFont = book.createFont();
			headFont.setFontName("宋体");
			headFont.setBold(true);
			headFont.setFontHeightInPoints((short) 14);

			// 表头样式
			CellStyle headStyle = book.createCellStyle();
			headStyle.setAlignment(HorizontalAlignment.CENTER);
			headStyle.setFont(headFont);

			row.setRowStyle(headStyle);

			for (int i = 0; i < headers.size(); i++) {
				OutputCellAdapter oca = headers.get(i);
				Cell cell = row.createCell(i, oca.getCellType());
				oca.fillRow(cell, null);
			}
		}

		return sheet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.changhong.data.common.xlsx.service.api.XlsxCreateService#create(java.
	 * io.OutputStream, java.util.List,
	 * com.changhong.data.common.xlsx.domain.adapter.OutputCellAdapterSet)
	 */
	@Override
	public void create(OutputStream os, List<Object> data, OutputCellAdapterSet ocas) {
		OnceDataSource ods = new OnceDataSource(data);
		this.createSingleSource(os, ods, ocas);
	}

	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.service.api.XlsxCreateService#create(java.io.OutputStream, java.util.List)
	 */
	@Override
	public void create(OutputStream os, List<Object> data) {
		Object o = data.get(0);
		OutputCellAdapterSet adapterSet = annotationAdapterMap.getAdapterSet(o);
		OnceDataSource ods = new OnceDataSource(data);
		this.createSingleSource(os, ods, adapterSet);
	}

    /**
     * 使用自定义的数据源和适配器生成的excel文件，输出到指定输出流
     * @param os
     * @param outputDataSource
     * @param ocas
     */
    @Override
    public void create(OutputStream os,  OutputDataSource outputDataSource, OutputCellAdapterSet ocas) {
        this.createSingleSource(os, outputDataSource, ocas);
    }


    /**
     * 使用自定义的数据源和注解定义的类型生成的excel文件，输出到指定输出流
     * @param os
     * @param outputDataSource
     * @param c
     */
    @Override
    public void create(OutputStream os, OutputDataSource outputDataSource,Class<?> c) {
        OutputCellAdapterSet adapterSet = annotationAdapterMap.getAdapterSet(c);
        this.createSingleSource(os, outputDataSource, adapterSet);
    }

}
