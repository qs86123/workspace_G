package com.changhong.data.ip.parse.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月26日
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServer.class,args);
    }
}
