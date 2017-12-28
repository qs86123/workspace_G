package com.myself.edu.qgdyxx.bean;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author: wangtao
 * @Date:10:56 2017/11/7
 * @Email:tao8.wang@changhong.com
 */
public class ZZJGEntity {
    private String id;
    private String name;
    private String type;
    private String organtype;
    private String parentId;
    private String isParent;
    private String childOuter;
    private String dropInner;
    private String schemaname;
    private String childs;
    private String icon;
    private String orderNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgantype() {
        return organtype;
    }

    public void setOrgantype(String organtype) {
        this.organtype = organtype;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getChildOuter() {
        return childOuter;
    }

    public void setChildOuter(String childOuter) {
        this.childOuter = childOuter;
    }

    public String getDropInner() {
        return dropInner;
    }

    public void setDropInner(String dropInner) {
        this.dropInner = dropInner;
    }

    public String getSchemaname() {
        return schemaname;
    }

    public void setSchemaname(String schemaname) {
        this.schemaname = schemaname;
    }

    public String getChilds() {
        return childs;
    }

    public void setChilds(String childs) {
        this.childs = childs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "ZZJGEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", organtype='" + organtype + '\'' +
                ", parentId='" + parentId + '\'' +
                ", isParent='" + isParent + '\'' +
                ", childOuter='" + childOuter + '\'' +
                ", dropInner='" + dropInner + '\'' +
                ", schemaname='" + schemaname + '\'' +
                ", childs='" + childs + '\'' +
                ", icon='" + icon + '\'' +
                ", orderNum='" + orderNum + '\'' +
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
}
