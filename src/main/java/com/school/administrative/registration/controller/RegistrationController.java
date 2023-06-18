package com.school.administrative.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.administrative.error.ErrorMessage;
import com.school.administrative.error.registration.EnumRegistrationError;
import com.school.administrative.error.student.EnumStudentError;
import com.school.administrative.error.teacher.EnumTeacherError;
import com.school.administrative.registration.model.Registration;
import com.school.administrative.registration.model.RegistrationDeregisterRequest;
import com.school.administrative.registration.model.RegistrationRequest;
import com.school.administrative.registration.service.RegistrationService;
import com.school.administrative.student.model.Student;
import com.school.administrative.student.service.StudentService;
import com.school.administrative.teacher.model.Teacher;
import com.school.administrative.teacher.service.TeacherService;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            // Get teacher
            Teacher teacher = teacherService.findTeacherByEmail(request.getTeacher());
            if (teacher == null) {
                ErrorMessage errorMessage = new ErrorMessage(EnumTeacherError.TEACHER_NOT_EXISTS.get());
                return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
            } else {
                registrationService.registerStudentsToTeacher(teacher, request.getStudents());
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deregister")
    public ResponseEntity<?> deregister(@RequestBody RegistrationDeregisterRequest request) {
        try {
            // Get teacher
            Teacher teacher = teacherService.findTeacherByEmail(request.getTeacher());
            if (teacher == null) {
                ErrorMessage errorMessage = new ErrorMessage(EnumTeacherError.TEACHER_NOT_EXISTS.get());
                return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
            } else {
                // Get student
                Student student = studentService.findStudentByEmail(request.getStudent());
                if (student == null) {
                    ErrorMessage errorMessage = new ErrorMessage(EnumStudentError.STUDENT_NOT_EXISTS.get());
                    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
                } else {
                    // Get registration
                    Registration registration = registrationService.findRegistrationByTeacherIdAndStudentId(
                            teacher.getId(), student.getId());
                    if (registration == null) {
                        ErrorMessage errorMessage = new ErrorMessage(
                                EnumRegistrationError.REGISTRATION_NOT_EXISTS.get());
                        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
                    } else {
                        if (!registration.isRegister()) {
                            ErrorMessage errorMessage = new ErrorMessage(
                                    EnumRegistrationError.REGISTRATION_DEREGISTERED.get());
                            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
                        }
                        registrationService.deregisterStudentFromTeacher(registration, request.getReason());
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                }

            }
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}