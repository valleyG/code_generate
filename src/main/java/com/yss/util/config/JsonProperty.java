package com.yss.util.config;

import com.google.gson.Gson;

import java.io.*;

/**
 * @author:zhuhongmin
 * @date:2020/4/3
 * @description: 程序启动参数
 **/
public class JsonProperty {
    private static  SettingConfig settingConfig;

    static {
        try {
            String property = System.getProperty("user.dir");
            InputStream inputStream = new FileInputStream(new File(property+"\\setting.json"));
            //InputStream inputStream = JsonProperty.class.getResourceAsStream("/property.json");
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int count = -1;
            while ((count = inputStream.read(data, 0, 1024)) != -1) {
                outStream.write(data, 0, count);
            }
            String jsonProperty = new String(outStream.toByteArray(), "ISO-8859-1");
            Gson gson = new Gson();
            settingConfig = gson.fromJson(jsonProperty, SettingConfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private JsonProperty() {
    }

    public static DatabaseConfig getDatabaseConfig() {
        return settingConfig.getDatabaseConfig();
    }

    public static GenerateCodeConfig getGenerateCodeConfig() {
        return settingConfig.getGenerateCodeConfig();
    }
}
