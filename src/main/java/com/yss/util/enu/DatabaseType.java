package com.yss.util.enu;

public enum DatabaseType {
    ORACLE("oracle","oracle数据库"),
    MYSQL("mysql","mysql数据库");
    private String databaseType;
    private String dateBaseName;

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDateBaseName() {
        return dateBaseName;
    }

    public void setDateBaseName(String dateBaseName) {
        this.dateBaseName = dateBaseName;
    }

    DatabaseType(String databaseType, String dateBaseName) {
        this.databaseType = databaseType;
        this.dateBaseName = dateBaseName;
    }
}
