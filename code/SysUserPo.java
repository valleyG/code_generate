package com.yss.test.po;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class SysUserPo{

private String id;


private String userCode;


private String legalCode;


private String legalFlag;


private String password;


private String userName;


private String userType;


private String userStatus;


private String lockStatus;


private String contactInfo;


private String email;


private String remark;


private String deleteFlag;


private String createUserId;


private String createTime;


private String updateUserId;


private String updateTime;

}