package com.changhong.data.activity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.data.activity.dao.ActivityInfoDao;
import com.changhong.data.activity.entity.ActivityInfo;

@Service
public class ActivityInfoService {

	@Autowired
	private ActivityInfoDao aiDao;

	public String save(ActivityInfo entity) {
		ActivityInfo ai = aiDao.save(entity);
		return ai.getId();
	}

	public ActivityInfo findById(String id) {
		ActivityInfo ai = aiDao.findById(id);
		return ai;
	}

	public void deleteById(String id) {
		aiDao.deleteById(id);
	}

	public List<ActivityInfo> findAll() {
		List<ActivityInfo> ais = aiDao.findAll();
		return ais;
	}

	public List<ActivityInfo> findByNameContaining(String name) {
		List<ActivityInfo> ais = aiDao.findByNameContaining(name);
		return ais;
	}

	public List<ActivityInfo> findByIdIn(List<String> ids) {
		List<ActivityInfo> ais = aiDao.findByIdIn(ids);
		return ais;
	}

	public List<ActivityInfo> findByStatus(Integer status) {
		List<ActivityInfo> ais = aiDao.findByStatus(status);
		return ais;
	}

	public List<ActivityInfo> findByLngAndLat(double minLng, double maxlng, double minlat, double maxlat) {
		List<ActivityInfo> ais = aiDao.findByLngAndLat(minLng, maxlng, minlat, maxlat);
		return ais;
	}

	public List<ActivityInfo> getPositions(double dlng, double dlat, double radius) throws Exception {
		List<ActivityInfo> result = new ArrayList<ActivityInfo>();
		System.out.println("初始位置：" + dlat + "," + dlng);
		// 计算一个正方形范围的经纬度的偏差，用于第一次过滤掉不在该正方形范围内的目标点
		// 地图上纬度1°对应约111km，假如要计算2km对应的度数，x/2km=1°/111km
		// 经度1°对应约111cos(a)km，这里的a是当前的纬度值，不是经度值
		double wd = 1d / 111d * radius / 1000;
		double jd = 1 / (111 * Math.cos(dlat)) * radius / 1000;
		double maxjd = dlng + jd;
		double maxwd = dlat + wd;
		double minjd = dlng - jd;
		double minwd = dlat - wd;
		// 第一次过滤，循环过滤掉不在该正方形区域里面的点
		List<ActivityInfo> list = findByLngAndLat(minjd, maxjd, minwd, maxwd);
		// 第二次过滤，循环在该正方形中存在的点，找出距离当前位置小于radius的点，即以radius为半径的圆内的点
		for (ActivityInfo l : list) {
			double dis = getDistance(dlng, dlat, l.getLng(), l.getLat());
			if (dis <= radius) {
				String str = (dis + "").substring(0, (dis + "").indexOf("."));
				try {
					l.setDistence(Double.parseDouble(str));
				} catch (Exception e) {
					l.setDistence(dis);
				}
				result.add(l);
				System.out.println(l.getLat() + "," + l.getLng() + "dis" + l.getDistence());
			}
		}
		System.out.println("查询到的位置：" + result.size());
		return result;
	}

	public double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
		// 维度
		double lat1 = (Math.PI / 180) * latitude1;
		double lat2 = (Math.PI / 180) * latitude2;

		// 经度
		double lon1 = (Math.PI / 180) * longitude1;
		double lon2 = (Math.PI / 180) * longitude2;

		// 地球半径
		double R = 6371;

		// 两点间距离 km，如果想要米的话，结果*1000就可以了
		double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1))
				* R;

		return d * 1000;
	}
}
