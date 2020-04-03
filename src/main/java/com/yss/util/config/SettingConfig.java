package com.yss.util.config;

import lombok.Data;

/**
*
* @author:zhuhongmin
* @date:2020/4/3
* @description:
**/
@Data
public class SettingConfig {
    private  DatabaseConfig databaseConfig;
    private  GenerateCodeConfig generateCodeConfig;
}
