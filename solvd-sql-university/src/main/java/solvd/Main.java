package solvd;

import solvd.laba.domain.Department;
import solvd.laba.domain.Exam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.domain.Specialty;
import solvd.laba.domain.Subject;
import solvd.laba.services.DepartmentService;
import solvd.laba.services.SpecialtyService;
import solvd.laba.services.SubjectService;

import java.lang.invoke.MethodHandles;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DepartmentService departmentService = new DepartmentService();
        SpecialtyService specialtyService = new SpecialtyService();
        SubjectService subjectService = new SubjectService();

        //Creating a Department
        Department newDepartment = new Department();
        newDepartment.setName("Engineering");

        // Adding Specialties to the Department
        Specialty specialty1 = new Specialty();
        specialty1.setName("Computer Science");

        Specialty specialty2 = new Specialty();
        specialty2.setName("Mechanical Engineering");

        newDepartment.setSpecialities(Arrays.asList(specialty1, specialty2));

        // Adding Subjects to the Specialties
        Subject subject1 = new Subject();
        subject1.setName("Data Structures");

        Subject subject2 = new Subject();
        subject2.setName("Thermodynamics");

        specialty1.setSubjects(Arrays.asList(subject1));
        specialty2.setSubjects(Arrays.asList(subject2));




    }
}