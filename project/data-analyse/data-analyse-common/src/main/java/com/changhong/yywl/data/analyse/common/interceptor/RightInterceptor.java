package com.changhong.yywl.data.analyse.common.interceptor;


import com.changhong.yyhl.data.encrypt.SHAUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 验证用户权限拦截器
 * Created by Administrator on 16-9-13.
 */
public class RightInterceptor implements HandlerInterceptor {

    private Set<String> timeSet=new HashSet<String>();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            //session中right字段不为空便不用验证
            if(httpServletRequest.getSession().getAttribute("right")!=null)
            {
                return true;
            }
            String time=httpServletRequest.getParameter("t");
            String access=httpServletRequest.getParameter("ac");
            String count=httpServletRequest.getParameter("cnt");
            //请求字段为空直接退出
            if(time==null||access==null||count==null)
            {
                return false;
            }
            Date date=new Date(Long.parseLong(time));
            if(canUse(date.getTime(),count))
            {
                String cipher = SHAUtils.digestDefaultKey(time,count);
                if(access.equals(cipher))
                {
                    httpServletRequest.getSession().setAttribute("right",true);
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }catch (Exception e)
        {
            e.getMessage();
            return false;
        }
    }

    /**
     * 验证当前时间戳是否能用。
     * 判断规则为：1，与当前服务器时间相差30分钟不可用，包括小于或大于
     *            2，已经使用过的不能使用,仅针对当前启动这一次内存的数据
     *            3，其它情况该时间戳能够使用
     * @param timestamp
     * @return
     */
    private boolean canUse(long timestamp,String count)
    {
        long currentTime=System.currentTimeMillis();
        List<String> list=new ArrayList<String>();
        for (String str:timeSet)
        {
            long time=Long.parseLong(str.split(",")[0]);
            if((currentTime-time)>=30*60*1000l)
            {
                list.add(str);
            }
        }
        timeSet.removeAll(list);
        if(Math.abs(currentTime-timestamp)>=30*60*1000l)
        {
            return false;
        }
        if(!timeSet.add(timestamp+","+count))
        {
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
