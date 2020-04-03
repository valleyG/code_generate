package com.yss.util.config;

import lombok.Data;

/**
*
* @author:zhuhongmin
* @date:2020/4/3
* @description: 生成代码配置文件
**/
@Data
public class GenerateCodeConfig {
    /**
     * po的包路径
     */
    private String poPackageName;
    /**
     * mapper的包路径
     */
    private String mapperPackageName;

    /**
     * 表名
     */
    private String tableName;

}
