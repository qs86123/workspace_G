package com.wt.demo.aop;

import com.wt.demo.thread.KafkaThread;
import com.wt.demo.thread.MyThread;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: wangtao
 * @Date:9:26 2017/7/25
 * @Email:tao8.wang@changhong.com
 */
@Aspect
@Component
public class MyAop {

    @AfterReturning(value = "execution(* com.wt.demo.controller.*.*(..))", returning = "rtn")
    public void record(JoinPoint joinPoint, Object rtn) {
        KafkaThread.executor.submit(new KafkaThread());
    }


}
