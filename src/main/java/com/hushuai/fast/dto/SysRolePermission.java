package com.hushuai.fast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.SysRolePermission")
@Data
public class SysRolePermission {
    @ApiModelProperty(value="null")
    private Integer id;

    @ApiModelProperty(value="null")
    private Integer rid;

    @ApiModelProperty(value="null")
    private Integer pid;
}