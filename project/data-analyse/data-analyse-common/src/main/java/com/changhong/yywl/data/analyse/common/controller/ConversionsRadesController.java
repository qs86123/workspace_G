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
import com.changhong.yywl.data.analyse.common.jdbc.entity.CRResultMap;
import com.changhong.yywl.data.analyse.common.jdbc.entity.GoodsEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.ItemData;
import com.changhong.yywl.data.analyse.common.jdbc.entity.UserCRResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.req.RestApiReqforTrade;
import com.changhong.yywl.data.analyse.common.jdbc.response.RestApiReqforUserCR;
import com.changhong.yywl.data.analyse.common.jdbc.service.ActiveUserService;
import com.changhong.yywl.data.analyse.common.jdbc.service.ConversionsRadesService;

@Controller
@RequestMapping("/conversions")
public class ConversionsRadesController {

	@Autowired
	private ConversionsRadesService conversionsRadesService;

	@RequestMapping("/goodsList")
	@ResponseBody
	public RestApiResponse getGoodsList(@RequestBody RestApiRequest req) {
		String startTime = req.getStartTime();
		String endTime = req.getEndTime();
		String category = req.getCategory();
		RestApiResponse rar = new RestApiResponse();
		try {
			List<GoodsEntity> goodsList = conversionsRadesService.findGoodsList(category, startTime, endTime);;

			rar.setData(goodsList);
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

	@RequestMapping("/bussinessCR")
	@ResponseBody
	public RestApiResponse getBussinessTranslate(@RequestBody RestApiReqforTrade req) {
		RestApiResponse rar = new RestApiResponse();
		String startTime = req.getStartTime();
		String endTime = req.getEndTime();
		String category = req.getCategory();
		String goodsId = req.getGoodsId();
		String userType = req.getUserType();
		ActiveUserEntity aue = new ActiveUserEntity();
		List<String> listDate = null;
		List<ItemData> itemDatas = new ArrayList<ItemData>();
		ItemData itemData = new ItemData();
		try {
			listDate = DateUtils.dateList(category, startTime, endTime);
			List<CRResultMap> cr = conversionsRadesService.findTradeCR(category, startTime, endTime, goodsId, userType);
			System.out.println(cr.size());
			itemData.setItemName("交易转化率");
			itemData.setItemCount(getList(cr, listDate));

			itemDatas.add(itemData);
			aue.setItemData(itemDatas);
			aue.setStatus(1);
			aue.setTimeList(listDate);

			rar.setCode(200);
			rar.setMsg("true");
			rar.setData(aue);
			return rar;
		} catch (Exception e) {
			e.printStackTrace();
			rar.setCode(0);
			rar.setMsg("false");
			return rar;
		}
	}

	@RequestMapping("/userCR")
	@ResponseBody
	public RestApiReqforUserCR getUserTranslate() {
		RestApiReqforUserCR rar = new RestApiReqforUserCR();
		try {
			List<CRResultMap> listCR = conversionsRadesService.findBuyerCountCR();
			Map<String, Object> map = getDatas(listCR);
			rar.setLegend(map.get("nameList"));
			rar.setData1(map.get("data0"));
			rar.setData2(map.get("data1"));
			rar.setData3(map.get("data2"));
			rar.setData4(map.get("data3"));
			if ((rar.getData1() == null && rar.getData2() == null && rar.getData3() == null && rar.getData4() == null)
					|| (((List<UserCRResponseEntity>) rar.getData1()).size() == 0
							&& ((List<UserCRResponseEntity>) rar.getData2()).size() == 0
							&& ((List<UserCRResponseEntity>) rar.getData3()).size() == 0
							&& ((List<UserCRResponseEntity>) rar.getData4()).size() == 0))
				rar.setCode(0);
			else
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

	// private List<ItemData> getItemDatas(List<CRResultMap> listCR) {
	// List<ItemData> itemDatas = new ArrayList<ItemData>();
	// ItemData itemDataNotBuy = new ItemData();
	// Integer notBuySum = 0;
	// if (listCR != null && listCR.size() > 0) {
	// for (int i = 0; i < listCR.size(); i++) {
	// CRResultMap crm = listCR.get(i);
	// ItemData itemData = new ItemData();
	// itemData.setItemName(crm.getUserType() + "");
	// itemData.setItemCount(crm.getBuyerCnt());
	// notBuySum += (crm.getUserCnt() - crm.getBuyerCnt());
	// itemDatas.add(itemData);
	// }
	// itemDataNotBuy.setItemName("未购买人数");
	// itemDataNotBuy.setItemCount(notBuySum);
	// itemDatas.add(itemDataNotBuy);
	// }
	// return itemDatas;
	// }

	private Map<String, Object> getDatas(List<CRResultMap> listCR) {
		Map<String, Object> map = new HashMap<>();
		List<String> nameList = new ArrayList<>();
		nameList.add("购买人数");
		nameList.add("未购买人数");
		if (listCR != null && listCR.size() > 0) {
			List<UserCRResponseEntity> data0 = new ArrayList<>();
			UserCRResponseEntity uBuer = new UserCRResponseEntity();
			UserCRResponseEntity uNotBuer = new UserCRResponseEntity();
			int buyAll = 0, all = 0;
			for (int i = 0; i < listCR.size(); i++) {
				List<UserCRResponseEntity> data = new ArrayList<>();
				UserCRResponseEntity u = new UserCRResponseEntity();
				UserCRResponseEntity u2 = new UserCRResponseEntity();
				CRResultMap crm = listCR.get(i);
				buyAll += crm.getBuyerCnt();
				all += crm.getUserCnt();
				u.setName(crm.getUserType() + "端购买人数");
				nameList.add(crm.getUserType() + "端购买人数");
				u.setValue(crm.getBuyerCnt());
				u2.setName(crm.getUserType() + "端未购买人数");
				nameList.add(crm.getUserType() + "端未购买人数");
				u2.setValue(crm.getUserCnt() - crm.getBuyerCnt());
				data.add(u);
				data.add(u2);
				int k = i + 1;
				map.put("data" + k, data);
			}
			uBuer.setName("购买人数");
			uBuer.setValue(buyAll);
			uNotBuer.setName("未购买人数");
			uNotBuer.setValue(all - buyAll);
			data0.add(uBuer);
			data0.add(uNotBuer);
			map.put("data0", data0);
			map.put("nameList", nameList);
		}
		return map;
	}

	private List<Integer> getList(List<CRResultMap> listResultMap, List<String> listDate) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Integer> list = new ArrayList<Integer>();
		// 遍历连续的时间数组，并以此为key，先将数据初始化为0
		for (String date : listDate) {
			map.put(date, 0);
		}
		if (listResultMap != null && listResultMap.size() > 0) {
			for (CRResultMap arm : listResultMap) {
				// 获得查询到的数据的日期，并将其格式化为指定日期，以便能与时间数组的字符串对应上，将map中的对应的时间为key的值改成从数据库查询到的值
				if (arm.getSoldCnt() == 0)
					map.put(arm.getTime(), 0);
				else {
					int bb = (int) ((float) (arm.getSoldCnt() / (float) arm.getViewCnt()) * 100);
					map.put(arm.getTime(), bb <= 100 ? bb : 100);
				}
			}
		}
		// 将map封装成需要的List并返回
		for (String str : listDate) {
			list.add(map.get(str));
		}
		return list;
	}
}
