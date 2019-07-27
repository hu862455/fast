package com.hushuai.fast.vo;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/20
 * @Interface: PageVo
 * @Description: 所有需要分页的vo都需要继承这个实体
 */
public class PageVo {

    /** 页码大小 */
    private Integer pageSize;
    /** 页码数 */
    private Integer pageNumber;
    /** 排序字段 */
    private String sort;
    /** 排序方式 默认为desc */
    private String sortOrder = "desc";

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageVo pageVo = (PageVo) o;
        return Objects.equals(pageSize, pageVo.pageSize) &&
                Objects.equals(pageNumber, pageVo.pageNumber) &&
                Objects.equals(sort, pageVo.sort) &&
                Objects.equals(sortOrder, pageVo.sortOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageSize, pageNumber, sort, sortOrder);
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }
}
