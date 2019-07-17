package com.hushuai.fast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.Member")
@Data
public class Member {
    @ApiModelProperty(value="null")
    private Integer id;

    /**
    * 会员类型id 对应会员类型表的主键
    */
    @ApiModelProperty(value="会员类型id 对应会员类型表的主键")
    private Integer memberLevelId;

    @ApiModelProperty(value="null")
    private String name;

    @ApiModelProperty(value="null")
    private String address;

    @ApiModelProperty(value="null")
    private String telephone;

    /**
    * 性别 1：男 0：女
    */
    @ApiModelProperty(value="性别 1：男 0：女")
    private Integer sex;

    @ApiModelProperty(value="null")
    private BigDecimal account;

    @ApiModelProperty(value="null")
    private Date createTime;

    @ApiModelProperty(value="null")
    private Integer createUser;

    @ApiModelProperty(value="null")
    private Integer updateUser;

    @ApiModelProperty(value="null")
    private Date updateTime;
}