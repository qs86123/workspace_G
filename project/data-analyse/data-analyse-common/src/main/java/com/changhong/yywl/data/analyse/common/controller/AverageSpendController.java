package com.changhong.yywl.data.analyse.common.controller;

import com.changhong.yywl.data.analyse.common.RestApiRequest;
import com.changhong.yywl.data.analyse.common.RestApiResponse;
import com.changhong.yywl.data.analyse.common.jdbc.entity.BarGraphResponseEntity;
import com.changhong.yywl.data.analyse.common.jdbc.service.AverageSpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 平均消费金额控制器
 * Created by Administrator on 16-9-7.
 */
@Controller
public class AverageSpendController {
    @Autowired
    private AverageSpendService averageSpendService;

    /**
     * 平均消费金额接口
     * @param restApiRequest 请求json对象，请求示例：{"startTime":"2016-01-01","endTime":"2016-01-07","category":"daily"}
     * @return 返回平均消费数据
     */
    @RequestMapping("/averageSpend")
    @ResponseBody
    public RestApiResponse averageSpend(@RequestBody RestApiRequest restApiRequest)
    {
        RestApiResponse restApiResponse=new RestApiResponse();
        try {
            BarGraphResponseEntity data=averageSpendService.findSpendData(restApiRequest.getCategory(),restApiRequest.getStartTime(),restApiRequest.getEndTime());
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
}
