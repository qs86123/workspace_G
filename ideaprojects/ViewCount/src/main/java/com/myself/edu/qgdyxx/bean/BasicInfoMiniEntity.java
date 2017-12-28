package com.myself.edu.qgdyxx.bean;

import java.lang.reflect.Field;

/**
 * @description
 * @author: wangtao
 * @date:9:52 2017/12/28
 * @email:tao8.wang@changhong.com
 */
public class BasicInfoMiniEntity {

    private String uuid;
    private String organid;
    private String content;

    public BasicInfoMiniEntity() {
    }

    public BasicInfoMiniEntity(String uuid, String organid, String content) {
        this.uuid = uuid;
        this.organid = organid;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOrganid() {
        return organid;
    }

    public void setOrganid(String organid) {
        this.organid = organid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
