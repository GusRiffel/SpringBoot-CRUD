package com.example.restservice.service;

import com.example.restservice.domain.Student;
import com.example.restservice.repository.StudentRepository;
import com.example.restservice.requests.StudentPostRequestBody;
import com.example.restservice.requests.StudentPutRequestBody;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    final
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> listAll() {
       return studentRepository.findAll();
    }

    @Cacheable(value = "students")
    public List<Student> cacheStudentsList() {
        return listAll();
    }

    public Student findByIdOrThrowBadRequestException(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student not Found"));
    }

    public Student save(StudentPostRequestBody student) {
        Student student1 = new Student();
        student1.setName(student.getName());
        student1.setCourse(student.getCourse());
        student1.setYear(student.getYear());
        return studentRepository.save(student1);
    }

    public void delete(Long id) {
        studentRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void update(StudentPutRequestBody student) {
        findByIdOrThrowBadRequestException(student.getId());
        Student student1 = new Student();
        student1.setId(student.getId());
        student1.setName(student.getName());
        student1.setCourse(student.getCourse());
        student1.setYear(student.getYear());
        studentRepository.save(student1);
    }
}
