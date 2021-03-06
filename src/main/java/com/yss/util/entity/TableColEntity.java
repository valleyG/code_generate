package com.yss.util.entity;

import com.yss.util.service.CodeGenerateService;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author:zhuhongmin
 * @date:2020/4/3
 * @description:
 **/
@Accessors(chain = true)
@Data
public class TableColEntity {
    /**
     * 字段名称
     */
    private String colName;

    public String getJavaColName() {
        return CodeGenerateService.transfer(colName, false);
    }


    /**
     * #{xxxx},用于xml里面使用
     *
     * @return
     */
    public String getXmlJavaColValue() {
        return "#{" + CodeGenerateService.transfer(colName, false) + "}";
    }

    public String getBatchXmlJavaColValue() {
        return "#{entity." + CodeGenerateService.transfer(colName, false) + "}";
    }

    /**
     * 字段类型
     */
    private String colType;

    /**
     * 字段备注
     */
    private String comment;

    public String getComment() {
        return comment;
    }

    /**
     * 转换数据库对象到java对象
     *
     * @return
     */
    public String getJavaType() {
        String javaType = "String";
        switch (colType) {
            case "varchar":
                javaType = "String";
                break;
            case "bigint":
                javaType = "Long";
                break;
            case "char":
                javaType = "String";
                break;
            case "date":
                javaType = "Date";
                break;
            case "datetime":
                javaType = "Date";
                break;
            case "decimal":
                javaType = "BigDecimal";
                break;
            case "int":
                javaType = "Integer";
                break;
            case "timestamp":
                javaType = "Timestamp";
                break;
            case "tinyint":
                javaType = "Integer";
                break;
            default:
                javaType = "String";
        }
        return javaType;
    }

}
