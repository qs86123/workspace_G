package com.myself.edu.qgdyxx;

import com.alibaba.fastjson.JSONObject;
import com.myself.edu.movedata.utils.MysqlUtils;
import com.myself.edu.qgdyxx.bean.UpdateRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 正式党员录入和申请人录入是同一个接口，两个方法都在这个类当中
 *
 * @Author: wangtao
 * @Date:20:36 2017/11/6
 * @Email:tao8.wang@changhong.com
 */
public class AddBasicInfoStart {

    public static void main(String[] args) throws Exception {
        //正式党员信息录入
//        zhengshi();
        //预备党员信息录入
        yubei();
    }

    /**
     * 添加正式党员信息
     */
    public static void zhengshi() throws Exception {

        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
        String cookieAll = "";
        cookieAll = webcookie + cookie;
//        cookieAll = "mapid=64a0a160;JSESSIONID=CFEC1D758628D88B26E1FA91FD8EC5F3.csdj1;SRV=3533ff4c-eb4e-47be-bef7-17ec40ca86cb;VSG_SESSIONID=172271669;";
        System.out.println(cookieAll);
        String zhengshiSql = "select c.id organid,a.* from update_base_info a " +
                "left join zzjg_base_info c on c.`name`= a.party_name and a.idcard='511681198811073000'";
        int pageSize = 100;
        Connection conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
        String sql = "insert into update_record (`uuid`,`organid`,`name`,`idcard`) values(";
        //查询另一个表update_base_info的数据
        PreparedStatement ps = conn.prepareStatement(zhengshiSql);
        ResultSet rs = ps.executeQuery();
        UpdateRequest ur = null;
        List<NameValuePair> list = new ArrayList<>();
        while (rs.next()) {
            ur = new UpdateRequest();
            String name = rs.getString("name");
            ur.setDyInfo_xm(name);
            ur.setDyInfo_xb(rs.getString("sex"));
            ur.setDyInfo_mz(rs.getString("mz"));
            String idcard = rs.getString("idcard");
            ur.setDyInfo_zjhm(idcard);
            ur.setDyInfo_csrq(rs.getString("birthday"));
            ur.setDyInfo_xl(rs.getString("xl"));
            ur.setDyInfo_dylb(rs.getString("person_type"));
            ur.setDyInfo_rdsj(rs.getString("join_org_date"));
            ur.setDyInfo_zzsj(rs.getString("change_date"));
            ur.setDyInfo_gzgw(rs.getString("work_unit"));
            ur.setDyInfo_lxdh(rs.getString("tel"));
            ur.setDyInfo_qh(rs.getString("phone"));
            ur.setDyInfo_dh(rs.getString("phone"));
            ur.setDyInfo_xjzd(rs.getString("home_address"));
            ur.setDyInfo_lkzgzzrq("");
            ur.setDyInfo_iscontact(rs.getString("is_notconnect"));
            ur.setDyInfo_losttimeStr(rs.getString("not_connect_time"));
            ur.setDyInfo_sfzld(rs.getString("is_flow"));
            ur.setDyInfo_wclx(rs.getString("flow_direction"));
            //基本信息初始化完成
            String organid = rs.getString("organid");
            list.add(new BasicNameValuePair("dyDjxx.uuid", ""));
            list.add(new BasicNameValuePair("changeDate", ""));
            list.add(new BasicNameValuePair("dyInfo.userid", ""));
            list.add(new BasicNameValuePair("dyInfo.organid", organid));
            list.add(new BasicNameValuePair("selectCon", ur.getDyInfo_iscontact()));
            list.add(new BasicNameValuePair("oldZjhm", idcard));
            list.add(new BasicNameValuePair("oldXm", name));
            //补录过程中身份证号码重复的都不会补录数据，重复表示直接设置为0
            list.add(new BasicNameValuePair("dyInfo.idcardmult", "0"));
            list.add(new BasicNameValuePair("dyInfo.lostreason", ""));
            list.add(new BasicNameValuePair("dyInfo.jg", ""));
            list.add(new BasicNameValuePair("dyInfo.sftwj", ""));
            list.add(new BasicNameValuePair("dyInfo.isworkers", ""));
            list.add(new BasicNameValuePair("dyInfo.sortnum", ""));
            list.add(new BasicNameValuePair("dyInfo.djxgsj", ""));
            list.add(new BasicNameValuePair("dyjgByZjhm", ""));
            list.add(new BasicNameValuePair("addFlag", "false"));
            list.add(new BasicNameValuePair("idCardValidity", ""));
            list.add(new BasicNameValuePair("zzsj", ""));
            //党员状态都是正常，1正常，2死亡，3已出党，4停止党籍
            list.add(new BasicNameValuePair("djdyzt", "1"));
            //添加状态为补录，bulu
            list.add(new BasicNameValuePair("addDyType", "bulu"));
            list.add(new BasicNameValuePair("dyInfo.iscontact", ur.getDyInfo_iscontact()));
            list.add(new BasicNameValuePair("isSqrToDy", "2"));
            list.add(new BasicNameValuePair("xb", ""));
            list.add(new BasicNameValuePair("orgId", organid));
            list.add(new BasicNameValuePair("organIdSelf", ""));
            list.add(new BasicNameValuePair("uuid", ""));
            list.add(new BasicNameValuePair("addReason", "13"));
            list.add(new BasicNameValuePair("isSqrToDyAfterSave", ""));
            list.add(new BasicNameValuePair("beginEditFlag", "1"));

            list.addAll(ur.getNameValuePairListForAddZhengshi());

            String isContact = ur.getDyInfo_iscontact();
            //检查身份证信息是否重复
            String checkResult = UpdateCheckUserId.updateCheckUserId(cookieAll, idcard, isContact);
            JSONObject json = JSONObject.parseObject(checkResult);
            if (json.getString("result").equals("0")) {
                //身份证检验通过，开始添加
                String result = AddBasicInfo.addBasicInfo(cookieAll, list);
                JSONObject j = JSONObject.parseObject(result);
                if (j.getString("result").equals("0")) {
                    PreparedStatement p = conn.prepareStatement(sql + "'" + json.getString("dyInfo.userid") + "'," + "'" + json.getString("dyInfo.organid") + "'," + "'" + name + "'," + "'" + idcard + "')");
                    p.executeUpdate();
                } else {
                    //当添加成功时往数据库记录一条信息
                    System.out.println("<" + idcard + ">:" + "补录信息失败：" + result);
                }
            } else {
                //身份证检验不通过，不添加
                System.out.println("<" + idcard + ">:" + checkResult);
            }
        }
    }

    /**
     * 添加预备党员信息
     */
    public static void yubei() throws Exception {
        System.out.println("----------login---VPN");
        String cookie = VpnLogin.loginvpn();
        System.out.println("----------login---WEB");
        String webcookie = WebLogin.loginweb(cookie);
        String cookieAll = "";
        cookieAll = webcookie + cookie;
//        cookieAll = "mapid=64a0a160;JSESSIONID=3643943EF3A59BBDA54685C93100A0CD.csdj1;SRV=079b6108-1995-4124-80bc-4cd63796deba;VSG_SESSIONID=1725844928;";
        System.out.println(cookieAll);
        String yubeiSql = "select c.id organid,a.* from update_base_info a " +
                "left join zzjg_base_info c on c.`name`= a.party_name where a.idcard='422201198912016000'";
        int pageSize = 100;
        Connection conn = MysqlUtils.getConnection("127.0.0.1", "3306", "qgdyxx", "root", "123456");
        String sql = "insert into update_record (`uuid`,`organid`,`name`,`idcard`) values(";
        //查询另一个表update_base_info的数据
        PreparedStatement ps = conn.prepareStatement(yubeiSql);
        ResultSet rs = ps.executeQuery();
        UpdateRequest ur = null;
        List<NameValuePair> list = new ArrayList<>();
        while (rs.next()) {
            ur = new UpdateRequest();
            String name = rs.getString("name");
            ur.setDyInfo_xm(name);
            ur.setDyInfo_xb(rs.getString("sex"));
            ur.setDyInfo_mz(rs.getString("mz"));
            String idcard = rs.getString("idcard");
            ur.setDyInfo_zjhm(idcard);
            ur.setDyInfo_csrq(rs.getString("birthday"));
            ur.setDyInfo_xl(rs.getString("xl"));
            ur.setDyInfo_dylb(rs.getString("person_type"));
            ur.setDyInfo_rdsj(rs.getString("join_org_date"));
            ur.setDyInfo_zzsj(rs.getString("change_date"));
            ur.setDyInfo_gzgw(rs.getString("work_unit"));
            ur.setDyInfo_lxdh(rs.getString("tel"));
            ur.setDyInfo_qh(rs.getString("phone"));
            ur.setDyInfo_dh(rs.getString("phone"));
            ur.setDyInfo_xjzd(rs.getString("home_address"));
            ur.setDyInfo_lkzgzzrq("");
            ur.setDyInfo_iscontact(rs.getString("is_notconnect"));
            ur.setDyInfo_losttimeStr(rs.getString("not_connect_time"));
            ur.setDyInfo_sfzld(rs.getString("is_flow"));
            ur.setDyInfo_wclx(rs.getString("flow_direction"));
            //基本信息初始化完成
            String organid = rs.getString("organid");
            list.add(new BasicNameValuePair("dyDjxx.uuid", ""));
            list.add(new BasicNameValuePair("addReason", ""));
            list.add(new BasicNameValuePair("changeDate", ""));
            list.add(new BasicNameValuePair("dyInfo.userid", ""));
            list.add(new BasicNameValuePair("dyInfo.organid", organid));
            list.add(new BasicNameValuePair("oldZjhm", ""));
            //补录过程中身份证号码重复的都不会补录数据，重复表示直接设置为0
            list.add(new BasicNameValuePair("dyInfo.idcardmult", "0"));
            list.add(new BasicNameValuePair("dyInfo.lostreason", ""));
            list.add(new BasicNameValuePair("dyInfo.jg", ""));
            list.add(new BasicNameValuePair("dyInfo.sftwj", ""));
            list.add(new BasicNameValuePair("dyInfo.isworkers", ""));
            list.add(new BasicNameValuePair("dyInfo.sortnum", ""));
            list.add(new BasicNameValuePair("idCardValidity", ""));
            list.add(new BasicNameValuePair("isSQR", "1"));
            list.add(new BasicNameValuePair("addDyType", "sqrbulu"));

            list.addAll(ur.getNameValuePairListForAddYubei());

            String isContact = ur.getDyInfo_iscontact();
            //检查身份证信息
            String checkResult = UpdateCheckUserId.updateCheckUserId(cookieAll, idcard, isContact);
            JSONObject json = JSONObject.parseObject(checkResult);
            if (json.getString("result").equals("0")) {
                //身份证信息检查通过，开始添加
                String result = AddBasicInfo.addBasicInfo(cookieAll, list);
                JSONObject j = JSONObject.parseObject(result);
                if (j.getString("result").equals("0")) {
                    //当添加成功时往数据库记录一条信息
                    PreparedStatement p = conn.prepareStatement(sql + "'" + json.getString("dyInfo.userid") + "'," + "'" + json.getString("dyInfo.organid") + "'," + "'" + name + "'," + "'" + idcard + "')");
                    p.executeUpdate();
                } else {
                    System.out.println("<" + idcard + ">:" + "补录信息失败：" + result);
                }
            } else {
                //身份证信息检查不通过，不添加
                System.out.println("<" + idcard + ">:" + checkResult);
            }
        }
    }

}
