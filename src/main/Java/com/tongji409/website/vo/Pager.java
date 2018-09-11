package com.tongji409.website.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijiechu
 * @create on 2018/9/12
 * @description 分页器
 */
public class Pager<T> {

    // 总数
    private Integer totalCount;

    // 第几页
    private Integer pageNumber;

    // 每页多少个
    private Integer pageCount;

    // 总共多少页
    private Integer totalPage;

    // 开始页
    private Integer startPage;

    // 结束页
    private Integer endPage;

    // 开始取数据的索引
    private Integer startIndex;

    // 存放的数据
    private List<T> data;

    /**
     * 根据传入的三个参数来计算成员变量
     * @param pageNumber 默认从第一页开始
     * @param pageCount
     * @param totalCount
     */
    public Pager(Integer pageNumber, Integer pageCount, Integer totalCount) {
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;
        this.pageCount = pageCount;
        this.data = new ArrayList<>();

        this.totalPage = totalCount / pageCount;
        if(totalCount % pageCount != 0) {
            this.totalPage += 1;
        }

        this.startPage = 1;
        this.endPage = 5;

        if(this.totalPage <= 5) {
            this.endPage =  this.totalPage;
        } else {
            this.startPage = pageNumber - 2;
            this.endPage = pageNumber + 2;

            if(this.startPage < 0) {
                this.startPage = 1;
                this.endPage = 5;
            }

            if(this.endPage > totalPage) {
                this.startPage = this.totalPage - 5;
                this.endPage = totalPage;
            }
        }

        // TODO: 缜密编程 判断页数是否在合法范围内
        this.startIndex = (pageNumber-1) * pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
