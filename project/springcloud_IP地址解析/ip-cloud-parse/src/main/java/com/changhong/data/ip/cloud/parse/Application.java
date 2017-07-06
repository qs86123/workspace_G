package com.changhong.data.ip.cloud.parse;

import com.changhong.data.ip.cloud.parse.entity.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author xi1.chen@changhong.com
 * @Date 2016年10月26日
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(RedisConfig.class)
@EnableCaching
public class Application {
    @Autowired
    private RedisConfig redisConfig;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate)
    {
        return new RedisCacheManager(redisTemplate);
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory()
    {
        JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisConfig.getHost());
        jedisConnectionFactory.setPort(redisConfig.getPort());
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPassword(redisConfig.getPassword());
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,AddressEntity> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String,AddressEntity> redisTemplate=new RedisTemplate<String, AddressEntity>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
