package com.solvd.university.model.exceptions;

import java.sql.SQLException;

public class CreateConnectionException extends Exception{
    SQLException e;
    public CreateConnectionException(String message, SQLException e){
        super(message);
        this.e = e;
    }
}
