package com.shenhua.base.utils;

import com.github.pagehelper.PageInfo;
import com.shenhua.base.domains.Result;
import com.shenhua.base.eums.ResultEnum;

/**
 * Created by liuye on 2018/8/7.
 */
public class ResultUtil {

    public static Result success(){
        return new Result(ResultEnum.SUCCESS);
    }
    public static Result success(Object o){
        Result result = new Result(ResultEnum.SUCCESS);
        result.setData(o);
        return result;
    }
    public static Result success(Long count,Object o){
        Result result = new Result(ResultEnum.SUCCESS);
        result.setCount(count==null?0:count);
        result.setData(o);
        return result;
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result createResult(PageInfo page){
        Result result = new Result();
        result.setData(page.getList());
        result.setCount(page.getTotal());
        return result;
    }
}
