package com.solvd.university.patterns.decorator;

public class KursovaSubjectDecorator implements SubjectPreaprator {
    private final SubjectPreaprator subjectPreaprator;

    public KursovaSubjectDecorator(SubjectPreaprator subjectPreaprator) {
        this.subjectPreaprator = subjectPreaprator;
    }

    @Override
    public void createSubject() {
        subjectPreaprator.createSubject();
        System.out.println("Mahe subject for kursova");
    }
}
