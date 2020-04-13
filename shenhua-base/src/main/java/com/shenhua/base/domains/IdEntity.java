package com.shenhua.base.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 统一定义id的entity基类.
 * Created by liuye on 2018/5/1.
 */
// JPA 基类的标识
@MappedSuperclass
@Data
public abstract class IdEntity extends PageEntity{

    public static final String DEL_FLAG_USE = "0";//0：正常 1：删除
    public static final String DEL_FLAG_STOP = "1";

    @Id
    @Column(name = "id")
    protected String id;
    /**
     * 备注
     */
    @Column(name = "remarks")
    protected String remarks;
    /**
     * 创建者
     */
    @Column(name = "create_by")
    protected String createBy;
    /**
     * 更新者
     */
    @Column(name = "update_by")
    protected String updateBy;
    /**
     * 删除标记（0：正常 1：删除）
     */
    @Column(name = "del_flag")
    protected String delFlag;
    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createDate;
    /**
     * 更新时间
     */
    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateDate;
}
