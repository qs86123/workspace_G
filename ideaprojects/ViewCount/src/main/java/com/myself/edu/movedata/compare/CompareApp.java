package com.myself.edu.movedata.compare;

import com.myself.edu.movedata.compare.bean.Account;
import com.myself.edu.movedata.compare.bean.Cash;
import com.myself.edu.movedata.compare.dao.MysqlUtils;
import com.myself.edu.movedata.compare.util.CHAppAllPaid;
import com.myself.edu.movedata.compare.util.DataCompare;
import com.myself.edu.movedata.compare.util.Login;
import com.myself.edu.utils.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2017年6月28日
 * @author wanglei
 */
public class CompareApp {

    public static void main(String[] args)throws Exception {
        //查出账户列表
        List<Account> accounts = new ArrayList<>();
        Connection connection = MysqlUtils.getConnection("172.17.123.193", "8096", "momx_dev", "momx_dev", "momx_dev20161019");
        String accountSql = "select * from movedata_dzb";
        accounts = selectAccounts(accountSql,connection);
//        Account accounta = new Account();
//        accounta.setUsername("CH04");
//        accounta.setPassword("123456");
//        accounta.setZzjg("制造与供应链管理党支部");
//        accounts.add(accounta);
        for(Account account : accounts){
            Logger.INS.info(account.getUsername()+"，"+account.getZzjg());
            String jsessionid = "JSESSIONID=" + Login.login(account);
            Logger.INS.info("登陆成功");
            List<Cash> cashs = CHAppAllPaid.getCashList(jsessionid, account);
            if(cashs.isEmpty()){
                Logger.INS.info("该党支部没有缴费记录");
            }
            for(Cash cash : cashs){
                String sql = "select a.* from user_cash_month a where a.username='" + cash.getUsername() + "' and zzjg='"+account.getZzjg()+"'";
                Cash cashExcel = select(sql, connection);
                if(cashExcel == null){
                    Logger.INS.info("没有"+cash.getUsername()+"的数据");
                }
                else {
                    if(!DataCompare.compare(cash, cashExcel)){
                        Logger.INS.info(cash.getUsername()+"所属支部"+account.getZzjg()+"错误");
                    }
                    else{
                        Logger.INS.info(cash.getUsername()+"所属支部"+account.getZzjg()+"缴纳成功");
                    }
                }
                Logger.INS.info("-----------------华丽的党员分界线------------------");
            }
            Logger.INS.info("-----------------华丽的党支部分界线------------------");
        }

//        Account account = new Account();
//        account.setUsername("DZB10");
//        account.setPassword("123456");
//        account.setZzjg("股份公司财务部党支部");


    }

    /**
     * 查询党费缴纳记录
     * */
    public static Cash select(String sql, Connection connection)throws Exception {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Cash cash = null;
            while (rs.next()) {
                cash = new Cash();
                cash.setUsername(rs.getString("username"));
                cash.setZzjg(rs.getString("zzjg"));
                cash.setYear(rs.getString("year"));
                cash.setMonth1(rs.getString("month" + 1));
                cash.setMonth2(rs.getString("month" + 2));
                cash.setMonth3(rs.getString("month" + 3));
                cash.setMonth4(rs.getString("month" + 4));
                cash.setMonth5(rs.getString("month" + 5));
                cash.setMonth6(rs.getString("month" + 6));
                cash.setMonth7(rs.getString("month" + 7));
                cash.setMonth8(rs.getString("month" + 8));
                cash.setMonth9(rs.getString("month" + 9));
                cash.setMonth10(rs.getString("month" + 10));
                cash.setMonth11(rs.getString("month" + 11));
                cash.setMonth12(rs.getString("month" + 12));
            }
            return cash;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 查询管理账户列表
     * */
    public static List<Account> selectAccounts(String sql, Connection connection)throws Exception{
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();;
            while (rs.next()) {
                Account account = new Account();
                account.setZzjg(rs.getString("site_name"));
                account.setUsername(rs.getString("username"));
                account.setPassword("123456");
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
