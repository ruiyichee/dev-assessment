package com.school.administrative.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.administrative.registration.repository.RegistrationRepository;
import com.school.administrative.registration.service.RegistrationService;
import com.school.administrative.student.repository.StudentRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RegistrationTests {
    private RegistrationService registrationService;
    private RegistrationRepository registrationRepository = Mockito.mock(RegistrationRepository.class);
    private StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    @BeforeEach
    void initTest() {
        registrationService = new RegistrationService(studentRepository, registrationRepository);
    }
}
