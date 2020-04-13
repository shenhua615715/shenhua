package com.shenhua.base.domains;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysDictVo implements Serializable {
    private String type;
    private String value;
    private String label;
    private String parentId;
    private String eparchyCode;
    private static final long serialVersionUID = 1L;
}