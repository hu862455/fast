package com.hushuai.fast.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/8/6
 * @Interface: CommonService
 * @Description:
 */
public class CommonService {

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
