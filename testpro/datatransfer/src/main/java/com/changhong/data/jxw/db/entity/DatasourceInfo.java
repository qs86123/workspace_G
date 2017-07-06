package com.changhong.data.jxw.db.entity;

import java.util.Date;

/**
 * 
 * 实体类，与数据库的datasource对应
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月22日下午3:44:07
 */
public class DatasourceInfo {

	private String id;
	private String dbType;
	private String url;
	private String username;
	private String pwd;
	private Date addTime;
	// 添加数据的时候，验证信息们是否连接成功
	private String validate;
	private String uid;
	private int port;
	// 数据库名字
	private String schemaName;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
