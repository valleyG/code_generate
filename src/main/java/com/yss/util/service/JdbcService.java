package com.yss.util.service;


import com.yss.util.config.DatabaseConfig;
import com.yss.util.config.JsonProperty;
import com.yss.util.entity.TableColEntity;
import com.yss.util.enu.DatabaseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zhuhongmin
 * @date:2020/4/3
 * @description:
 **/
public class JdbcService {
    private static Connection connection;

    public Connection getConnection() throws Exception {
        if (null == connection) {
            DatabaseConfig databaseConfig = JsonProperty.getDatabaseConfig();
            if (DatabaseType.MYSQL.getDatabaseType().equals(databaseConfig.getDataType())) {
                Class.forName("com.mysql.jdbc.Driver");
            } else {

            }
            connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUserName(), databaseConfig.getPassword());
            return connection;
        } else {
            return connection;
        }
    }


    public List<TableColEntity> getTableCols(String databaseName, String tableName) throws Exception {
        List<TableColEntity> result = new ArrayList<>();
        PreparedStatement statement = getConnection().prepareStatement("SELECT column_name as cloName,data_type as type FROM information_schema.columns WHERE table_schema= ? AND table_name=?");
        statement.setString(1, databaseName);
        statement.setString(2, tableName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            TableColEntity colEntity = new TableColEntity();
            colEntity.setColName(resultSet.getString("cloName"));
            colEntity.setColType(resultSet.getString("type"));
            result.add(colEntity);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return result;
    }

}
