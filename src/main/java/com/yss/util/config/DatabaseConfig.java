package com.yss.util.config;

import lombok.Data;

/**
*
* @author:zhuhongmin
* @date:2020/4/3
* @description: 数据库配置
**/
@Data
public class DatabaseConfig {
    /**
     * 数据库类型，mysql、oracle
     */
    private String dataType;
    /**
     * jdbc连接串
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String userName;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库名称
     */
    private String databaseName;

}
