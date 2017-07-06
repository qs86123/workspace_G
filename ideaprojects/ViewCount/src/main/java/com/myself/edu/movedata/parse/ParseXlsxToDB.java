package com.myself.edu.movedata.parse;

import com.myself.edu.movedata.parse.bean.UsrCashMonth;
import com.myself.edu.movedata.utils.MysqlUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:20 2017/6/28
 * @Email:tao8.wang@changhong.com
 */
public class ParseXlsxToDB {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream(new File("G:\\Program Files\\Tencent\\QQdata\\386427665\\FileRecv\\党费7.4.xls"));
        ParseWorker pw = new XlsxParserWorker();
        pw.setInputStream(is);
        pw.parse();
        List<UsrCashMonth> userCashMonths = pw.getUserCashMonths();
        PreparedStatement ps = null;
        List<String> sqls = new ArrayList<>();
        for (UsrCashMonth u : userCashMonths) {
            System.out.println(u);
            sqls.add(u.toString());
        }
        MysqlUtils.batchSql(sqls, "172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
    }
}
