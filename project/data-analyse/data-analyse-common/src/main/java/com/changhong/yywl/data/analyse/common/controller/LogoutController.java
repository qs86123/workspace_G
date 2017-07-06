package com.changhong.yywl.data.analyse.common.controller;

import com.changhong.yywl.data.analyse.common.RestApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 16-9-18.
 */
@Controller
public class LogoutController {
    @RequestMapping("/logout")
    @ResponseBody
    public RestApiResponse logout(HttpSession httpSession)
    {
        RestApiResponse restApiResponse=new RestApiResponse();
        restApiResponse.setMsg("还未登录");
        if(httpSession!=null&&httpSession.getAttribute("right")!=null)
        {
            httpSession.removeAttribute("right");
            restApiResponse.setCode(200);
            restApiResponse.setMsg("注销成功");
        }
        return restApiResponse;
    }
}
