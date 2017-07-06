package com.changhong.yywl.data.analyse.common.controller;

import com.changhong.yywl.data.analyse.common.RestApiRequest;
import com.changhong.yywl.data.analyse.common.RestApiResponse;
import com.changhong.yywl.data.analyse.common.jdbc.entity.AllUserCountEntity;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarGraphResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.service.RegisterCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 注册用户数控制器
 * Created by Administrator on 16-9-7.
 */
@Controller
public class RegisterCountController {
    @Autowired
    private RegisterCountService registerCountService;

    /**
     *获取请求中限制的注册用户数数据
     * @param restApiRequest 请求json对象，请求示例：{"startTime":"2016-01-01","endTime":"2016-01-07","category":"daily"}
     * @return
     */
    @RequestMapping("/registerCount")
    @ResponseBody
    public RestApiResponse userCount(@RequestBody RestApiRequest restApiRequest)
    {
        RestApiResponse restApiResponse=new RestApiResponse();
        try {
            BarGraphResponseEntity data=registerCountService.findUserCount(restApiRequest.getCategory(),restApiRequest.getStartTime(),restApiRequest.getEndTime());
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
     * 获取各个类型用户的总用户数
     * @return
     */
    @RequestMapping("/allUserCount")
    @ResponseBody
    public RestApiResponse allUserCount()
    {
        RestApiResponse restApiResponse=new RestApiResponse();
        try {
            List<AllUserCountEntity> data=registerCountService.findAllUserCount();
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
