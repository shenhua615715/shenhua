package com.shenhua.base.eums;
/**
 * Created by liuye on 2019/3/7.
 */
public enum ConstantEnum {
    /************************通用变量开始******************************/
    PROVINCE_HE("河北省","18"),
    DEL_FLAG("可用数据","0"),
    DEL_FLAG_OK("已删除数据","1"),
    YES("是","1"),//是否默认、是否可用(0：否 1：是）
    NO("否","0"),
    STATUS_YES("正常","0"),
    STATUS_NO("停用","1"),
    SHOW_NO("隐藏","0"),
    SHOW_YES("展示","1"),
    DEFAULT_STAFF_ID("超级管理员","SUPERUSR"),
    SEQUENCT_SYS_INTF_LOG_ID("日志表ID","SEQ_SYS_INTF_LOG_ID"),
    DISHI_MANAGER("地市管理员","20001000"),
    YIZHAN_MANAGER("驿站管理员","20001001"),
    PUTONG_YUANGONG("普通员工","20001002")

    /************************通用变量结束******************************/



    /************************post模块变量开始******************************/



    /************************post模块变量结束******************************/




    /************************act模块变量开始******************************/



    /************************act模块变量结束******************************/
    ;

    private String name;
    private String val;

    ConstantEnum(String val) {
        this.val = val;
    }

    ConstantEnum(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
