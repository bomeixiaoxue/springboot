package com.example.demo.common;

import lombok.Data;

/**
 * @author hao
 * @date 2019-08-07 9:13
 * description
 */
@Data
public class ResponseVo {

    private String code;
    private String msg;
    private Object data;
    private String count;

    public ResponseVo(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
