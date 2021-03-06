<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        
<mapper namespace="${mapperFullPath}">

    <resultMap id="BaseResultMap" type="${poFullPath}" >
    	<#list tableCols as p>
 			<result column="${p.colName}" property="${p.javaColName}" />
		</#list>
    </resultMap>

    <sql id="Base_Column_List">
    	<#list tableCols as p>
    	${p.colName}<#if p_has_next>,<#else> </#if>
		</#list>
    </sql>

    <insert id="insert"  keyProperty="id" parameterType="${poFullPath}">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        	<#list tableCols as p>
    	       <#if p.javaType?index_of("String")!=-1>
                <if test ='null != ${p.javaColName} and  "" != ${p.javaColName}'>
                     ${p.colName}<#if p_has_next>,<#else> </#if>
                </if>
                <#else> 
                <if test ='null != ${p.javaColName}'>
                     ${p.colName} <#if p_has_next>,<#else> </#if>
                </if>
                </#if>
			</#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">

        	 <#list tableCols as p>
              <#if p.javaType?index_of("String")!=-1>
                <if test ='null != ${p.javaColName} and "" != ${p.javaColName}'>
                     ${p.xmlJavaColValue}<#if p_has_next>,<#else> </#if>
                </if>
                <#else> 
                <if test ='null != ${p.javaColName}'>
                     ${p.xmlJavaColValue}<#if p_has_next>,<#else> </#if>
                </if>
                </#if>
			</#list>
        </trim>
    </insert>
    
    <update id="deleteById" parameterType="${poFullPath}">
    	UPDATE ${tableName}
    	SET delete_flag = ${deleteFlag}, 
    		update_user_id = ${updateUserId},
    	 	update_time = ${updateTime}
	    WHERE id = ${id}
    </update>
    
    <update id="update" parameterType="${poFullPath}">
        UPDATE ${tableName}
        <set>
        	<#list tableCols as p>
              <#if p.javaType?index_of("String")!=-1>
                <if test ='null != ${p.javaColName} and "" != ${p.javaColName}'>
                    ${p.colName} =${p.xmlJavaColValue}<#if p_has_next>,<#else> </#if>
                </if>
                <#else> 
                <if test ='null != ${p.javaColName}'>
                    ${p.colName} =${p.xmlJavaColValue}<#if p_has_next>,<#else> </#if>
                </if>
                </#if>
			</#list>
        </set>
        WHERE id = ${id}
    </update>

    <select id="selectById" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableName}
        WHERE id = ${id}
    </select>

    <select id="listByCondition" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableName}
        <where>
            <if test="extraSpliceSql != null and  extraSpliceSql != ''">
                and ${extraSpliceSql}
            </if>
            <if test="spliceSql != null and spliceSql != ''">
                and ${spliceSql}
            </if>
        </where>
    </select>


    <select id="selectByEntity" resultMap="BaseResultMap" parameterType="${poFullPath}">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableName}
        <where>
         	<#list tableCols as p>
        	     <#if p.javaType?index_of("String")!=-1>
                <if test ='null != ${p.javaColName} and "" != ${p.javaColName}'>
                    and ${p.colName} =${p.xmlJavaColValue}
                </if>
                <#else> 
                <if test ='null != ${p.javaColName}'>
                    and ${p.colName} =${p.xmlJavaColValue}
                </if>
                </#if>
			</#list>
        </where>
    </select>
    <insert id="batchInsert" parameterType="list">
        INSERT INTO ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <include refid="Base_Column_List"></include>
        </trim>
        values
        <foreach collection="list" item="entity"  separator=",">
               ( <#list tableCols as p>
                ${p.batchXmlJavaColValue}<#if p_has_next>,<#else> </#if>
            </#list>)
        </foreach>
    </insert>
    
  <select id="listByEntity" resultMap="BaseResultMap" parameterType="${poFullPath}">
        SELECT <include refid="Base_Column_List" />
        FROM ${tableName}
        <where>
            <#list tableCols as p>
                 <#if p.javaType?index_of("String")!=-1>
                <if test ='null != ${p.javaColName} and "" != ${p.javaColName}'>
                    and ${p.colName} =${p.xmlJavaColValue}
                </if>
                <#else> 
                <if test ='null != ${p.javaColName}'>      
                    and ${p.colName} =${p.xmlJavaColValue}
                </if>
                </#if>
            </#list>
        </where>
    </select>
    <select id="exist" resultType="integer">
    	select 1
    	FROM ${tableName} 
    	<where>
            <#list tableCols as p>
                 <#if p.javaType?index_of("String")!=-1>
                <if test ='null != ${p.javaColName} and "" != ${p.javaColName}'>
                    and ${p.colName} =${p.xmlJavaColValue}
                </if>
                <#else> 
                <if test ='null != ${p.javaColName}'>      
                    and ${p.colName} =${p.xmlJavaColValue}
                </if>
                </#if>
            </#list>
        </where>
        limit 1
    </select>
</mapper>