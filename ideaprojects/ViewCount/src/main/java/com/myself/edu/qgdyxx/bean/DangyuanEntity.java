package com.myself.edu.qgdyxx.bean;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:56 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class DangyuanEntity {

    private String uuid;
    private String realName;
    private String identityCard;
    private String sexName;
    private String age;
    private String national;
    private String partyOrganName;
    private String xxwzd;
    private String dylb;
    private String rdrq;
    private String dyzt;
    private String joinDateFlag;
    private String organid;
    private String idcardmult;
    private String dyFlag;
    private String idcardvalidity;
    private String hasErrorMsg;
    private String errorMsg;
    private String checkBirthday;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getPartyOrganName() {
        return partyOrganName;
    }

    public void setPartyOrganName(String partyOrganName) {
        this.partyOrganName = partyOrganName;
    }

    public String getXxwzd() {
        return xxwzd;
    }

    public void setXxwzd(String xxwzd) {
        this.xxwzd = xxwzd;
    }

    public String getDylb() {
        return dylb;
    }

    public void setDylb(String dylb) {
        this.dylb = dylb;
    }

    public String getRdrq() {
        return rdrq;
    }

    public void setRdrq(String rdrq) {
        this.rdrq = rdrq;
    }

    public String getDyzt() {
        return dyzt;
    }

    public void setDyzt(String dyzt) {
        this.dyzt = dyzt;
    }

    public String getJoinDateFlag() {
        return joinDateFlag;
    }

    public void setJoinDateFlag(String joinDateFlag) {
        this.joinDateFlag = joinDateFlag;
    }

    public String getOrganid() {
        return organid;
    }

    public void setOrganid(String organid) {
        this.organid = organid;
    }

    public String getIdcardmult() {
        return idcardmult;
    }

    public void setIdcardmult(String idcardmult) {
        this.idcardmult = idcardmult;
    }

    public String getDyFlag() {
        return dyFlag;
    }

    public void setDyFlag(String dyFlag) {
        this.dyFlag = dyFlag;
    }

    public String getIdcardvalidity() {
        return idcardvalidity;
    }

    public void setIdcardvalidity(String idcardvalidity) {
        this.idcardvalidity = idcardvalidity;
    }

    public String getHasErrorMsg() {
        return hasErrorMsg;
    }

    public void setHasErrorMsg(String hasErrorMsg) {
        this.hasErrorMsg = hasErrorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCheckBirthday() {
        return checkBirthday;
    }

    public void setCheckBirthday(String checkBirthday) {
        this.checkBirthday = checkBirthday;
    }

    @Override
    public String toString() {
        return "DangyuanEntity{" +
                "uuid='" + uuid + '\'' +
                ", realName='" + realName + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", sexName='" + sexName + '\'' +
                ", age='" + age + '\'' +
                ", national='" + national + '\'' +
                ", partyOrganName='" + partyOrganName + '\'' +
                ", xxwzd='" + xxwzd + '\'' +
                ", dylb='" + dylb + '\'' +
                ", rdrq='" + rdrq + '\'' +
                ", dyzt='" + dyzt + '\'' +
                ", joinDateFlag='" + joinDateFlag + '\'' +
                ", organid='" + organid + '\'' +
                ", idcardmult='" + idcardmult + '\'' +
                ", dyFlag='" + dyFlag + '\'' +
                ", idcardvalidity='" + idcardvalidity + '\'' +
                ", hasErrorMsg='" + hasErrorMsg + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", checkBirthday='" + checkBirthday + '\'' +
                '}';
    }

    public String toSql(String tableName) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbValue = new StringBuilder();
        sb.append("INSERT INTO " + tableName + " (");
        Field[] fs = this.getClass().getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            fs[i].setAccessible(true);
            String dbName = getDbName(fs[i].getName());
            sb.append("`" + dbName + "`,");
            Object value = null;
            try {
                value = fs[i].get(this);
            } catch (IllegalAccessException e) {
                ;
            }
            sbValue.append(value == null ? "null," : "'" + value + "',");
        }
        String str1 = sb.toString();
        str1 = str1.substring(0, str1.length() - 1) + ") values(";
        String str2 = sbValue.toString();
        str2 = str2.substring(0, str2.length() - 1) + ")";
        return str1 + str2;
    }

    private String getDbName(String name) {
        StringBuilder sb = new StringBuilder();
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (c >= 'A' && c <= 'Z') {
                sb.append(("_" + c).toLowerCase());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DangyuanEntity().toSql("aaa"));
    }
}
