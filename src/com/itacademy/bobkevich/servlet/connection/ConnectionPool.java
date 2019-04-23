package com.itacademy.bobkevich.servlet.connection;

import com.itacademy.bobkevich.servlet.util.PropertyManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionPool {

    private static DataSource DATA_SOURCE;

    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
        PoolProperties poolProperties1 = new PoolProperties();
        poolProperties1.setDriverClassName(PropertyManager.get("db.driver"));
        poolProperties1.setUrl(PropertyManager.get("db.url"));
        poolProperties1.setUsername(PropertyManager.get("db.user"));
        poolProperties1.setPassword(PropertyManager.get("db.password"));
        poolProperties1.setMaxActive(Integer.parseInt(PropertyManager.get("db.pool.size")));
        DATA_SOURCE = new DataSource(poolProperties1);
    }

    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}