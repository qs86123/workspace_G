package com.myself.edu.qgdyxx.bean;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
    //    private String dyInfo_losttimeStr;
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

        uuid = "5c00519fb86f4c619e86c2d54bbd087a";
        beginEditFlag = "1";
        dyInfo_xm = "茆海云";
        dyInfo_xb = "2";
        dyInfo_mz = "01";
        dyInfo_zjhm = "510702197507160541";
        dyInfo_csrq = "1975-07-16";
        dyInfo_xl = "21";
        dyInfo_dylb = "1";
        dyInfo_rdsj = "1997-05-15";
        dyInfo_zzsj = "1998-05-15";
        dyInfo_gzgw = "0214";
        dyInfo_lxdh = "15808163367";
        dyInfo_qh = "0816";
        dyInfo_dh = "2416920";
        dyInfo_xjzd = "虹色景苑";
        dyInfo_iscontact = "2";
//        dyInfo_losttimeStr="2017-12";
        dyInfo_sfzld = "0";
    }

    public List<NameValuePair> getNameValuePairList() {
        List<NameValuePair> list = new ArrayList<>();
        try {
            Field[] fs = this.getClass().getDeclaredFields();
            for (Field f : fs) {
                f.setAccessible(true);
                String key = f.getName().replace("_", ".");
                Object value = f.get(this);
                list.add(new BasicNameValuePair(key, value == null ? "" : value.toString()));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("sadf_kl".replace("_", "."));
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

    public void setDyInfo_xb(String dyInfo_xb) {
        this.dyInfo_xb = dyInfo_xb;
    }

    public String getDyInfo_mz() {
        return dyInfo_mz;
    }

    public void setDyInfo_mz(String dyInfo_mz) {
        this.dyInfo_mz = dyInfo_mz;
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
        this.dyInfo_csrq = dyInfo_csrq;
    }

    public String getDyInfo_xl() {
        return dyInfo_xl;
    }

    public void setDyInfo_xl(String dyInfo_xl) {
        this.dyInfo_xl = dyInfo_xl;
    }

    public String getDyInfo_dylb() {
        return dyInfo_dylb;
    }

    public void setDyInfo_dylb(String dyInfo_dylb) {
        this.dyInfo_dylb = dyInfo_dylb;
    }

    public String getDyInfo_rdsj() {
        return dyInfo_rdsj;
    }

    public void setDyInfo_rdsj(String dyInfo_rdsj) {
        this.dyInfo_rdsj = dyInfo_rdsj;
    }

    public String getDyInfo_zzsj() {
        return dyInfo_zzsj;
    }

    public void setDyInfo_zzsj(String dyInfo_zzsj) {
        this.dyInfo_zzsj = dyInfo_zzsj;
    }

    public String getDyInfo_gzgw() {
        return dyInfo_gzgw;
    }

    public void setDyInfo_gzgw(String dyInfo_gzgw) {
        this.dyInfo_gzgw = dyInfo_gzgw;
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
        this.dyInfo_qh = dyInfo_qh;
    }

    public String getDyInfo_dh() {
        return dyInfo_dh;
    }

    public void setDyInfo_dh(String dyInfo_dh) {
        this.dyInfo_dh = dyInfo_dh;
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

    public void setDyInfo_iscontact(String dyInfo_iscontact) {
        this.dyInfo_iscontact = dyInfo_iscontact;
    }

    public String getDyInfo_sfzld() {
        return dyInfo_sfzld;
    }

    public void setDyInfo_sfzld(String dyInfo_sfzld) {
        this.dyInfo_sfzld = dyInfo_sfzld;
    }

    public String getDyInfo_wclx() {
        return dyInfo_wclx;
    }

    public void setDyInfo_wclx(String dyInfo_wclx) {
        this.dyInfo_wclx = dyInfo_wclx;
    }
}
