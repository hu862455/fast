package com.hushuai.fast.controller;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/8/5
 * @Interface: commonController
 * @Description:
 */
public class CommonController {

    /**
     * @Description: 用于将中文的性别转换成实体的属性
     * @params: [sexName]
     * @return: java.lang.Integer
     * @exception:
     * @methodName: sexName2Integer
     * @updateDate: 2019/8/5 10:39
     * @updateAuthor: shuaihu2
     */
    public Integer sexName2Integer(String sexName) {
        Integer sexInteger = 2;
        switch (sexName) {

            case "男":
                sexInteger = 1;
                break;
            case "女":
                sexInteger = 0;

            default:
                sexInteger = 2;
                break;
        }
        return sexInteger;
    }

}
