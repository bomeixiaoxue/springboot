package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hao
 * @date 2019-08-06 14:31
 * description
 */
//@Controller
public class MyController {

//    @RequestMapping("hello")
    public String hello() {
        return "/hello";
    }

//    @RequestMapping(value = "getHello", method = RequestMethod.GET)
//    @ResponseBody
    public String getHello() {
        return "hello";
    }

}
