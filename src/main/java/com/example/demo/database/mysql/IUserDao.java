package com.example.demo.database.mysql;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author hao
 * @date 2019-08-06 15:22
 * description
 */
public interface IUserDao extends JpaRepository<User, String> {

}
