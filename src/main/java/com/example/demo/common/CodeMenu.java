package com.example.demo.common;

import lombok.Data;

/**
 * @author hao
 * @date 2019-08-07 9:16
 * description
 */
public enum CodeMenu {

    SUCCESS("0", "成功！"),
    FAILURE("1", "失败！");

    private String code;
    private String msg;

    private CodeMenu(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
