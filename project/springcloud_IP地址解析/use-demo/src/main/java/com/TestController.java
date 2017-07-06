package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月26日
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ipp")
    @ResponseBody
    public String test(String ip)
    {
        return restTemplate.getForObject("http://cloud-ip-parse-service/ipToAddress?ip="+ip,String.class);
    }
}
