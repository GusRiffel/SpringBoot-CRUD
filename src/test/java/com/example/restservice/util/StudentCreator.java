package com.example.restservice.util;

import com.example.restservice.domain.Student;

public class StudentCreator {

    public static Student createStudentToBeSaved() {
        Student student1 = new Student();
        student1.setName("Gus");
        student1.setCourse("Computing");
        student1.setYear(3);
        return student1;
    }

    public static Student createValidStudent() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Gus");
        student1.setCourse("Computing");
        student1.setYear(3);
        return student1;
    }

    public static Student createValidUpdatedAnime() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("George");
        student1.setCourse("Law");
        student1.setYear(1);
        return student1;
    }
}
