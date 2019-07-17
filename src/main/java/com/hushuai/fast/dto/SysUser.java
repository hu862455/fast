package com.hushuai.fast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.SysUser")
@Data
public class SysUser {
    @ApiModelProperty(value="null")
    private Integer id;

    @ApiModelProperty(value="null")
    private String username;

    @ApiModelProperty(value="null")
    private String realname;

    @ApiModelProperty(value="null")
    private String password;
}