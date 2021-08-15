package com.whpowernode.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目 code
 * @描述 TODO 分页的实体类
 * @时间 2021-08-10 16:04
 **/
public class PageInfo<T> {
    /*当前页*/
    private Long pageNum;
    /*每页显示条数*/
    private Long pageSize;
    /*总条数*/
    private Long totalCount;
    /*总页数=Math.ceil((totalCount*1.0/pageSize))*/
    private Long totalPage;
    /*存放当前分页数*/
    private List<T> data=new ArrayList<>();

    public PageInfo() {
    }
    public PageInfo(Long pageNum, Long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    public Long getPageNum() {
        return pageNum;
    }
    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }
    public Long getPageSize() {
        return pageSize;
    }
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
    public Long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        //因为总条数是从数据库里面查询出来了，所以当设置总条数时 就应该去计算总页数
        this.totalPage=Double.valueOf(Math.ceil(this.totalCount*1.0/this.pageSize)).longValue();
    }
    public Long getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
}
