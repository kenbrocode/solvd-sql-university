package com.solvd.university.patterns.decorator;

public class ExamSubjectDecorator implements SubjectPreaprator {

    private final SubjectPreaprator subjectPreaprator;

    public ExamSubjectDecorator(SubjectPreaprator subjectPreaprator) {
        this.subjectPreaprator = subjectPreaprator;
    }

    @Override
    public void createSubject() {
        subjectPreaprator.createSubject();
        System.out.println("Made subject for exam");
    }
}
