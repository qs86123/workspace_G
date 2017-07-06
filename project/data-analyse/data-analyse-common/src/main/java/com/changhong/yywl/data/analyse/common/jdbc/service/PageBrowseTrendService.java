package com.changhong.yywl.data.analyse.common.jdbc.service;

import com.changhong.yywl.data.analyse.common.jdbc.entity.BarEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarGraphResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.PageBrowseCountEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.SectionNameEntity;
import com.changhong.yywl.data.analyse.common.jdbc.mapper.PageBrowseTrendMapper;
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
public class PageBrowseTrendService {
    @Autowired
    private PageBrowseTrendMapper pageBrowseTrendMapper;

    /**
     * 组装页面名称数据
     * @return
     */
    public List<SectionNameEntity> findPageName()
    {
        List<String> list=pageBrowseTrendMapper.findPages();
        List<SectionNameEntity> retList=new ArrayList<SectionNameEntity>();
        int i=0;
        for (String s:list)
        {
            if ("应用启动".equals(s))//过滤
            {
                continue;
            }
            SectionNameEntity sectionNameEntity=new SectionNameEntity();
            sectionNameEntity.setKey(""+i++);
            sectionNameEntity.setSectionName(s);
            retList.add(sectionNameEntity);
        }
        return retList;
    }

    /**
     * 组装页面访问趋势数据
     * @param category
     * @param start
     * @param end
     * @param code
     * @return
     */
    public BarGraphResponseEntity findBrowseTrendData(String category,String start,String end,String code)
    {
        List<String> dateList= DateUtils.dateList(category, start, end);
        List<SectionNameEntity> sectionNameEntities=findPageName();
        String pageName="";
        for (SectionNameEntity sectionNameEntity:sectionNameEntities)
        {
            if (sectionNameEntity.getKey().equals(code))
            {
                pageName=sectionNameEntity.getSectionName();
                break;
            }
        }
        if("".equals(pageName))
        {
            return null;
        }
        Map<String,String> query=new HashMap<String,String>();
        query.put("category",category);
        query.put("startTime",start);
        query.put("endTime",end);
        query.put("pageName",pageName);
        List<String> userTypes=pageBrowseTrendMapper.findTypeList(query);
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
            List<PageBrowseCountEntity> pageBrowseCountList=pageBrowseTrendMapper.findPageBrowseData(query);
            BarEntity barEntity=new BarEntity();
            barEntity.setItemName(type);
            barEntity.setItemCount(new ArrayList<Integer>());
            for(int i=0;i<dateList.size();i++)
            {
                barEntity.getItemCount().add(0);
            }
            for (PageBrowseCountEntity page:pageBrowseCountList)
            {
                int index= dateList.indexOf(page.getTime());
                all.set(index, all.get(index) + page.getCount());
                barEntity.getItemCount().set(index,page.getCount());
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
