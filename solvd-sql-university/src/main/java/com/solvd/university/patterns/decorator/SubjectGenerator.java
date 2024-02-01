package com.solvd.university.patterns.decorator;

public class SubjectGenerator {
    public static void createSubject(boolean exam, boolean kursova) {
        SubjectPreaprator subjectPreaprator = new RegularSubjectDecorator();
        if (exam) {
            subjectPreaprator = new ExamSubjectDecorator(subjectPreaprator);
        }
        if (kursova) {
            subjectPreaprator = new KursovaSubjectDecorator(subjectPreaprator);
        }

        subjectPreaprator.createSubject();
    }
}
