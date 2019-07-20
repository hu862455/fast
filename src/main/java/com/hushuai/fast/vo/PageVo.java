package com.hushuai.fast.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/20
 * @Interface: PageVo
 * @Description: 所有需要分页的vo都需要继承这个实体
 */
@Data
public class PageVo {

    /** 页码大小 */
    private Integer rows;
    /** 页码数 */
    private Integer page;
    /** 排序字段 */
    private String sort;
    /** 排序方式 默认为desc */
    private String sortOrder = "desc";

}
