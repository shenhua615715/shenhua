package com.shenhua.base.entity.vo;

import com.shenhua.base.domains.IdEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
public class SysStaffVo extends IdEntity {

    private String staffId;

    private String staffName;

    private String departId;

    private String password;

    private String sex;

    private String email;

    private String serialNumber;

    private String psptId;

    private Date birthday;

    private String dimissionTag;

    private String managerStaffId;

    private String loginFlag;

    private String cityCode;

    private String eparchyCode;

    private String rsrvStr1;

    private String rsrvStr2;

    private String rsrvStr3;

    private String rsrvStr4;

    private String rsrvStr5;

    private Date pwdDate;

    private String phId;

    private String roleCodeA;

    private Date createTime;

}