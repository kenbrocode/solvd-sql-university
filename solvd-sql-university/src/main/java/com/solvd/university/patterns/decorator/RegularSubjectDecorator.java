package com.solvd.university.patterns.decorator;

public class RegularSubjectDecorator implements SubjectPreaprator {

    @Override
    public void createSubject() {
        System.out.println("regular subject");
    }
}
