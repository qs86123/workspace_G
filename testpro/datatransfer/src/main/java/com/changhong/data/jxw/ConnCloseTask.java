package com.changhong.data.jxw;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.changhong.data.jxw.db.utils.MongoDBUtils;
import com.changhong.data.jxw.db.utils.MysqlUtils;

/**
 * 
 * 定时关闭mongo和mysql的连接
 * 
 * Company: changhong
 * 
 * @author wangtao
 * @date 2017年3月24日下午2:58:42
 */
@Component
public class ConnCloseTask {

	// 每6分钟执行一次，关闭mongo和mysql的链接
	@Scheduled(fixedRate = 360000)
	public void repeat() {
		MysqlUtils.closeConn();
		MongoDBUtils.closeConn();
	}

}
