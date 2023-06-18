package com.school.administrative.teacher;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.administrative.teacher.model.Teacher;
import com.school.administrative.teacher.repository.TeacherRepository;
import com.school.administrative.teacher.service.TeacherService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeacherTests {
    private TeacherService teacherService;
    private TeacherRepository teacherRepository = Mockito.mock(TeacherRepository.class);

    @BeforeEach
    void initTest() {
        teacherService = new TeacherService(teacherRepository, null);
    }

    @Test
    void findExistentTeacherByEmail() {
        Teacher teacher = new Teacher("teachermary@gmail.com", "Mary");
        Mockito.when(teacherService.save(teacher.getEmail(), teacher.getName())).thenReturn(teacher);
        Mockito.when(teacherRepository.findByEmail(teacher.getEmail())).thenReturn(java.util.Optional.of(teacher));
        assertThat(teacher).isNotNull();
        assertThat(teacher.getEmail()).isEqualTo("teachermary@gmail.com");
        assertThat(teacher.getName()).isEqualTo("Mary");
    }

    @Test
    void saveTeacher() {
        Teacher teacher = new Teacher("teachermary@gmail.com", "Mary");
        Mockito.when(teacherService.save(teacher.getEmail(), teacher.getName())).thenReturn(teacher);
        assertThat(teacher).isNotNull();
    }
}