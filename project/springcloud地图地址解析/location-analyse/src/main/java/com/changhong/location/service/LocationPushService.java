package com.changhong.location.service;


public interface LocationPushService {

	/**
	 * 根据经纬度获取位置信息
	 * @param lng 经度
	 * @param lat 纬度
	 * @author wangtao
	 * @return
	 */
	public Object getLocation(String lng,String lat,boolean parse);
	
}
