package com.changhong.yywl.data.analyse.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changhong.yywl.data.analyse.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.yywl.data.analyse.common.RestApiRequest;
import com.changhong.yywl.data.analyse.common.RestApiResponse;
import com.changhong.yywl.data.analyse.common.jdbc.entity.ActiveUserEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.ActiveUserResultMap;
import com.changhong.yywl.data.analyse.common.jdbc.entity.ItemData;
import com.changhong.yywl.data.analyse.common.jdbc.service.ActiveUserService;

@Controller
@RequestMapping("/active")
public class ActiveUserController {

	@Autowired
	private ActiveUserService activeUserService;

	/**
	 * 查询指定时间段的用户活跃度数据 返回结果时间是连续的，没有活跃度填充0
	 * 
	 * @param req
	 * @return
	 */

	@RequestMapping("/userCount")
	@ResponseBody
	public RestApiResponse getActiveUserCount(@RequestBody RestApiRequest req) {
		String category = req.getCategory();
		String startTime = req.getStartTime();
		String endTime = req.getEndTime();
		List<String> listDate = null;
		List<String> listUserType = null;
		ActiveUserEntity aue = new ActiveUserEntity();
		RestApiResponse rar = new RestApiResponse();
		List<ItemData> itemDatas = new ArrayList<ItemData>();
		ItemData itemDataAll = new ItemData();
		try {
			// 根据起止日期获得连续的时间日期的string数组
			listDate = DateUtils.dateList(category,startTime, endTime);
			listUserType = activeUserService.findUserType();
			// 传入的数组表示用户类型，1B端，2B带C端，3，C端
			// List<ActiveUserResultMap> listResultMap =
			// activeUserService.findUserCount(category, startTime, endTime,
			// new Integer[] { 1 });
			// List<ActiveUserResultMap> listResultMapB2C =
			// activeUserService.findUserCount(category, startTime, endTime,
			// new Integer[] { 2 });
			// List<ActiveUserResultMap> listResultMapC =
			// activeUserService.findUserCount(category, startTime, endTime,
			// new Integer[] { 3 });
			// List<Integer> listB = getList(listResultMap, listDate);
			// List<Integer> listB2C = getList(listResultMapB2C, listDate);
			// List<Integer> listC = getList(listResultMapC, listDate);
			itemDatas = getItemDatas(listDate, category, startTime, endTime, listUserType);
			List<Integer> listAll = getListAll(itemDatas);

			itemDataAll.setItemName("全部");
			itemDataAll.setItemCount(listAll);
			itemDatas.add(itemDataAll);

			aue.setItemData(itemDatas);
			aue.setStatus(1);
			aue.setTimeList(listDate);

			rar.setData(aue);
			rar.setCode(200);
			rar.setMsg("true");
			return rar;
		} catch (Exception e) {
			e.printStackTrace();
			rar.setCode(0);
			rar.setMsg("false");
			return rar;
		}
	}

	// private List<Integer> getListAll(List<Integer> listB, List<Integer>
	// listB2C, List<Integer> listC) {
	// List<Integer> listAll = new ArrayList<Integer>();
	// for (int i = 0; i < listB.size(); i++) {
	// listAll.add(listB.get(i) + listB2C.get(i) + listC.get(i));
	// }
	// return listAll;
	// }

	private List<Integer> getListAll(List<ItemData> itemDatas) {
		List<Integer> listAll = new ArrayList<Integer>();
		int sum = 0;
		if (itemDatas != null && itemDatas.size() > 0) {
			int total = ((List<Integer>) (itemDatas.get(0).getItemCount())).size();
			for (int i = 0; i < total; i++) {
				for (int j = 0; j < itemDatas.size(); j++) {
					// 把每一个itemdata的itemCount的第i项都取出来相加
					sum += ((List<Integer>) (itemDatas.get(j).getItemCount())).get(i);
				}
				listAll.add(sum);
				sum = 0;
			}
		}
		return listAll;
	}

	private List<Integer> getList(List<ActiveUserResultMap> listResultMap, List<String> listDate) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Integer> list = new ArrayList<Integer>();
		// 遍历连续的时间数组，并以此为key，先将数据初始化为0
		for (String date : listDate) {
			map.put(date, 0);
		}
		if (listResultMap != null && listResultMap.size() > 0) {
			for (ActiveUserResultMap arm : listResultMap) {
				// 获得查询到的数据的日期，并将其格式化为指定日期，以便能与时间数组的字符串对应上，将map中的对应的时间为key的值改成从数据库查询到的值
				map.put(arm.getTime(), arm.getMacCnt());
			}
		}
		// 将map封装成需要的List并返回
		for (String str : listDate)
			list.add(map.get(str));
		return list;
	}

	public List<ItemData> getItemDatas(List<String> listDate, String category, String startTime, String endTime,
			List<String> listUserType) throws Exception {
		List<ItemData> itemDatas = new ArrayList<ItemData>();
		if (listUserType != null && listUserType.size() > 0) {
			for (int i = 0; i < listUserType.size(); i++) {
				// 如果需要将两个user_type的用户合为一个类型的话加一个判断if(userType!=0&&userType!=1),在else中执行查询合并的用户的数据
				List<ActiveUserResultMap> listResultMap = activeUserService.findUserCount(category, startTime, endTime,
						 listUserType.get(i));
				List<Integer> itemCount = getList(listResultMap, listDate);
				ItemData itemData = new ItemData();
				itemData.setItemName(listUserType.get(i) + "");
				itemData.setItemCount(itemCount);
				itemDatas.add(itemData);
			}
		}
		return itemDatas;
	}

}
