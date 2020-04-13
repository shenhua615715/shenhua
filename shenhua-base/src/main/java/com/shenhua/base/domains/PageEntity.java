package com.shenhua.base.domains;


import javax.persistence.Transient;

/**
 * @Description:
 * @Author: liuye
 * @Date: 2019/11/16 22:15
 */
public class PageEntity {
    @Transient
    private Integer page;
    @Transient
    private Integer pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
