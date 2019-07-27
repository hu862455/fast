package com.hushuai.fast.vo;

import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/27
 * @Interface: MemberVo
 * @Description:
 */
@Data
public class MemberVo extends PageVo{
    private Integer id;
    private Integer memberLevelId;
    private String name;
    private String address;
    private String telephone;
    private Integer sex;
    private BigDecimal account;
    private BigDecimal totalAccount;
    private String password;
    private String levelName;
    private Double count;
    private BigDecimal recharge;
}
