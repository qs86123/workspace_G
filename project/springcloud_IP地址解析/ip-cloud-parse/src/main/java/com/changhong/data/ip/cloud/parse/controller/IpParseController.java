package com.changhong.data.ip.cloud.parse.controller;


import com.changhong.data.ip.cloud.parse.entity.AddressEntity;
import com.changhong.data.ip.cloud.parse.service.TaobaoIpParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月25日
 */
@Controller
public class IpParseController {
    @Autowired
    private TaobaoIpParse taobaoIpParse;


    @RequestMapping("/ipToAddress")
    @ResponseBody
    public Object parseIp(String ip)
    {
        AddressEntity addressEntity=new AddressEntity();
        try {
            addressEntity= taobaoIpParse.parse(ip);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return addressEntity;
    }


}
