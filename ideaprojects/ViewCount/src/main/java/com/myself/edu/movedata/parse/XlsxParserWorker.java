package com.myself.edu.movedata.parse;

import com.myself.edu.movedata.parse.bean.UsrCashMonth;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

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
//        for (int i = 0; i < headRow.getLastCellNum(); i++) {
//            head[i] = headRow.getCell(i).getStringCellValue();
//        }
        UsrCashMonth usrCashMonth = null;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            usrCashMonth = new UsrCashMonth();
            XSSFRow bodyRow = sheet.getRow(i);
            usrCashMonth.setUserNo(getCellVaule(bodyRow, 0));
            usrCashMonth.setName(getCellVaule(bodyRow, 1));
            usrCashMonth.setZzjg(getCellVaule(bodyRow, 2));
            usrCashMonth.setYear(getCellVaule(bodyRow, 3));
            String[] str = new String[12];
            int j = 4;
            for (j = 4; j < 16; j++)
                str[j - 4] = getCellVaule(bodyRow, j);
            usrCashMonth.setCashs(str);
            usrCashMonth.setCashAll(getCellVaule(bodyRow, j));
            userCashMonths.add(usrCashMonth);
        }
    }

    private String getCellVaule(XSSFRow bodyRow, int j) {
        XSSFCell xssfCell = bodyRow.getCell(j);
        xssfCell.setCellType(CellType.STRING);
        String s = xssfCell.getStringCellValue();
        s = s == null ? "0" : s.trim().equals("") ? "0" : s;
        return s;
    }

}
