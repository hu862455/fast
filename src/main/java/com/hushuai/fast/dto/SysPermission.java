package com.hushuai.fast.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com.hushuai.fast.dto.SysPermission")
@Data
public class SysPermission {
    @ApiModelProperty(value="null")
    private Integer id;

    @ApiModelProperty(value="null")
    private String permissionname;

    @ApiModelProperty(value="null")
    private String descript;

    @ApiModelProperty(value="null")
    private String url;

    @ApiModelProperty(value="null")
    private Integer pid;
}