package com.shenhua.base.utils;

import com.github.pagehelper.PageHelper;
import com.shenhua.base.domains.IdEntity;
import com.shenhua.base.domains.PageEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuye
 * @date 2018/10/3 0:39
 * @Description: ${todo}
 */
public class PageUtil<T> {

    private com.github.pagehelper.Page page;

    public PageUtil(HttpServletRequest request){
        //mysql写法
        Integer pageNum = request.getParameter("page") != null?Integer.parseInt(request.getParameter("page")):1;
        Integer pageSize = request.getParameter("pagesize") != null?Integer.parseInt(request.getParameter("pagesize")):10;
        this.page =  PageHelper.startPage(pageNum, pageSize, true);
    }

    public PageUtil(T bean){
        //mysql写法
        Integer pageNum = 1;
        Integer pageSize = 10;
        if(bean instanceof PageEntity){
            PageEntity pageEntity = (PageEntity) bean;
            pageNum = pageEntity.getPage();
            pageSize = pageEntity.getPageSize();
        }
        this.page =  PageHelper.startPage(pageNum, pageSize, true);
    }

    public PageUtil() {

    }

    public com.github.pagehelper.Page get(){
        return this.page;
    }
}
