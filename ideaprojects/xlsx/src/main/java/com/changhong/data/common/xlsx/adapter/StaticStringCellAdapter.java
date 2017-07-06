package com.changhong.data.common.xlsx.adapter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 * 静态String 适配器
 * @author QiYao yao.qi@changhong.com
 * @version 1.0.0 2014年10月9日
 * @see com.changhong.data.common.xlsx.adapter.builder.XlsHeaderBuilder build(String... strings)
 */
public class StaticStringCellAdapter
	extends BaseOutputCellAdapter{

	private String s = null;
	
	public StaticStringCellAdapter(String s){
		this.s = s;
	}
	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.domain.adapter.OutputCellAdapter#getCellType()
	 */
	@Override
	public CellType getCellType() {
		return CellType.STRING;
	}

	/* (non-Javadoc)
	 * @see com.changhong.data.common.xlsx.domain.adapter.OutputCellAdapter#fillRow(org.apache.poi.ss.usermodel.Cell, java.lang.Object)
	 */
	@Override
	public void fillRow(Cell cell, Object obj) {
		cell.setCellValue(s);
	}


}
