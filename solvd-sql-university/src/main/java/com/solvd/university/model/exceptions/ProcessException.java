package com.solvd.university.model.exceptions;

import java.sql.SQLException;

public class ProcessException extends RuntimeException{
    SQLException e;
    public ProcessException(String message, SQLException e){
        super(message);
        this.e = e;
    }
}
