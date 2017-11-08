package com.myself.edu.qgdyxx.bean;

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
}
