package com.solvd.university.patterns.decorator;

public class SubjectDecorator implements SubjectPreaprator {
    private final SubjectPreaprator subjectPreaprator;

    public SubjectDecorator(SubjectPreaprator subjectPreaprator) {
        this.subjectPreaprator = subjectPreaprator;
    }

    @Override
    public void createSubject() {
        subjectPreaprator.createSubject();
        System.out.println("Mahe subject for kursova");
    }
}
