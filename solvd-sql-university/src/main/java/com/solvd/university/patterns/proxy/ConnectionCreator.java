package com.solvd.university.patterns.proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator implements ConnectionPreparator {

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
