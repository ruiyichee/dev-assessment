package com.school.administrative.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.administrative.student.model.Student;
import com.school.administrative.student.repository.StudentRepository;
import com.school.administrative.student.service.StudentService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentTests {
    private StudentService studentService;
    private StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    @BeforeEach
    void initTest() {
        studentService = new StudentService(studentRepository, null, null);
    }

    @Test
    void findExistentStudentByEmail() {
        Student student = new Student("jon@gmail.com", "Jon");
        Mockito.when(studentService.save(student.getEmail(), student.getName())).thenReturn(student);
        Mockito.when(studentRepository.findByEmail(student.getEmail())).thenReturn(java.util.Optional.of(student));
        assertThat(student).isNotNull();
        assertThat(student.getEmail()).isEqualTo("jon@gmail.com");
        assertThat(student.getName()).isEqualTo("Jon");
    }

    @Test
    void saveStudent() {
        Student student = new Student("jon@gmail.com", "Jon");
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        assertThat(student).isNotNull();
    }
}
