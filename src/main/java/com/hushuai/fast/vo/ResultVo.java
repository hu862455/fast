package com.hushuai.fast.vo;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/18
 * @Interface: ResultVo
 * @Description:
 */
public class ResultVo {

    /** code码 0表示正常 1表示失败 */
    private Integer code;
    /** 消息 */
    private String msg;
    /** 返回体 */
    private Object date;

    public ResultVo() {
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVo(Integer code, String msg, Object date) {
        this.code = code;
        this.msg = msg;
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
