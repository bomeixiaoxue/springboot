package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.RabbitConfig;
import com.example.demo.entity.SysLog;
import com.example.demo.service.ILoggerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hao
 * @date 2019-08-09 11:27
 * description 发送日志消息队列服务
 */
@Service
public class LoggerServiceImpl implements ILoggerService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void log(SysLog sysLog) {
        rabbitTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
    }
}
