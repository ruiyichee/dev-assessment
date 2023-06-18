package com.school.administrative.registration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.school.administrative.registration.model.Registration;
import com.school.administrative.registration.repository.RegistrationRepository;
import com.school.administrative.student.model.Student;
import com.school.administrative.student.repository.StudentRepository;
import com.school.administrative.teacher.model.Teacher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final StudentRepository studentRepository;
    private final RegistrationRepository registrationRepository;

    public Registration findRegistrationByTeacherIdAndStudentId(Long teacherId, Long studentId) {
        Optional<Registration> registration = registrationRepository.findByTeacherIdAndStudentId(teacherId,
                studentId);
        if (registration.isPresent()) {
            return registration.get();
        } else {
            return null;
        }
    }

    public void registerStudentsToTeacher(Teacher teacher, List<String> studentEmails) {
        // Get students and loop through them
        for (String studentEmail : studentEmails) {
            // Check that the student exists
            Optional<Student> student = studentRepository.findByEmail(studentEmail);
            if (student.isPresent()) {
                registerStudentToTeacher(teacher, student.get());
            }
        }
    }

    public void deregisterStudentFromTeacher(Registration registration, String reason) {
        registration.setRegister(false);
        registration.setReason(reason);
        registrationRepository.save(registration);
    }

    public List<String> getListOfStudentEmailsByTeacherId(long teacherId) {
        List<String> studentEmailsList = new ArrayList<>();

        Optional<List<Registration>> registrations = registrationRepository.findAllRegisteredByTeacherId(teacherId);
        if (registrations.isPresent()) {
            for (Registration registration : registrations.get()) {
                Optional<Student> student = studentRepository.findById(registration.getStudentId());
                if (student.isPresent()) {
                    studentEmailsList.add(student.get().getEmail());
                }
            }
        }

        return studentEmailsList;
    }

    protected void registerStudentToTeacher(Teacher teacher, Student student) {
        if (registrationRepository.findByTeacherIdAndStudentId(teacher.getId(),
                student.getId()).isPresent()) {
            // Registration already exists
            // Continue to next student
        } else {
            // Create the Registration
            registrationRepository.save(new Registration(teacher.getId(), student.getId()));
        }
    }
}
