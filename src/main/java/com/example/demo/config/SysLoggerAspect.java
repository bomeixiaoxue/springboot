package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.StringUtil;
import com.example.demo.entity.SysLog;
import com.example.demo.service.ILoggerService;
import com.example.demo.util.HttpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLoggerAspect {

    @Autowired
    private ILoggerService iLoggerService;

    @Pointcut("@annotation(com.example.demo.config.SysLogger)")
    public void loggerPointCut() {

    }

    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (sysLogger != null) {
            //注解上的描述
            sysLog.setOperation(sysLogger.value());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = "";
        for (Object o : args) {
            params += JSON.toJSONString(o);
        }
        if (StringUtil.isNoBlank(params)) {
            sysLog.setParams(params);
        }
        //设置IP地址
        sysLog.setIp(HttpUtils.getIpAddress());
        //用户名
        String username = "林广豪";
        if (StringUtil.isNoBlank(username)) {
            sysLog.setUsername(username);
        }
        sysLog.setCreateDate(new Date());
        //保存系统日志
        iLoggerService.log(sysLog);
    }

}

