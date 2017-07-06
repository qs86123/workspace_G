package com.changhong.yywl.data.analyse.common.jdbc.service;

import com.changhong.yywl.data.analyse.common.jdbc.entity.AllUserCountEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarGraphResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.UserCountEntity;
import com.changhong.yywl.data.analyse.common.jdbc.mapper.RegisterCountMapper;
import com.changhong.yywl.data.analyse.common.util.DateUtils;
import com.changhong.yywl.data.analyse.common.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 16-9-7.
 */
@Service
public class RegisterCountService {
    @Autowired
    private RegisterCountMapper registerCountMapper;

    /**
     * 组装用户各个模块用户总数
     * @return
     */
    public List<AllUserCountEntity> findAllUserCount()
    {
        List<AllUserCountEntity> list=registerCountMapper.findAllUC();
        if(list!=null&&list.size()>0)
        {
            AllUserCountEntity allUserCountEntity=new AllUserCountEntity();
            int count=0;
            for (AllUserCountEntity allUserCountEntity1:list)
            {
                count+=allUserCountEntity1.getUserCount();
            }
            allUserCountEntity.setUserCount(count);
            allUserCountEntity.setUserType("全部");
            list.add(allUserCountEntity);
        }
        return list;
    }

    /**
     * 组装各个类型用户分时段激活数据
     * @param category
     * @param start
     * @param end
     * @return
     */
    public BarGraphResponseEntity findUserCount(String category,String start,String end)
    {
        List<String> dateList= DateUtils.dateList(category, start, end);
        Map<String,String> query=new HashMap<String,String>();
        query.put("category",category);
        query.put("startTime",start);
        query.put("endTime",end);
        List<String> userTypes=registerCountMapper.findTypeList(query);
        if(ListUtils.isEmpty(userTypes))
        {
            return null;
        }

        List<BarEntity> barEntities=new ArrayList<BarEntity>();
        List<Integer> all=new ArrayList<Integer>();
        for(int i=0;i<dateList.size();i++)
        {
            all.add(0);
        }
        for(String type:userTypes)
        {
            query.put("userType",type);
            List<UserCountEntity> useList=registerCountMapper.findUserCount(query);
            BarEntity barEntity=new BarEntity();
            barEntity.setItemName(type);
            barEntity.setItemCount(new ArrayList<Integer>());
            for(int i=0;i<dateList.size();i++)
            {
                barEntity.getItemCount().add(0);
            }
            for (UserCountEntity use:useList)
            {
                int index= dateList.indexOf(use.getTime());
                all.set(index,all.get(index)+use.getUserCount());
                barEntity.getItemCount().set(index,use.getUserCount());
            }
            barEntities.add(barEntity);
        }
        BarEntity barEntity=new BarEntity();
        barEntity.setItemName("全部");
        barEntity.setItemCount(all);
        barEntities.add(barEntity);


        BarGraphResponseEntity barGraphResponseEntity=new BarGraphResponseEntity();
        barGraphResponseEntity.setTimeList(dateList);
        barGraphResponseEntity.setItemData(barEntities);
        return barGraphResponseEntity;
    }
}
