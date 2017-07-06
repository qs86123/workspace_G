package com.wt.xlsxparse.demo.parse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:34 2017/6/16
 * @Email:tao8.wang@changhong.com
 */
public class XlsxParserWorker extends ParseWorker {

    public XlsxParserWorker() {
        this.nextParseWorker = null;
    }

    public XlsxParserWorker(ParseWorker nextParseWorker) {
        this.nextParseWorker = nextParseWorker;
    }

    public void parse() throws Exception {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        XSSFRow headRow = sheet.getRow(0);
        head = new String[headRow.getLastCellNum()];
        for (int i = 0; i < headRow.getLastCellNum(); i++) {
            head[i] = headRow.getCell(i).getStringCellValue();
        }
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow bodyRow = sheet.getRow(i);
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < bodyRow.getLastCellNum(); j++) {
                XSSFCell xssfCell = bodyRow.getCell(j);
                xssfCell.setCellType(CellType.STRING);
                sb.append(xssfCell.getStringCellValue() + "   ");
            }
            body.put(i, sb.toString());
        }
    }

}
