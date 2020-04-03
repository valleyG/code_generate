package com.yss.util.service;

import com.yss.util.config.GenerateCodeConfig;
import com.yss.util.config.JsonProperty;
import com.yss.util.config.SettingConfig;
import com.yss.util.entity.TableColEntity;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author:zhuhongmin
 * @date:2020/4/3
 * @description:
 **/
public class CodeGenerateService {
    private static JdbcService jdbcService = new JdbcService();
    private Configuration freemarkerConfiguration = null;

    /**
     * 代码生成
     */
    public void generate() throws Exception {
        generateFile(JsonProperty.getGenerateCodeConfig().getTableName());

    }

    private void generateFile(String tableName) throws Exception {
        List<TableColEntity> tableCols = jdbcService.getTableCols(JsonProperty.getDatabaseConfig().getDatabaseName(), tableName);

        String property = System.getProperty("user.dir");
        String[] split = property.split("\\\\");
        StringBuffer codeDir = new StringBuffer();
        for (String s : split) {
            codeDir.append(s).append("\\");
        }

        File file = new File(codeDir.append("code\\").toString());
        if (file.exists()) {
            file.delete();
        } else {
            file.mkdirs();
        }

        //TODO 生成po文件
        Template template = getConfiguration().getTemplate("po.ftl");
        file = new File(codeDir.toString() + transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Po.java");
        Writer w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF-8"));

        Map<String, Object> modelData = new HashMap<>();
        modelData.put("tableCols", tableCols);
        modelData.put("poName", transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Po");
        modelData.put("packageName", JsonProperty.getGenerateCodeConfig().getPoPackageName());
        template.process(modelData, w);

        //TODO 生成mapper文件
        template = getConfiguration().getTemplate("mapper.ftl");
        file = new File(codeDir.toString() + transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Mapper.java");
        w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF-8"));
        modelData.clear();
        modelData.put("mapperName", transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Mapper");
        modelData.put("poName", transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Po");
        modelData.put("packageName", JsonProperty.getGenerateCodeConfig().getMapperPackageName());
        template.process(modelData, w);

        //TODO 生成mapper xml文件
        template = getConfiguration().getTemplate("mapperxml.ftl");
        file = new File(codeDir.toString() + transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Mapper.xml");
        w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF-8"));
        modelData.clear();
        modelData.put("tableCols", tableCols);
        modelData.put("mapperFullPath", JsonProperty.getGenerateCodeConfig().getMapperPackageName() + "." + transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Mapper");
        modelData.put("poFullPath", JsonProperty.getGenerateCodeConfig().getPoPackageName() + "." + transfer(JsonProperty.getGenerateCodeConfig().getTableName(), true) + "Po");
        modelData.put("tableName",JsonProperty.getGenerateCodeConfig().getTableName());
        modelData.put("id","#{id}");
        modelData.put("deleteFlag","#{deleteFlag}");
        modelData.put("updateUserId","#{updateUserId}");
        modelData.put("updateTime","#{updateTime}");
        template.process(modelData, w);

    }

    private Configuration getConfiguration() {
        if (null != freemarkerConfiguration) {
            return freemarkerConfiguration;
        } else {
            freemarkerConfiguration = new Configuration();
            freemarkerConfiguration.setClassForTemplateLoading(this.getClass(), "/templates");
            freemarkerConfiguration.setObjectWrapper(new DefaultObjectWrapper());
            freemarkerConfiguration.setEncoding(Locale.CHINA, "UTF-8");
        }
        return freemarkerConfiguration;
    }

    /**
     * @param source
     * @param tableNameFlag true:表名，false：列明
     * @return
     */
    public static String transfer(String source, Boolean tableNameFlag) {
        String[] s = source.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length; i++) {
            if (i == 0) {
                if (tableNameFlag) {
                    result.append(upperFirstChar(s[i]));
                } else {
                    result.append(s[i]);
                }
            } else {
                result.append(upperFirstChar(s[i]));
            }
        }
        return result.toString();
    }

    private static String upperFirstChar(String s) {
        String substring = s.substring(0, 1);
        return substring.toUpperCase() + s.substring(1);
    }

}
