package com.example.demo.common;

/**
 * @author hao
 * @date 2019-08-07 11:28
 * description
 */
public class StringUtil {

    /**
     * 判断字符串非空（2个条件）：
     *     1.引用非空-null
     *     2.非空字符串-" "
     * @param str
     * @return
     */
    public static boolean hasLength(String str){
        return str!=null && !"".equals(str.trim());
    }

    /**
     * 判断字符串为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return !hasLength(str);
    }

    /**
     * 判断字符串非空
     * @param str
     * @return
     */
    public static boolean isNoBlank(String str){
        return hasLength(str);
    }

}
