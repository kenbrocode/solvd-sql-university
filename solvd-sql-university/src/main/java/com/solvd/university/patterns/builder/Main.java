package com.solvd.university.patterns.builder;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Faculty faculty = Faculty.builder()
                .title("KLU")
                .description("Interesting faculty")
                .dekan("Anna")
                .universityId(1L)
                .dateCreatingFaculty(Date.valueOf("1999-07-08"))
                .build();
    }
}
