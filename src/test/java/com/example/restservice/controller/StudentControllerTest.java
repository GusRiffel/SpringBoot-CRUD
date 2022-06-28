package com.example.restservice.controller;

import com.example.restservice.domain.Student;
import com.example.restservice.service.StudentService;
import com.example.restservice.util.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentServiceMock;

    @BeforeEach
    void setUp() {
        List<Student> studentsList = List.of((StudentCreator.createValidStudent()));
        BDDMockito.when(studentServiceMock.listAll())
                .thenReturn(studentsList);
    }

    @Test
    @DisplayName("List of students")
    void list_ReturnListOfStudents_WhenSuccessful() {
        String expectedName = StudentCreator.createValidStudent().getName();

        List<Student> studentList = studentController.listAll().getBody();

        Assertions.assertThat(studentList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(studentList.get(0).getName()).isEqualTo(expectedName);
    }
}