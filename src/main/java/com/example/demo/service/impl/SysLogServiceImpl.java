package com.example.demo.service.impl;

import com.example.demo.database.mysql.SysLogDAO;
import com.example.demo.entity.SysLog;
import com.example.demo.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    SysLogDAO sysLogDAO;

    public void saveLogger(SysLog sysLog) {
        sysLogDAO.save(sysLog);
    }

}
