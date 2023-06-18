package com.school.administrative.student.model;

import java.util.List;

public class StudentListResponse {
	private List<String> students;

	public StudentListResponse(List<String> students) {
		this.students = students;
	}

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}
}