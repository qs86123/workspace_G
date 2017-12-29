package com.myself.edu.qgdyxx.bean;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description
 * @author: wangtao
 * @date:13:12 2017/12/27
 * @email:tao8.wang@changhong.com
 */
public class UpdateRequest {


//    private String dyInfo_userid;
//    private String dyInfo_organid;
//    private String dyDjxx_uuid;
//    private String changeDate;
//    private String selectCon;
//    private String oldZjhm;
//    private String oldXm;
//    private String dyInfo_idcardmult;
//    private String dyInfo_djxgsj;
//    private String addFlag;
//    private String idCardValidity;
//    private String zzsj;
//    private String djdyzt;

    private String uuid;
    private String addReason;
    private String isSqrToDyAfterSave;
    private String beginEditFlag;
    private String dyInfo_xm;
    private String dyInfo_xb;
    private String dyInfo_mz;
    private String dyInfo_zjhm;
    private String dyInfo_csrq;
    private String dyInfo_xl;
    private String dyInfo_dylb;
    private String dyInfo_rdsj;
    private String dyInfo_zzsj;
    private String dyInfo_gzgw;
    private String dyInfo_lxdh;
    private String dyInfo_qh;
    private String dyInfo_dh;
    private String dyInfo_xjzd;
    private String dyInfo_lkzgzzrq;
    private String dyInfo_iscontact;
    private String dyInfo_losttimeStr;
    private String dyInfo_sfzld;
    private String dyInfo_wclx;

    public UpdateRequest() {
//        dyInfo_userid = "5c00519fb86f4c619e86c2d54bbd087a";
//        dyInfo_organid = "020E36FEB24A458CB4266DA0131AD13E";
//        dyDjxx_uuid = "2c92d39160892055016095a9dbf515da";
//        selectCon = "2";
//        oldZjhm = "510702197507160541";
//        oldXm = "茆海云";
//        dyInfo_idcardmult = "0";
//        dyInfo_djxgsj = "98-5-15 0:00:00.000";
//        addFlag = "false";
//        idCardValidity = "0";
//        zzsj = "1998-05-15";
//        djdyzt = "1";


//        uuid = "5c00519fb86f4c619e86c2d54bbd087a";
//        beginEditFlag = "1";

//        dyInfo_xm = "茆海云";
//        dyInfo_xb = "2";
//        dyInfo_mz = "01";
//        dyInfo_zjhm = "510702197507160541";
//        dyInfo_csrq = "1975-07-16";
//        dyInfo_xl = "21";
//        dyInfo_dylb = "1";
//        dyInfo_rdsj = "1997-05-15";
//        dyInfo_zzsj = "1998-05-15";
//        dyInfo_gzgw = "0214";
//        dyInfo_lxdh = "15808163367";
//        dyInfo_qh = "0816";
//        dyInfo_dh = "2416920";
//        dyInfo_xjzd = "虹色景苑";
//        dyInfo_lkzgzzrq="";
//        dyInfo_iscontact = "2";
//        dyInfo_losttimeStr = "2017-12";
//        dyInfo_sfzld = "0";
//        dyInfo_wclx="";
    }

    public List<NameValuePair> getNameValuePairList() {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("dyInfo.xm", dyInfo_xm));
        list.add(new BasicNameValuePair("dyInfo.xb", dyInfo_xb));
        list.add(new BasicNameValuePair("dyInfo.mz", dyInfo_mz));
        list.add(new BasicNameValuePair("dyInfo.zjhm", dyInfo_zjhm));
        list.add(new BasicNameValuePair("dyInfo.csrq", dyInfo_csrq));
        list.add(new BasicNameValuePair("dyInfo.xl", dyInfo_xl));
        list.add(new BasicNameValuePair("dyInfo.dylb", dyInfo_dylb));
        list.add(new BasicNameValuePair("dyInfo.rdsj", dyInfo_rdsj));
        list.add(new BasicNameValuePair("dyInfo.zzsj", dyInfo_zzsj));
        list.add(new BasicNameValuePair("dyInfo.gzgw", dyInfo_gzgw));
        list.add(new BasicNameValuePair("dyInfo.lxdh", dyInfo_lxdh));
        list.add(new BasicNameValuePair("dyInfo.qh", dyInfo_qh));
        list.add(new BasicNameValuePair("dyInfo.dh", dyInfo_dh));
        list.add(new BasicNameValuePair("dyInfo.xjzd", dyInfo_xjzd));
        list.add(new BasicNameValuePair("dyInfo.lkzgzzrq", dyInfo_lkzgzzrq));
        list.add(new BasicNameValuePair("dyInfo.iscontact", dyInfo_iscontact));
        if ("1".equals(dyInfo_iscontact)) {
            //1表示失联，失联时才会有该字段得值
            list.add(new BasicNameValuePair("dyInfo.losttimeStr", dyInfo_losttimeStr));
        }
        list.add(new BasicNameValuePair("dyInfo.sfzld", dyInfo_sfzld));
        list.add(new BasicNameValuePair("dyInfo.wclx", dyInfo_wclx));
        return list;
    }

    public List<NameValuePair> getNameValuePairListForAddZhengshi() {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("dyInfo.xb", dyInfo_xb));
        list.add(new BasicNameValuePair("dyInfo.mz", dyInfo_mz));
        list.add(new BasicNameValuePair("dyInfo.zjhm", dyInfo_zjhm));
        list.add(new BasicNameValuePair("dyInfo.csrq", dyInfo_csrq));
        list.add(new BasicNameValuePair("dyInfo.xl", dyInfo_xl));
        list.add(new BasicNameValuePair("dyInfo.dylb", dyInfo_dylb));
        list.add(new BasicNameValuePair("dyInfo.rdsj", dyInfo_rdsj));
        list.add(new BasicNameValuePair("dyInfo.zzsj", dyInfo_zzsj));
        list.add(new BasicNameValuePair("dyInfo.gzgw", dyInfo_gzgw));
        list.add(new BasicNameValuePair("dyInfo.lxdh", dyInfo_lxdh));
        list.add(new BasicNameValuePair("dyInfo.qh", dyInfo_qh));
        list.add(new BasicNameValuePair("dyInfo.dh", dyInfo_dh));
        list.add(new BasicNameValuePair("dyInfo.xjzd", dyInfo_xjzd));
        list.add(new BasicNameValuePair("dyInfo.lkzgzzrq", dyInfo_lkzgzzrq));
        list.add(new BasicNameValuePair("dyInfo.iscontact", dyInfo_iscontact));
        if ("1".equals(dyInfo_iscontact)) {
            //1表示失联，失联时才会有该字段得值
            list.add(new BasicNameValuePair("dyInfo.losttimeStr", dyInfo_losttimeStr));
        }
        list.add(new BasicNameValuePair("dyInfo.sfzld", dyInfo_sfzld));
        list.add(new BasicNameValuePair("dyInfo.wclx", dyInfo_wclx));
        return list;
    }

    public List<NameValuePair> getNameValuePairListForAddYubei() {
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("dyInfo.xm", dyInfo_xm));
        list.add(new BasicNameValuePair("dyInfo.xb", dyInfo_xb));
        list.add(new BasicNameValuePair("dyInfo.mz", dyInfo_mz));
        list.add(new BasicNameValuePair("dyInfo.zjhm", dyInfo_zjhm));
        list.add(new BasicNameValuePair("dyInfo.csrq", dyInfo_csrq));
        list.add(new BasicNameValuePair("dyInfo.xl", dyInfo_xl));
        list.add(new BasicNameValuePair("dyInfo.dylb", dyInfo_dylb));
        list.add(new BasicNameValuePair("dyInfo.gzgw", dyInfo_gzgw));
        list.add(new BasicNameValuePair("dyInfo.lxdh", dyInfo_lxdh));
        list.add(new BasicNameValuePair("dyInfo.qh", dyInfo_qh));
        list.add(new BasicNameValuePair("dyInfo.dh", dyInfo_dh));
        list.add(new BasicNameValuePair("dyInfo.xjzd", dyInfo_xjzd));
        return list;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAddReason() {
        return addReason;
    }

    public void setAddReason(String addReason) {
        this.addReason = addReason;
    }

    public String getIsSqrToDyAfterSave() {
        return isSqrToDyAfterSave;
    }

    public void setIsSqrToDyAfterSave(String isSqrToDyAfterSave) {
        this.isSqrToDyAfterSave = isSqrToDyAfterSave;
    }

    public String getBeginEditFlag() {
        return beginEditFlag;
    }

    public void setBeginEditFlag(String beginEditFlag) {
        this.beginEditFlag = beginEditFlag;
    }

    public String getDyInfo_xm() {
        return dyInfo_xm;
    }

    public void setDyInfo_xm(String dyInfo_xm) {
        this.dyInfo_xm = dyInfo_xm;
    }

    public String getDyInfo_xb() {
        return dyInfo_xb;
    }

    public void setDyInfo_xb(String dyInfo_xb) throws Exception {
        if (dyInfo_xb.equals("男"))
            this.dyInfo_xb = "1";
        else if (dyInfo_xb.equals("女"))
            this.dyInfo_xb = "2";
        else
            throw new Exception("性别有误");
    }

    public String getDyInfo_mz() {
        return dyInfo_mz;
    }

    public void setDyInfo_mz(String dyInfo_mz) {
        this.dyInfo_mz = dyInfo_mz.substring(0, 2);
    }

    public String getDyInfo_zjhm() {
        return dyInfo_zjhm;
    }

    public void setDyInfo_zjhm(String dyInfo_zjhm) {
        this.dyInfo_zjhm = dyInfo_zjhm;
    }

    public String getDyInfo_csrq() {
        return dyInfo_csrq;
    }

    public void setDyInfo_csrq(String dyInfo_csrq) {
        this.dyInfo_csrq = dyInfo_csrq.replace("年", "-").replace("月", "-").replace("日", "");
    }

    public String getDyInfo_xl() {
        return dyInfo_xl;
    }

    public void setDyInfo_xl(String dyInfo_xl) {
        this.dyInfo_xl = dyInfo_xl.substring(0, 2).trim();
    }

    public String getDyInfo_dylb() {
        return dyInfo_dylb;
    }

    public void setDyInfo_dylb(String dyInfo_dylb) throws Exception {
        if (dyInfo_dylb.equals("正式党员"))
            this.dyInfo_dylb = "1";
        else if (dyInfo_dylb.equals("预备党员"))
            this.dyInfo_dylb = "2";
        else {
            throw new Exception("党员类别有误");
        }
    }

    public String getDyInfo_rdsj() {
        return dyInfo_rdsj;
    }

    public void setDyInfo_rdsj(String dyInfo_rdsj) {
        this.dyInfo_rdsj = dyInfo_rdsj.replace("年", "-").replace("月", "-").replace("日", "");
    }

    public String getDyInfo_zzsj() {
        return dyInfo_zzsj;
    }

    public void setDyInfo_zzsj(String dyInfo_zzsj) {
        this.dyInfo_zzsj = dyInfo_zzsj.replace("年", "-").replace("月", "-").replace("日", "");
    }

    public String getDyInfo_gzgw() {
        return dyInfo_gzgw;
    }

    public void setDyInfo_gzgw(String dyInfo_gzgw) {
        String[] a = dyInfo_gzgw.split(" ");
        Integer.parseInt(a[0]);
        this.dyInfo_gzgw = a[0];
    }

    public String getDyInfo_lxdh() {
        return dyInfo_lxdh;
    }

    public void setDyInfo_lxdh(String dyInfo_lxdh) {
        this.dyInfo_lxdh = dyInfo_lxdh;
    }

    public String getDyInfo_qh() {
        return dyInfo_qh;
    }

    public void setDyInfo_qh(String dyInfo_qh) {
        this.dyInfo_qh = dyInfo_qh.substring(0, dyInfo_qh.indexOf("-"));
    }

    public String getDyInfo_dh() {
        return dyInfo_dh;
    }

    public void setDyInfo_dh(String dyInfo_dh) {
        this.dyInfo_dh = dyInfo_dh.substring(dyInfo_dh.indexOf("-") + 1);
    }

    public String getDyInfo_xjzd() {
        return dyInfo_xjzd;
    }

    public void setDyInfo_xjzd(String dyInfo_xjzd) {
        this.dyInfo_xjzd = dyInfo_xjzd;
    }

    public String getDyInfo_lkzgzzrq() {
        return dyInfo_lkzgzzrq;
    }

    public void setDyInfo_lkzgzzrq(String dyInfo_lkzgzzrq) {
        this.dyInfo_lkzgzzrq = dyInfo_lkzgzzrq;
    }

    public String getDyInfo_iscontact() {
        return dyInfo_iscontact;
    }

    public void setDyInfo_iscontact(String dyInfo_iscontact) throws Exception {
        if (dyInfo_iscontact.equals("是"))
            this.dyInfo_iscontact = "1";
        else if (dyInfo_iscontact.equals("否"))
            this.dyInfo_iscontact = "2";
        else
            throw new Exception("失联数据有误");
    }

    public String getDyInfo_sfzld() {
        return dyInfo_sfzld;
    }

    public void setDyInfo_sfzld(String dyInfo_sfzld) throws Exception {
        if (dyInfo_sfzld.equals("是"))
            this.dyInfo_sfzld = "1";
        else if (dyInfo_sfzld.equals("否"))
            this.dyInfo_sfzld = "0";
        else
            throw new Exception("是否流动党员数据有误");
    }

    public String getDyInfo_wclx() {
        return dyInfo_wclx;
    }

    public void setDyInfo_wclx(String dyInfo_wclx) {
        this.dyInfo_wclx = dyInfo_wclx;
    }

    public String getDyInfo_losttimeStr() {
        return dyInfo_losttimeStr;
    }

    public void setDyInfo_losttimeStr(String dyInfo_losttimeStr) {
        this.dyInfo_losttimeStr = dyInfo_losttimeStr;
    }

    public void print() throws IllegalAccessException {
        Field[] fs = this.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            System.out.println(f.getName() + ":" + f.get(this));
        }
    }

}
