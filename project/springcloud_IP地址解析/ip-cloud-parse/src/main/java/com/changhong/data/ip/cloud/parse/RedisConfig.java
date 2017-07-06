package com.changhong.data.ip.cloud.parse;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月28日
 */
@ConfigurationProperties(prefix = "redis",locations = "classpath:redis.properties")
public class RedisConfig {
    private String host;
    private int port;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
