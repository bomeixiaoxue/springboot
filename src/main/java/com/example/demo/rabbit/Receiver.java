package com.example.demo.rabbit;

import com.alibaba.fastjson.JSON;
import com.example.demo.database.redis.RedisService;
import com.example.demo.entity.SysLogMg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

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
        try {
            log.info("rabbitMq保存日志信息：{}", message);
            SysLogMg sysLog = JSON.parseObject(message, SysLogMg.class);
            mongotemplate.save(sysLog, "sys_log");
        }catch (Exception e) {
            log.error("rabbitMq保存日志信息出现异常：{}", message);
        }finally {
            latch.countDown();
        }
    }

}