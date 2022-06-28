package com.example.restservice.repository;

import com.example.restservice.domain.Student;
import com.example.restservice.util.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Student Repository Test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Save student")
    void save_PersistStudent_WhenSuccessful() {
        Student studentToBeSaved = StudentCreator.createStudentToBeSaved();

        Student savedStudent = this.studentRepository.save(studentToBeSaved);

        Assertions.assertThat(savedStudent).isNotNull();

        Assertions.assertThat(savedStudent.getId()).isNotNull();

        Assertions.assertThat(savedStudent.getName()).isEqualTo(studentToBeSaved.getName());
    }

    @Test
    @DisplayName("Update student")
    void save_UpdateStudent_WhenSuccessful() {
        Student studentToBeSaved = StudentCreator.createStudentToBeSaved();

        Student savedStudent = this.studentRepository.save(studentToBeSaved);

        savedStudent.setName("Victor");

        Student updatedStudent = this.studentRepository.save(savedStudent);

        Assertions.assertThat(updatedStudent).isNotNull();

        Assertions.assertThat(updatedStudent.getId()).isNotNull();

        Assertions.assertThat(updatedStudent.getName()).isEqualTo(studentToBeSaved.getName());
    }

    @Test
    @DisplayName("Delete student")
    void delete_Student_WhenSuccessful() {
        Student studentToBeSaved = StudentCreator.createStudentToBeSaved();

        Student savedStudent = this.studentRepository.save(studentToBeSaved);

        this.studentRepository.delete(savedStudent);

        Optional<Student> studentOptional = this.studentRepository.findById(savedStudent.getId());

        Assertions.assertThat(studentOptional).isEmpty();
    }

    @Test
    @DisplayName("Find student by ID")
    void findById_Student_WhenSuccessful() {
        Student studentToBeSaved = StudentCreator.createStudentToBeSaved();

        Student savedStudent = this.studentRepository.save(studentToBeSaved);

        Long studentId = savedStudent.getId();

        Optional<Student> studentOptional = this.studentRepository.findById(studentId);

        Assertions.assertThat(studentOptional).isNotEmpty();
    }
}