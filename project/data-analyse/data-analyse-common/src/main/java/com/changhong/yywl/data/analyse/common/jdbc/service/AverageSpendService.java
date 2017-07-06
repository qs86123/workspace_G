package com.changhong.yywl.data.analyse.common.jdbc.service;

import com.changhong.yywl.data.analyse.common.jdbc.entity.AverageConsumeEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarGraphResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.mapper.AverageSpendMapper;
import com.changhong.yywl.data.analyse.common.util.DateUtils;
import com.changhong.yywl.data.analyse.common.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 16-9-7.
 */
@Service
public class AverageSpendService {
    @Autowired
    private AverageSpendMapper averageSpendMapper;

    /**
     * 组装用户平均花费数据
     * @param category
     * @param start
     * @param end
     * @return
     */
    public BarGraphResponseEntity findSpendData(String category,String start,String end)
    {
        List<String> dateList= DateUtils.dateList(category, start, end);
        Map<String,String> query=new HashMap<String,String>();
        query.put("category",category);
        query.put("startTime",start);
        query.put("endTime",end);
        List<String> userTypes=averageSpendMapper.findTypeList(query);
        if(ListUtils.isEmpty(userTypes))
        {
            return null;
        }

        List<BarEntity> barEntities=new ArrayList<BarEntity>();
        List<BigDecimal> all=new ArrayList<BigDecimal>();
        for(int i=0;i<dateList.size();i++)
        {
            all.add(new BigDecimal(0));
        }
        for(String type:userTypes)
        {
            query.put("userType",type);
            List<AverageConsumeEntity> aveList=averageSpendMapper.findAveConsume(query);
            BarEntity barEntity=new BarEntity();
            barEntity.setItemName(type);
            barEntity.setItemCount(new ArrayList<Integer>());
            for(int i=0;i<dateList.size();i++)
            {
                barEntity.getItemCount().add(0);
            }
            for (AverageConsumeEntity ave:aveList)
            {
                int index= dateList.indexOf(ave.getTime());
                all.set(index,all.get(index).add(ave.getAvgConsume()));
                barEntity.getItemCount().set(index,ave.getAvgConsume());
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
