package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author hao
 * @date 2019-08-06 15:24
 * description
 */
@Data
@Entity
@Table(name="user_1")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private Integer age;

}
