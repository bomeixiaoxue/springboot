package com.example.demo.rabbit;

import com.alibaba.fastjson.JSON;
import com.example.demo.database.redis.RedisService;
import com.example.demo.entity.SysLogMg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

import java.util.concurrent.CountDownLatch;

/**
 * 消息队列接收者
 */
@Slf4j
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    RedisService redisService;

    @Autowired
    MongoTemplate mongotemplate;

    public void receiveMessage(String message) {
        log.info("rabbitMq保存日志信息：{}", message);
        SysLogMg sysLog = JSON.parseObject(message, SysLogMg.class);
        mongotemplate.save(sysLog, "sys_log");

        redisService.lPush("sys_log", JSON.toJSONString(sysLog));
        log.info("redis的数据：{}", redisService.lRange("sys_log", 0, -1));

        latch.countDown();
    }

}