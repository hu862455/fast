package com.hushuai.fast.constants;

public enum ResultConstant {
    ERRPR_PASSWORD(1,"账号名或密码错误！","ERRPR_PASSWORD"),
    NULL(999,"请输入正确的SIGN！","NULL"),
    SUCCESS(0,"success","SUCCESS")
    ;

    Integer code;
    String msg;
    String sign;

    ResultConstant(Integer code, String msg, String sign){
        this.code = code;
        this.msg = msg;
        this.sign = sign;
    }

    public static ResultConstant getResultConstant(String sign){
        for (ResultConstant resultConstant : values()) {
            if (sign.equals(resultConstant.sign)) {
                return resultConstant;
            }
        }
        return NULL;

    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSign() {
        return sign;
    }

}
