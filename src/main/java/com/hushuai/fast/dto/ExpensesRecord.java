package com.hushuai.fast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.ExpensesRecord")
@Data
public class ExpensesRecord {
    @ApiModelProperty(value="null")
    private Integer id;

    /**
    * 消费者id
    */
    @ApiModelProperty(value="消费者id")
    private Integer memberId;

    /**
    * 本次消费金额
    */
    @ApiModelProperty(value="本次消费金额")
    private BigDecimal expensesAmount;

    /**
    * 消费详情表id
    */
    @ApiModelProperty(value="消费详情表id")
    private Integer expensesDetailsId;

    @ApiModelProperty(value="null")
    private Date createTime;
}