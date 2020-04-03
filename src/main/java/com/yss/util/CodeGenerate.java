package com.yss.util;

import com.yss.util.service.CodeGenerateService;

/**
 * @author:zhuhongmin
 * @date:2020/4/3
 * @description: 代码生成器
 **/
public class CodeGenerate {
    public static void main(String[] args) {
        CodeGenerateService service = new CodeGenerateService();
        try {
            service.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
