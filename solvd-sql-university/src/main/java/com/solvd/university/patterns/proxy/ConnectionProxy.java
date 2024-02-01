package com.solvd.university.patterns.proxy;

import java.sql.Connection;

public class ConnectionProxy implements ConnectionPreparator {
    private final ConnectionPreparator connectionPreparator;

    public ConnectionProxy() {
        this.connectionPreparator = new ConnectionCreator();
    }

    @Override
    public Connection getConnection() {
        return connectionPreparator.getConnection();
    }
}
