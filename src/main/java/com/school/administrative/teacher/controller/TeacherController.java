package com.school.administrative.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.administrative.error.ErrorMessage;
import com.school.administrative.error.teacher.EnumTeacherError;
import com.school.administrative.student.service.StudentService;
import com.school.administrative.teacher.model.Teacher;
import com.school.administrative.teacher.model.TeacherListResponse;
import com.school.administrative.teacher.service.TeacherService;

@RestController
@RequestMapping("/api")
public class TeacherController {
	@Autowired
	StudentService studentService;

	@Autowired
	TeacherService teacherService;

	// Create teacher
	@PostMapping("/teachers")
	public ResponseEntity<?> postTeacher(@RequestBody Teacher teacher) {
		try {
			// Check that the email is not already in use
			Teacher teacherData = teacherService.findTeacherByEmail(teacher.getEmail());
			if (teacherData != null) {
				ErrorMessage errorMessage = new ErrorMessage(EnumTeacherError.TEACHER_EXISTS.get());
				return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
			}
			Teacher teacherResponse = teacherService.save(teacher.getEmail(), teacher.getName());
			return new ResponseEntity<>(teacherResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
			return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/teachers")
	public ResponseEntity<?> getTeachersAndStudents() {
		TeacherListResponse teachersResponse = teacherService.getListOfTeachersAndStudents();
		return new ResponseEntity<>(teachersResponse, HttpStatus.OK);
	}
}