package com.changhong.yywl.data.analyse.common.controller;

import com.changhong.yywl.data.analyse.common.RestApiRequest;
import com.changhong.yywl.data.analyse.common.RestApiResponse;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarGraphResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.SectionNameEntity;
import com.changhong.yywl.data.analyse.common.jdbc.service.PageBrowseTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 页面访问趋势控制器
 * Created by Administrator on 16-9-7.
 */
@Controller
public class PageBrowseTrendController {
    @Autowired
    private PageBrowseTrendService pageBrowseTrendService;

    /**
     * 获取请求中限制的页面访问数据
     * @param restApiRequest 请求json对象，请求示例：{"startTime":"2016-01-01","endTime":"2016-01-07","category":"daily","pageName":"1"}
     * @return
     */
    @RequestMapping("/pageBrowseTrend")
    @ResponseBody
    public RestApiResponse pageBrowseTrend(@RequestBody RestApiRequest restApiRequest)
    {
        RestApiResponse restApiResponse=new RestApiResponse();
        try{
            BarGraphResponseEntity data=pageBrowseTrendService.findBrowseTrendData(restApiRequest.getCategory(),restApiRequest.getStartTime(),restApiRequest.getEndTime(),restApiRequest.getPageName());
            if(data!=null)
            {
                restApiResponse.setCode(200);
                restApiResponse.setData(data);
            }
            return restApiResponse;
        }catch (Exception e)
        {
            restApiResponse.setCode(404);
            restApiResponse.setData(null);
            return restApiResponse;
        }
    }

    /**
     * 获取页面名称列表，每个名称绑定了一个键用于请求使用
     * @return
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public RestApiResponse pageList()
    {
        RestApiResponse restApiResponse=new RestApiResponse();
        try {
            List<SectionNameEntity> data=pageBrowseTrendService.findPageName();
            if(data!=null&&data.size()>0)
            {
                restApiResponse.setCode(200);
                restApiResponse.setData(data);
            }
            return restApiResponse;
        }catch (Exception e)
        {
            restApiResponse.setCode(404);
            restApiResponse.setData(null);
            return restApiResponse;
        }
    }

}
