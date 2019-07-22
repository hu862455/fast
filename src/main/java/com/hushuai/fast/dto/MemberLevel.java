package com.hushuai.fast.dto;

import com.hushuai.fast.vo.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.MemberLevel")
@Data
public class MemberLevel{
    @ApiModelProperty(value="null")
    private Integer id;

    @ApiModelProperty(value="null")
    private String levelName;

    /**
    * 折扣 
    */
    @ApiModelProperty(value="折扣 ")
    private Double count;

    /**
    * 充值限额
    */
    @ApiModelProperty(value="充值限额")
    private BigDecimal limit;
}