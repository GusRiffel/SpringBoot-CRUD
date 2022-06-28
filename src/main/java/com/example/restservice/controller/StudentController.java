package com.example.restservice.controller;

import com.example.restservice.domain.Student;
import com.example.restservice.requests.StudentPostRequestBody;
import com.example.restservice.requests.StudentPutRequestBody;
import com.example.restservice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    final
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> listAll() {
        return new ResponseEntity<>(studentService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/cacheList")
    public ResponseEntity<List<Student>> cacheStudentsList() {
        return new ResponseEntity<>(studentService.cacheStudentsList(), HttpStatus.OK);
    }

    @PostMapping("/newStudent")
    public ResponseEntity<Student> save(@RequestBody StudentPostRequestBody student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findByName(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody StudentPutRequestBody student) {
        studentService.update(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
