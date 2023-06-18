package com.school.administrative.error.student;

public enum EnumStudentError {
	STUDENT_NOT_EXISTS("Student with this email does not exist."),
	STUDENT_EXISTS("Student with this email exists.");

	String value;

	EnumStudentError() {
	}

	EnumStudentError(String string) {
		this.value = string;
	}

	public String get() {
		return value;
	}
}