package com.myself.edu.movedata.parse;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author: wangtao
 * @Date:16:36 2017/11/13
 * @Email:tao8.wang@changhong.com
 */
public class SqlXslxParse extends ParseWorker {

    private String oldFile;

    private String outFile;

    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public String getOldFile() {
        return oldFile;
    }

    public void setOldFile(String oldFile) {
        this.oldFile = oldFile;
    }

    @Override
    public void parse() throws Exception {
        FileOutputStream fos = new FileOutputStream(outFile, true);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        for (int m = 0; m < xssfWorkbook.getNumberOfSheets(); m++) {
            XSSFSheet sheet = xssfWorkbook.getSheetAt(m);
            String sheetName = sheet.getSheetName();
            Set<String> oldFileColumns = getOldFileColumns(sheetName);
            StringBuilder insert = new StringBuilder();
            StringBuilder select = new StringBuilder();
            insert.append("insert into `" + sheetName + "` (");
            select.append("select ");
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                XSSFRow bodyRow = sheet.getRow(i);
                String columnName = getCellVaule(bodyRow, 0);
                if (columnName != null && !oldFileColumns.add(columnName)) {
                    insert.append("`" + sheetName + "_" + columnName + "`,");
                    select.append("`" + columnName + "`,");
                }
            }
            String insertStr = insert.toString();
            insertStr = insertStr.substring(0, insertStr.length() - 1) + ") ";
            String selectStr = select.toString();
            selectStr = selectStr.substring(0, selectStr.length() - 1) + "from `" + sheetName + "`";
            String outStr = "#sheet:" + sheet.getSheetName() + "\n" + insertStr + selectStr + "\n";
            strToFile(outStr, fos);
            System.out.println(outStr);

        }
    }

    private void strToFile(String outStr, FileOutputStream fos) throws Exception {
        InputStream is = new ByteArrayInputStream(outStr.getBytes());
        byte[] b = new byte[4096];
        int len = -1;
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        fos.flush();
        is.close();
    }

    public Set<String> getOldFileColumns(String sheetName) throws IOException {
        Set<String> columns = new HashSet<>();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(oldFile));
        XSSFSheet sheet = xssfWorkbook.getSheet(sheetName);
        XSSFRow headRow = sheet.getRow(0);
        head = new String[headRow.getLastCellNum()];
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow bodyRow = sheet.getRow(i);
            String columnName = getCellVaule(bodyRow, 0);
            if (columnName != null) {
                columns.add(columnName);
            }
        }
        return columns;
    }

    private String getCellVaule(XSSFRow bodyRow, int j) {
        if (bodyRow == null) {
            return null;
        }
        XSSFCell xssfCell = bodyRow.getCell(j);
        if (xssfCell == null) {
            return null;
        }
        xssfCell.setCellType(CellType.STRING);
        String s = xssfCell.getStringCellValue();
        s = s == null ? null : s.trim().equals("") ? null : s;
        return s;
    }

    private String getCellVauleForInsert(XSSFRow bodyRow, Map<String, String> map) {
        XSSFCell xssfCell = bodyRow.getCell(1);
        xssfCell.setCellType(CellType.STRING);
        String s = xssfCell.getStringCellValue();
        s = s == null ? null : s.trim().equals("") ? null : s;
        if (s != null) {
            Set<String> set = map.keySet();
            for (String key : set) {
                if (s.equals(map.get(key))) {
                    return key;
                }
            }
        }
        return s + "";
    }
}
