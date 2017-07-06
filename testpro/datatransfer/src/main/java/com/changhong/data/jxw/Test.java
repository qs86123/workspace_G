package com.changhong.data.jxw;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.changhong.data.jxw.db.utils.MysqlUtils;

public class Test {
	public static void main(String[] args) {
		try {
			Connection conn = MysqlUtils.getConnection("192.168.11.105", "3306", "bigdata", "admin",
					"admin");
			PreparedStatement st = conn.prepareStatement(
					"insert into datasource (id,db_type,url,port,schema_name,username,pwd,validate,uid) values ('eee','tt','127.0.0.1','3306','hh','username','password','中文','uid')");
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
