package com.example.demo.database.mysql;

import com.example.demo.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogDAO extends JpaRepository<SysLog, Long> {
}
