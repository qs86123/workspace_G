package com.changhong.data.jxw.db.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

import com.changhong.data.jxw.db.exception.ConnException;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoTimeoutException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * mongo数据库连接工具类
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日上午10:09:05
 */
public class MongoDBUtils {

	private static Map<String, MongoClient> conns = new HashMap<>();

	/**
	 * 获得mongo的client
	 * 
	 * @param host
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 * @return
	 */
	public static MongoClient getMongoClient(String host, int port, String dbName, String username, String password) {
		MongoClient mc = conns.get(host + port + dbName + username + password);
		// 添加连接缓存，避免每次访问都重新连接
		if (mc != null) {
			System.out.println("return cached mongoClient");
			return mc;
		}
		if (username != null) {// 判断有密码，进行登录再连接
			ServerAddress sa = new ServerAddress(host, port);
			List<MongoCredential> options = new ArrayList<MongoCredential>();
			MongoCredential mct = MongoCredential.createCredential(username, dbName, password.toCharArray());
			options.add(mct);
			mc = new MongoClient(sa, options);
		} else {// 如果没有密码直接连接
			System.out.println("no password");
			mc = new MongoClient(host, port);
			System.out.println("connection success");
		}
		conns.put(host + port + dbName + username + password, mc);
		return mc;
	}

	// public static MongoDatabase getMongoClientTest(DatasourceInfo dsInfo) {
	// MongoClientURI uri = new MongoClientURI("mongodb://" +
	// dsInfo.getUsername() + ":" + dsInfo.getPwd() + "@"
	// + dsInfo.getUrl() + "/?authSource=" + dsInfo.getSchemaName());
	// MongoClient mongoClient = new MongoClient(uri);
	// MongoDatabase database = mongoClient.getDatabase(dsInfo.getSchemaName());
	//
	// return database;
	// }

	/**
	 * 通过client获得mongoDatabase的链接
	 * 
	 * @param client
	 * @param dbName
	 * @return
	 */
	public static MongoDatabase getMongoDataBase(MongoClient client, String dbName) throws ConnException {
		try {
			return client.getDatabase(dbName);
		} catch (Exception e) {
			throw new ConnException("未知数据库名");
		}
	}

	/**
	 * 获取到某个集合
	 * 
	 * @param mdb
	 * @param collectionName
	 * @return
	 */
	public static MongoCollection<Document> getMongoCollection(MongoDatabase mdb, String collectionName) {
		return mdb.getCollection(collectionName);
	}

	/**
	 * 获取某个库下面所有collection的名字
	 * 
	 * @param mdb
	 * @return
	 * @throws ConnException
	 */
	public static String[] getCollectionNames(MongoDatabase mdb) throws ConnException {
		String str = "";
		MongoIterable<String> names = mdb.listCollectionNames();
		MongoCursor<String> iterator = null;
		try {
			iterator = names.iterator();
		} catch (Exception e) {
			e.printStackTrace();
			String msg = e.getMessage();
			if (msg.indexOf("Authentication failed") != -1)
				throw new ConnException("用户名与密码不匹配,或该用户没有访问此数据库的权限");
			if (msg.indexOf("Connection refused") != -1)
				throw new ConnException("连接被拒绝,请检查ip和端口是否正确");
			else if (e instanceof MongoTimeoutException)
				throw new ConnException("连接超时，请检查连接是否有误");
			else
				throw new ConnException("连接失败，未知原因");
		}
		while (iterator.hasNext()) {
			String name = iterator.next();
			str += name + ",";
		}
		return str.split(",");
	}

	// 关闭连接
	public static void closeResource(MongoCursor<?> cursor, MongoClient client) {
		try {
			if (cursor != null)
				cursor.close();
			if (client != null)
				client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 在定时任务中调度，定时清除链接信息
	public static void closeConn() {
		System.out.println("mongo client close all");
		Set<String> keys = conns.keySet();
		for (String key : keys) {
			closeResource(null, conns.get(key));
			conns.remove(key);
		}
	}

}
