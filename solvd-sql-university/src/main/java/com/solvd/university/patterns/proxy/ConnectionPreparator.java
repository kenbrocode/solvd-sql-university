package com.solvd.university.patterns.proxy;

import java.sql.Connection;

public interface ConnectionPreparator {
    Connection getConnection();
}
