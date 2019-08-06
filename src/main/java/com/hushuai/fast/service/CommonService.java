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

    public String sexInteger2String(Integer sexInteger) {
        String sexString = "未知";
        switch (sexInteger) {

            case 1:
                sexString = "男";
                break;
            case 0:
                sexString = "女";

            default:
                sexString = "未知";
                break;
        }
        return sexString;
    }
}
