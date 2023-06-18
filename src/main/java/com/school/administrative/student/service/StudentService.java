package com.school.administrative.student.service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.school.administrative.student.repository.StudentRepository;
import com.school.administrative.registration.service.RegistrationService;
import com.school.administrative.teacher.service.TeacherService;
import com.school.administrative.teacher.model.Teacher;
import com.school.administrative.student.model.Student;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RegistrationService registrationService;
    private final TeacherService teacherService;

    public Student findStudentByEmail(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public Student save(String email, String name) {
        return studentRepository
                .save(new Student(email, name));
    }

    public List<String> getCommonStudents(List<String> teacherEmails) {
        List<String> studentEmails = new ArrayList<String>();
        // Get teacherId
        for (String email : teacherEmails) {
            Teacher teacher = teacherService.findTeacherByEmail(email);
            if (teacher != null) {
                // Get students
                List<String> studentEmailsList = registrationService.getListOfStudentEmailsByTeacherId(teacher.getId());
                for (String studentEmail : studentEmailsList) {
                    if (!studentEmails.contains(studentEmail)) {
                        studentEmails.add(studentEmail);
                    }
                }
            }
        }
        return studentEmails;
    }
}
