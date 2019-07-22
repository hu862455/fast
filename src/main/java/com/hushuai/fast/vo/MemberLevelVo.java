package com.hushuai.fast.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/22
 * @Interface: MemberLevelVo
 * @Description:
 */
@Data
public class MemberLevelVo extends PageVo {

    private Integer id;
    private String levelName;
    private Double count;
    private BigDecimal limit;

}
