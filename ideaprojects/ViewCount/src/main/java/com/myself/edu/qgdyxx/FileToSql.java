package com.myself.edu.qgdyxx;

import java.io.*;

/**
 * @Description
 * @Author: wangtao
 * @Date:15:44 2017/11/14
 * @Email:tao8.wang@changhong.com
 */
public class FileToSql {
    /**
     * #A01不能执行，d05表不存在
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:/党建/columnsMap.properties");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = "";
        StringBuilder total = new StringBuilder();
        StringBuilder insert = new StringBuilder();
        StringBuilder select = new StringBuilder();
        String oldTable = "";
        String newTable = "";
        while ((line = br.readLine()) != null) {
            int index = line.indexOf("#");
            if (index != -1) {
                insert.append("\n" + line + "\n");
                int mid = line.indexOf("=");
                oldTable = line.substring(1, mid);
                int end = line.indexOf("-");
                newTable = end == -1 ? line.substring(mid + 1) : line.substring(mid + 1, end);
                insert.append("insert into `" + newTable + "`(");
                select.append("select ");
            } else if (line.trim().equals("")) {
                String insertStr = insert.toString();
                String selectStr = select.toString();
                total.append(insertStr.substring(0, insertStr.length() - 1) + ") ");
                total.append(selectStr.substring(0, selectStr.length() - 1) + " from `" + oldTable + "`");
                total.append(";\n");
                insert = new StringBuilder();
                select = new StringBuilder();
            } else {
                int i = line.indexOf("=");
                String oldColumn = line.substring(0, i);

                select.append(oldColumn.equals("uuid()") ? oldColumn + "," : "`" + oldColumn + "`,");
                insert.append("`" + line.substring(i + 1) + "`,");
            }
        }
        String outStr = total.toString();
        System.out.println(outStr);
        strToFile(outStr, new FileOutputStream("D:/党建/outsql.sql"));
    }

    private static void strToFile(String outStr, FileOutputStream fos) throws Exception {
        InputStream is = new ByteArrayInputStream(outStr.getBytes());
        byte[] b = new byte[4096];
        int len = -1;
        while ((len = is.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        fos.flush();
        is.close();
    }
}
