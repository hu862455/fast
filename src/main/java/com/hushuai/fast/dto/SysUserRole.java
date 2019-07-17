package com.hushuai.fast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.SysUserRole")
@Data
public class SysUserRole {
    @ApiModelProperty(value="null")
    private Integer id;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    private Integer uid;

    /**
    * roleid
    */
    @ApiModelProperty(value="roleid")
    private Integer rid;
}