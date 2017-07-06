package com.changhong.yywl.data.analyse.common.jdbc.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.yywl.data.analyse.common.jdbc.entity.ActiveUserResultMap;
import com.changhong.yywl.data.analyse.common.jdbc.mapper.ActiveUserMapper;

@Service
public class ActiveUserService {

	@Autowired
	private ActiveUserMapper activeUserMapper;

	/**
	 * 根据用户类型查询用户活跃度
	 * 
	 * @param category
	 *            时间粒度
	 * @param startTime
	 * @param endTime
	 * @param userType
	 * @return
	 */
	public List<ActiveUserResultMap> findUserCount(String category, String startTime, String endTime,
			String userType) {
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("userType", userType);
		List<ActiveUserResultMap> lists = activeUserMapper.selectUserCount(map);
		return lists;
	}

	public List<String> findUserType() {
		return activeUserMapper.selectUserType();
	}

	/**
	 * 获取格式化之后的连续的时间数组,eg:[2018-01-01,2018-01-02...]
	 * 
	 * @param startTime
	 * @param endTime
	 * @throws Exception
	 */
	/*public static List<String> getDateList(String startTime, String endTime, String type) throws Exception {
        List<String> listDate = new ArrayList<String>();
        if (type.equals("daily")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            for (; startDate.getTime() <= endDate.getTime(); startDate
                    .setTime(startDate.getTime() + 1 * 24 * 60 * 60 * 1000l)) {
                listDate.add(sdf.format(startDate));
            }
        } else if (type.equals("monthly")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            for (; startDate.getTime() <= endDate.getTime(); startDate.setMonth(startDate.getMonth() + 1)) {
                listDate.add(sdf.format(startDate));
            }
        } else if (type.equals("weekly")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyww");
            Calendar c = Calendar.getInstance();

            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            c.setTime(startDate);
            // 这里年份自己取，如果采用c.get(Calendar.YEAR)的方式取的话，如果当前周是第一周，那么取出来的年份将会是去年的
            // eg：如果startTime=201401，那么取出来的年份将会是2013，也就是说每年的第一周都是从去年开始的，取年份的时候日历会取星期一所在的那一年
            int startYear = Integer.parseInt(startTime.substring(0, 4));
            listDate.add(sdf.format(startDate));
            while (startDate.getTime() <= endDate.getTime()) {
                c.add(Calendar.WEEK_OF_YEAR, 1);
                startDate = c.getTime();// 把加了一周之后的日期重新赋值给startDate，继续循环
                int currentWeek = c.get(Calendar.WEEK_OF_YEAR);
                if (currentWeek == 1)
                    startYear++;
                listDate.add(currentWeek < 10 ? (startYear + "0" + currentWeek) : (startYear + "" + currentWeek));
            }
        }
        return listDate;
    }
*/
}
