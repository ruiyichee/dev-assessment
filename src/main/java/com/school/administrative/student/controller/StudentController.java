package com.school.administrative.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.administrative.error.ErrorMessage;
import com.school.administrative.error.student.EnumStudentError;
import com.school.administrative.student.model.Student;
import com.school.administrative.student.model.StudentListResponse;
import com.school.administrative.student.service.StudentService;
import com.school.administrative.teacher.service.TeacherService;

@RestController
@RequestMapping("/api")
public class StudentController {
	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;

	// Create student
	@PostMapping("/students")
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		// Check that the email is not already in use
		try {
			// Check that the email is not already in use
			Student studentData = studentService.findStudentByEmail(student.getEmail());
			if (studentData != null) {
				ErrorMessage errorMessage = new ErrorMessage(EnumStudentError.STUDENT_EXISTS.get());
				return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
			}
			Student studentResponse = studentService.save(student.getEmail(), student.getName());
			return new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get student based on email.
	@GetMapping("/students/{email}")
	public ResponseEntity<?> getStudentByEmail(@PathVariable("email") String email) {
		Student studentData = studentService.findStudentByEmail(email);

		if (studentData != null) {
			return new ResponseEntity<>(studentData, HttpStatus.OK);
		} else {
			ErrorMessage errorMessage = new ErrorMessage(EnumStudentError.STUDENT_NOT_EXISTS.get());
			return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
		}
	}

	// Get all students based on teacher email.
	@GetMapping("/commonstudents")
	public ResponseEntity<?> getCommonStudents(@RequestParam(name = "teacher") List<String> teacherEmails) {
		try {
			List<String> studentEmails = studentService.getCommonStudents(teacherEmails);

			if (studentEmails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			StudentListResponse students = new StudentListResponse(studentEmails);

			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}